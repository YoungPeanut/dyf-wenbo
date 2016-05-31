package com.dyf.wenbo.app;


import com.dyf.wenbo.utils.HuanXinUtil;
import com.zhonghua.dileber.app.SApplication;
import com.zhonghua.dileber.tools.annotation.SFontdType;

/**
 * Created by  on 16/1/21.
 */
@SFontdType(value = "icomoon.ttf")
public class MApplication extends SApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        buglog(Configer.BUG_STATIC, Configer.BUG_NAME);

        HuanXinUtil.init();
    }
}
