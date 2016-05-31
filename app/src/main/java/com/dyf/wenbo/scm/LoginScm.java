package com.dyf.wenbo.scm;
import com.android.volley.VolleyError;
import com.dyf.wenbo.app.Api;
import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.app.Configer;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.model.UserWrapper;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.dyf.wenbo.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class LoginScm extends Scm implements ILoginScm {

    @Override
    public void login(UserModel userModel, final INetListener<UserModel> iNetListener) {
        iNetListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginname",userModel.getLoginname());
        map.put("userpass",userModel.getUserpass());
        map.put("apptype",Configer.app_type);
        netWork(Configer.HTTP_URL + Api.ADD_LOGIN, map, UserWrapper.class, new HttpListener<UserWrapper>() {
            @Override
            protected void onSuccess(UserWrapper response) {
                if(response.getState()==1){
                    UserModel userModel1 = response.getData();
                    if(userModel1==null){
                        iNetListener.errMsg("用户异常");
                        return;
                    }
                    AppHper.putAppUser(response.getData());
                    iNetListener.success(response.getData());
                }else if(response.getState()==-1){
                    iNetListener.errMsg(response.getMsg());
                }else{
                    iNetListener.failed();
                }
            }

            @Override
            protected void onFailure(VolleyError error) {

                iNetListener.failed();
            }
        },TYPE_GET);
    }
}
