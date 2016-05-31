package com.dyf.wenbo.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.delegate.SplashViewDelegate;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.presenter.home.HomeActivity;
import com.dyf.wenbo.scm.ISplashScm;
import com.dyf.wenbo.scm.SplashScm;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.zhonghua.dileber.tools.annotation.CloseStatusBar;
import com.zhonghua.dileber.tools.annotation.CloseTitle;

@CloseTitle
@CloseStatusBar
public class SplashActivity extends ActivityPresenter<SplashViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class<SplashViewDelegate> getDelegateClass() {
        return SplashViewDelegate.class;
    }
    ISplashScm splashSrc;
    splashhandler splashhandler;
    Handler x;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        splashSrc = new SplashScm();
        x = new Handler();
        splashhandler = new splashhandler();
        splashSrc.splash();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(x!=null&&splashhandler!=null){
            x.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(x!=null&&splashhandler!=null){
            x.postDelayed(splashhandler, 3000);
        }
    }

    class splashhandler implements Runnable{

        public void run() {

            UserModel userModel = new UserModel();
            userModel.setId(10086);
            userModel.setApptype(1);
            userModel.setLoginname("yonghuming");
            userModel.setUserimage("");
            userModel.setUsername("yonghuming");
            userModel.setUserpass("000000");
            AppHper.putAppUser(userModel);
            Intent intent = new Intent(SplashActivity.this,HomeActivity.class);


//            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onClick(View view) {

    }
}