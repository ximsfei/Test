package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.SkinManager;
import skin.support.utils.SkinLog;

/**
 * Created by ximsfei on 2017/1/10.
 */

public class SkinCompatTextHelper {

    final TextView mView;

    int mTextColorResId;
    int mTextColorHintResId;

    SkinCompatTextHelper(TextView view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        final Context context = mView.getContext();

        // First read the TextAppearance style id
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.AppCompatTextHelper, defStyleAttr, 0);
        final int ap = a.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        // Now read the compound drawable and grab any tints
        a.recycle();

        // First check TextAppearance's textAllCaps value
        if (ap != -1) {
            a = TintTypedArray.obtainStyledAttributes(context, ap, R.styleable.TextAppearance);
                // If we're running on < API 23, the text color may contain theme references
                // so let's re-set using our own inflater
                if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
//                    textColor = a.getColorStateList(R.styleable.TextAppearance_android_textColor);
                    mTextColorResId = a.getResourceId(R.styleable.TextAppearance_android_textColor, -1);
                }
                if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                    mTextColorHintResId = a.getResourceId(
                            R.styleable.TextAppearance_android_textColorHint, -1);
            }
            a.recycle();
        }

        // Now read the style's values
        a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TextAppearance,
                defStyleAttr, 0);
            // If we're running on < API 23, the text color may contain theme references
            // so let's re-set using our own inflater
            if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
                mTextColorResId = a.getResourceId(R.styleable.TextAppearance_android_textColor, -1);
            }
            if (a.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                mTextColorHintResId = a.getResourceId(
                        R.styleable.TextAppearance_android_textColorHint, -1);
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
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,
                resId, R.styleable.TextAppearance);
//        if (a.hasValue(android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps)) {
//            // This breaks away slightly from the logic in TextView.setTextAppearance that serves
//            // as an "overlay" on the current state of the TextView. Since android:textAllCaps
//            // may have been set to true in this text appearance, we need to make sure that
//            // app:textAllCaps has the chance to override it
//        }
        if (a.hasValue(R.styleable.TextAppearance_android_textColor)) {
//            // If we're running on < API 23, the text color may contain theme references
//            // so let's re-set using our own inflater
            mTextColorResId = a.getResourceId(R.styleable.TextAppearance_android_textColor, -1);
//            if (textColor != null) {
//                mView.setTextColor(textColor);
//            }
//        }
        }
        a.recycle();
        applySkin();
    }

    public void applySkin() {
        if (mTextColorResId != -1) {
            int color = SkinResLoader.getInstance().getColor(mTextColorResId);
            mView.setTextColor(color);
            return;
        }
        if (mTextColorHintResId != -1) {
            return;
        }
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
