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
                R.styleable.ViewBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                mBackgroundResId = a.getResourceId(
                        R.styleable.ViewBackgroundHelper_android_background, -1);
                SkinLog.d("mBackgroundResId = " + mBackgroundResId);
                SkinLog.d("mBackgroundResId = " + R.styleable.ViewBackgroundHelper_android_background);
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

    public void applySkin() {
        if (mBackgroundResId == -1) {
            return;
        }
        String typeName = mView.getResources().getResourceTypeName(mBackgroundResId);
        if ("color".equals(typeName)) {
            int color = SkinResLoader.getInstance().getColor(mBackgroundResId);
            mView.setBackgroundColor(color);
        } else if ("drawable".equals(typeName)) {
            Drawable drawable = SkinResLoader.getInstance().getDrawable(mBackgroundResId);
            mView.setBackgroundDrawable(drawable);
        }
    }
}
