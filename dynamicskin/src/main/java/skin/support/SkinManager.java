package skin.support;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by ximsfei on 17-1-10.
 */

public class SkinManager {
    private static volatile SkinManager sInstance;
    private final Context mAppContext;

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
    }

    public int getColor(int attrValueRefId) {
        return -1;
    }

    public Drawable getDrawable(int attrValueRefId) {
        return null;
    }
}
