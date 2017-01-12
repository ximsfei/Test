package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by ximsfei on 2017/1/11.
 */

public class SkinCompatResources {
    private static volatile SkinCompatResources sInstance;
    private final Context mAppContext;
    private Resources mResources;
    private String mSkinPkgName;
    private boolean isDefaultSkin;

    private SkinCompatResources(Context context) {
        mAppContext = context;
    }

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinCompatResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatResources(context);
                }
            }
        }
    }

    public static SkinCompatResources getInstance() {
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

        return targetResId == 0 ? originColor : mResources.getColor(targetResId);
    }

    public Drawable getDrawable(int resId) {
        Drawable originDrawable = ContextCompat.getDrawable(mAppContext, resId);
        if (mResources == null || isDefaultSkin) {
            return originDrawable;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "drawable", mSkinPkgName);

        return targetResId == 0 ? originDrawable : mResources.getDrawable(targetResId);
    }

    public ColorStateList getColorStateList(int resId) {
        ColorStateList originColorList = ContextCompat.getColorStateList(mAppContext, resId);
        if (mResources == null || isDefaultSkin) {
            return originColorList;
        }

        String resName = mAppContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mSkinPkgName);

        return targetResId == 0 ? originColorList : mResources.getColorStateList(targetResId);
    }
}
