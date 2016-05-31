package com.dyf.wenbo.scm;

import com.dyf.wenbo.model.UserModel;
import com.zhonghua.dileber.mvp.scm.INetListener;

/**
 * Created by  on 16/1/17.
 */
public interface IRegistScm {

    void regist(UserModel userModel, final INetListener<UserModel> iNetListener);

}
