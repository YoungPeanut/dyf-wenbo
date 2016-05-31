package com.dyf.wenbo.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.presenter.home.HomeActivity;
import com.dyf.wenbo.utils.HuanXinUtil;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.dyf.wenbo.R;
import com.dyf.wenbo.delegate.LoginViewDelegate;
import com.dyf.wenbo.scm.ILoginScm;
import com.dyf.wenbo.scm.LoginScm;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.UText;
import com.zhonghua.dileber.tools.annotation.CloseTitle;

@CloseTitle
public class LoginActivity extends ActivityPresenter<LoginViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Class<LoginViewDelegate> getDelegateClass() {
        return LoginViewDelegate.class;
    }

    ILoginScm loginSrc;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        loginSrc = new LoginScm();
        viewDelegate.get(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final UserModel userModel = viewDelegate.getUser();
                final String userName = userModel.getLoginname();
                final String pass = userModel.getUserpass();
                if(!UText.checkEditText(userName, 6)||!UText.checkEditText(pass,6)){
                    viewDelegate.toast("账号或密码不能小于6位数", Toast.LENGTH_SHORT);
                    return;
                }
                loginSrc.login(userModel, new INetListener<UserModel>() {

                    @Override
                    public void before() {
                        viewDelegate.loading();
                    }

                    @Override
                    public void success(UserModel model) {
                        final Intent it = new Intent();
                        it.setClass(LoginActivity.this, HomeActivity.class);
                        HuanXinUtil.login(userModel, null);
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.toast("欢迎" + model.getUsername(), Toast.LENGTH_SHORT);
                        startActivity(it);
                        finish();
                    }


                    @Override
                    public void failed() {
                        viewDelegate.loadDialogDismiss();
                    }

                    @Override
                    public void errMsg(String msg) {
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.showAlert(viewDelegate.DIALOG_ERROR, msg);
                    }
                });

            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AppHper.getAppUser()!=null){
            Intent it = new Intent();
            it.setClass(LoginActivity.this,HomeActivity.class);
            startActivity(it);
            finish();
        }
    }
}