package skin.support;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Method;

import skin.support.observe.SkinObservable;
import skin.support.utils.SkinFileUtils;
import skin.support.utils.SkinLog;
import skin.support.utils.SkinPreference;
import skin.support.widget.SkinResLoader;

/**
 * Created by ximsfei on 17-1-10.
 */

public class SkinManager extends SkinObservable {
    private static volatile SkinManager sInstance;
    private final Context mAppContext;

    public interface SkinLoaderListener {
        void onStart();

        void onSuccess();

        void onFailed(String errMsg);
    }

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinManager(context);
                }
            }
        }
    }

    public static SkinManager getInstance() {
        return sInstance;
    }

    private SkinManager(Context context) {
        mAppContext = context.getApplicationContext();
        SkinPreference.init(mAppContext);
        SkinResLoader.init(mAppContext);
    }

    public void restoreDefaultTheme() {
        SkinPreference.getInstance().setSkinPath("").commitEditor();
        SkinResLoader.getInstance().setSkinResource(mAppContext.getResources(), mAppContext.getPackageName());
        notifyUpdateSkin();
    }

    public void loadSkin() {
        String skin = SkinPreference.getInstance().getSkinPath();
        if (TextUtils.isEmpty(skin)) {
            return;
        }
        loadSkin(skin, null);
    }

    public void loadSkin(SkinLoaderListener listener) {
        String skin = SkinPreference.getInstance().getSkinPath();
        if (TextUtils.isEmpty(skin)) {
            return;
        }
        loadSkin(skin, listener);
    }

    public void loadSkin(String skinName, final SkinLoaderListener listener) {
        new SkinLoadTask(listener).execute(skinName);
    }

    private class SkinLoadTask extends AsyncTask<String, Void, Boolean> {

        private final SkinLoaderListener mListener;

        public SkinLoadTask(SkinLoaderListener listener) {
            mListener = listener;
        }

        protected void onPreExecute() {
            if (mListener != null) {
                mListener.onStart();
            }
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                if (params.length == 1) {
                    SkinLog.d("skinPkgPath", params[0]);
                    String skinPkgPath = SkinFileUtils.getSkinDir(mAppContext) + File.separator + params[0];
                    File file = new File(skinPkgPath);
                    SkinLog.d("skinPkgPath", file + " " + file.exists());
                    if (!file.exists()) {
                        return false;
                    }
                    PackageManager mPm = mAppContext.getPackageManager();
                    PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
                    String pkgName = mInfo.packageName;
                    AssetManager assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                    addAssetPath.invoke(assetManager, skinPkgPath);

                    Resources superRes = mAppContext.getResources();
                    Resources resources = null;
                    try {
                        resources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SkinPreference.getInstance().setSkinPath(params[0]).commitEditor();

                    if (resources != null) {
                        SkinResLoader.getInstance().setSkinResource(resources, pkgName);
                        return true;
                    }
                    SkinResLoader.getInstance().setSkinResource(
                            mAppContext.getResources(), mAppContext.getPackageName());
                    return false;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Boolean result) {
            SkinLog.e("result = " + result);
            if (result != null && result) {
                if (mListener != null) mListener.onSuccess();
                notifyUpdateSkin();
            } else {
                if (mListener != null) mListener.onFailed("皮肤资源获取失败");
            }
        }

    }

    ;
}
