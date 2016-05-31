package com.dyf.wenbo.scm;
import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.utils.HuanXinUtil;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.dyf.wenbo.R;
import com.zhonghua.dileber.tools.SLog;

/**
 * Created by  on 16/1/17.
 */
public class SplashScm extends Scm implements ISplashScm {

    @Override
    public void splash() {
        SLog.w("Ok");
        UserModel userModel = AppHper.getAppUser();
        if(userModel!=null){
            HuanXinUtil.login(userModel, null);
        }
    }

}
