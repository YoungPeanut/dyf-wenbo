package com.dyf.wenbo.scm;
import com.android.volley.VolleyError;
import com.dyf.wenbo.app.Api;
import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.app.Configer;
import com.dyf.wenbo.model.TypeWrapper;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.model.SWrapper;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.dyf.wenbo.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class ActiveScm extends Scm implements IActiveScm {


    @Override
    public void setType(Integer id, Integer leixing, final INetListener<Integer> iNetListener) {
        iNetListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("leixing",leixing);
        map.put("userId", AppHper.getAppUser().getId());
        netWork(Configer.HTTP_URL + Api.SET_TYPE, map, SWrapper.class, new HttpListener<SWrapper>() {
            @Override
            protected void onSuccess(SWrapper response) {
                if(response!=null){
                    iNetListener.success(response.getState());
                }
                iNetListener.failed();
            }

            @Override
            protected void onFailure(VolleyError error) {
                iNetListener.failed();
            }
        },TYPE_GET);
    }

    @Override
    public void getType(Integer id, final INetListener<TypeWrapper> iNetListener) {
        iNetListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("userId", AppHper.getAppUser().getId());
        netWork(Configer.HTTP_URL + Api.GET_TYPE, map, TypeWrapper.class, new HttpListener<TypeWrapper>() {
            @Override
            protected void onSuccess(TypeWrapper response) {
                if(response!=null){
                    iNetListener.success(response);
                }
                iNetListener.failed();
            }

            @Override
            protected void onFailure(VolleyError error) {
                iNetListener.failed();
            }
        },TYPE_GET);
    }
}
