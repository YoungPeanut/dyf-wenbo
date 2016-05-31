package com.dyf.wenbo.delegate;

import android.view.View;
import android.widget.EditText;
import com.dyf.wenbo.exception.RegistException;
import com.dyf.wenbo.model.UserModel;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;
import com.zhonghua.dileber.tools.UText;

/**
 * Created by  on 16/1/17.
 */
public class RegistViewDelegate extends AppViewDelegate implements IRegistView,View.OnClickListener{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_regist;
    }


    EditText regist_pass,regist_phone;

    @Override
    public void initWidget() {
        super.initWidget();
        regist_pass = get(R.id.regist_pass);
        regist_phone = get(R.id.regist_phone);
        setOnClickListener(this, R.id.regist_btn);
    }

    public UserModel getUser() throws RegistException {

        String username = regist_phone.getText().toString().trim();
        String pass = regist_pass.getText().toString().trim();
        if(!UText.checkEditText(username, 0)){
            throw new RegistException("用户名不能为空");
        }
        if(!UText.checkEditText(username,6)||!UText.checkEditText(pass,6)){
            throw new RegistException("用户名密码不能小于6位");
        }

        UserModel userModel = new UserModel();
        userModel.setLoginname(username);
        userModel.setUserpass(pass);
        return userModel;
    }

    @Override
    public void onClick(View view) {

    }
}