package skin.support.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/11.
 */

public class SkinResLoader {
    private static volatile SkinResLoader sInstance;
    private final Context mAppContext;
    private Resources mResources;
    private String mSkinPkgName;
    private boolean isDefaultSkin;

    private SkinResLoader(Context context) {
        mAppContext = context;
    }

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinResLoader.class) {
                if (sInstance == null) {
                    sInstance = new SkinResLoader(context);
                }
            }
        }
    }

    public static SkinResLoader getInstance() {
        return sInstance;
    }

    public void setSkinResource(Resources resources, String pkgName) {
        mResources = resources;
        mSkinPkgName = pkgName;
        isDefaultSkin = mAppContext.getPackageName().equals(pkgName);
    }

    public int getColor(int resId) {
        int originColor = ContextCompat.getColor(mAppContext, resId);
        if (mResources == null || isDefaultSkin) {
            return originColor;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);
        int targetColor;

        try {
            targetColor = mResources.getColor(targetResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            targetColor = originColor;
        }

        return targetColor;
    }
}
