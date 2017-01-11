package com.ximsfei.dynamicskindemo;

import android.app.Application;

import java.io.File;
import java.io.IOException;

import skin.support.SkinManager;
import skin.support.utils.SkinConstants;
import skin.support.utils.SkinFileUtils;
import skin.support.utils.SkinLog;

/**
 * Created by pengfengwang on 2017/1/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setUpSkinFile();
        SkinManager.init(this);
        SkinManager.getInstance().loadSkin();
    }

    private void setUpSkinFile() {
        try {
            String[] skinFiles = getAssets().list(SkinConstants.SKIN_DEPLOY_PATH);
            for (String fileName : skinFiles) {
                File file = new File(SkinFileUtils.getSkinDir(this), fileName);
                SkinLog.e("file = " + file);
                if (!file.exists()) {
                    SkinFileUtils.copySkinAssetsToDir(this, fileName, SkinFileUtils.getSkinDir(this));
                }
            }
        } catch (IOException e) {
            SkinLog.e(e.toString());
            e.printStackTrace();
        }
    }
}
