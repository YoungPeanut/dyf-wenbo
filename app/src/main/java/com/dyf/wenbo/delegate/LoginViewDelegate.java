package com.dyf.wenbo.delegate;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.presenter.RegistActivity;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;

/**
 * Created by  on 16/1/17.
 */
public class LoginViewDelegate extends AppViewDelegate implements ILoginView,View.OnClickListener{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    EditText login_username,login_pass;

    @Override
    public void initWidget() {
        super.initWidget();
        login_username = get(R.id.login_phone);
        login_pass = get(R.id.login_pass);
        setOnClickListener(this, R.id.login_repass);
    }
    public UserModel getUser() {
        UserModel userModel = new UserModel();
        userModel.setLoginname(login_username.getText().toString().trim());
        userModel.setUserpass(login_pass.getText().toString().trim());
        return userModel;
    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent();
        switch (view.getId()){
            case R.id.login_repass:
                it.setClass(getActivity(), RegistActivity.class);
                getActivity().startActivity(it);
                break;
        }

    }


}