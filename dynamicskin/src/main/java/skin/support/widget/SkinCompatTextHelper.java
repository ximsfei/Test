package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatTextHelper {

    final TextView mView;

    SkinCompatTextHelper(TextView view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();
//        final AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();

        // First read the TextAppearance style id
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.AppCompatTextHelper, defStyleAttr, 0);
        final int ap = a.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        // Now read the compound drawable and grab any tints
//        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
//            mDrawableLeftTint = createTintInfo(context, drawableManager,
//                    a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
//        }
//        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
//            mDrawableTopTint = createTintInfo(context, drawableManager,
//                    a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
//        }
//        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
//            mDrawableRightTint = createTintInfo(context, drawableManager,
//                    a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
//        }
//        if (a.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
//            mDrawableBottomTint = createTintInfo(context, drawableManager,
//                    a.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
//        }
        a.recycle();

        ColorStateList textColor = null;
        ColorStateList textColorHint = null;

        // First check TextAppearance's textAllCaps value
        if (ap != -1) {
            a = TintTypedArray.obtainStyledAttributes(context, ap, R.styleable.TextAppearance);
            if (Build.VERSION.SDK_INT < 23) {
                // If we're running on < API 23, the text color may contain theme references
                // so let's re-set using our own inflater
                if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
                    textColor = a.getColorStateList(R.styleable.TextAppearance_android_textColor);
                }
                if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                    textColorHint = a.getColorStateList(
                            R.styleable.TextAppearance_android_textColorHint);
                }
            }
            a.recycle();
        }

        // Now read the style's values
        a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TextAppearance,
                defStyleAttr, 0);
        if (Build.VERSION.SDK_INT < 23) {
            // If we're running on < API 23, the text color may contain theme references
            // so let's re-set using our own inflater
            if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
                textColor = a.getColorStateList(R.styleable.TextAppearance_android_textColor);
            }
            if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                textColorHint = a.getColorStateList(
                        R.styleable.TextAppearance_android_textColorHint);
            }
        }
        a.recycle();

//        if (textColor != null) {
//            mView.setTextColor(textColor);
//        }
//        if (textColorHint != null) {
//            mView.setHintTextColor(textColorHint);
//        }
    }

    void onSetTextAppearance(Context context, int resId) {
//        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,
//                resId, android.support.v7.appcompat.R.styleable.TextAppearance);
//        if (a.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps)) {
//            // This breaks away slightly from the logic in TextView.setTextAppearance that serves
//            // as an "overlay" on the current state of the TextView. Since android:textAllCaps
//            // may have been set to true in this text appearance, we need to make sure that
//            // app:textAllCaps has the chance to override it
//        }
//        if (Build.VERSION.SDK_INT < 23
//                && a.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor)) {
//            // If we're running on < API 23, the text color may contain theme references
//            // so let's re-set using our own inflater
//            final ColorStateList textColor
//                    = a.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
//            if (textColor != null) {
//                mView.setTextColor(textColor);
//            }
//        }
//        a.recycle();
    }

    public void applySkin() {
//        if (mBackgroundResId == -1) {
//            return;
//        }
//        SkinLog.d("pengfeng " + mBackgroundResId);
////        int color = SkinManager.getInstance().getColor(mBackgroundResId);
////        SkinLog.d("pengfeng " + color);
////        if (mView instanceof TextView) {
////            ((TextView) mView).setTextColor(color);
////        }
//        int color = SkinResLoader.getInstance().getColor(mBackgroundResId);
//        SkinLog.d("pengfeng" + color);
//        mView.setBackgroundResource(mBackgroundResId);
    }
}
