package skin.support.widget;

import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatBackgroundHelper {

    private final View mView;

    private int mBackgroundResId = -1;

    SkinCompatBackgroundHelper(View view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(mView.getContext(), attrs,
                android.support.v7.appcompat.R.styleable.ViewBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                mBackgroundResId = a.getResourceId(
                        R.styleable.ViewBackgroundHelper_android_background, -1);
                SkinLog.d("mBackgroundResId = " + mBackgroundResId);
            }
        } finally {
            a.recycle();
        }
    }

    void onSetBackgroundResource(int resId) {
        mBackgroundResId = resId;
        // Update the default background tint
        applySkin();
    }

    void onSetBackgroundDrawable(Drawable background) {
//        mBackgroundResId = -1;
        // We don't know that this drawable is, so we need to clear the default background tint
        applySkin();
    }

    public void applySkin() {
        if (mBackgroundResId == -1) {
            return;
        }
        SkinLog.d("pengfeng " + mBackgroundResId);
//        int color = SkinManager.getInstance().getColor(mBackgroundResId);
//        SkinLog.d("pengfeng " + color);
//        if (mView instanceof TextView) {
//            ((TextView) mView).setTextColor(color);
//        }
        int color = SkinResLoader.getInstance().getColor(mBackgroundResId);
        SkinLog.d("pengfeng" + color);
        mView.setBackgroundColor(color);
    }
}
