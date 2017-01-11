package com.ximsfei.dynamicskindemo;

import android.os.Bundle;
import android.view.View;

import skin.support.SkinManager;
import skin.support.app.SkinCompatActivity;
import skin.support.utils.SkinLog;

/**
 * Created by pengfengwang on 2017/1/9.
 */

public class MainActivity extends SkinCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinManager.getInstance().loadSkin("new.skin", new SkinManager.SkinLoaderListener() {
                    @Override
                    public void onStart() {
                        SkinLog.d("onStart");
                    }

                    @Override
                    public void onSuccess() {
                        SkinLog.d("onSuccess");
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        SkinLog.d("onFailed");
                    }
                });
            }
        });

        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinManager.getInstance().restoreDefaultTheme();
            }
        });
    }

}
