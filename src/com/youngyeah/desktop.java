package com.youngyeah;

import java.io.File;

/**
 * Created by 李凌耀 on 2017/8/7.
 */
public class desktop {
    public static void main(String[] args) {
        File f = new File(args[0]);
        if(!f.exists())
            return;
        PrintSetting printSettingInstance = Factory.getInstance().getPrintSettingInstance();
        printSettingInstance.setFile(f);
        new Setting();
    }
}
