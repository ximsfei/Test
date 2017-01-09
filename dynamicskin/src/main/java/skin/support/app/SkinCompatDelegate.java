package skin.support.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import skin.support.utils.SkinConfig;
import skin.support.utils.SkinLog;

/**
 * Created by pengfengwang on 2017/1/9.
 */

public class SkinCompatDelegate implements LayoutInflaterFactory {
    private final AppCompatActivity mAppCompatActivity;
    private SkinCompatViewInflater mSkinCompatViewInflater;

    private SkinCompatDelegate(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        boolean skinEnabled = attrs.getAttributeBooleanValue(
                SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLED, false);
        View view = mAppCompatActivity.getDelegate().createView(parent, name, context, attrs);

        if (skinEnabled) {
            if (view == null) {
                view = createView(name, context, attrs);
            }
            if (view == null) {
                return null;
            }
            parseSkinAttr(context, attrs, view);
        }
        SkinLog.d("view name = " + name + ", skin enabled = " + skinEnabled);
        return view;
    }

    private View createView(final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {

        if (mSkinCompatViewInflater == null) {
            mSkinCompatViewInflater = new SkinCompatViewInflater();
        }

        return mSkinCompatViewInflater.createViewFromTag(context, name, attrs);
    }

    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            SkinLog.d("AttributeName = " + attrName + ", attrValue = " + attrValue);
        }
    }

    public static SkinCompatDelegate create(AppCompatActivity appCompatActivity) {
        return new SkinCompatDelegate(appCompatActivity);
    }
}
