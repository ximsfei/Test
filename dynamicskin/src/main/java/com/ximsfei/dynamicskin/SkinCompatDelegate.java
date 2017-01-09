package com.ximsfei.dynamicskin;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by pengfengwang on 2017/1/9.
 */

public class SkinCompatDelegate implements LayoutInflaterFactory {
    private final AppCompatActivity mAppCompatActivity;

    private SkinCompatDelegate(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.e("pengfeng", "name = " + name);
        View view = mAppCompatActivity.getDelegate().createView(parent, name, context, attrs);
        Log.e("pengfeng", "view = " + view);
        return view;
    }

    public static SkinCompatDelegate create(AppCompatActivity appCompatActivity) {
        return new SkinCompatDelegate(appCompatActivity);
    }
}
