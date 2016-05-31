package com.dyf.wenbo.app;

import com.dyf.wenbo.model.UserModel;
import com.zhonghua.dileber.data.PerfManager;
import com.zhonghua.dileber.tools.HJson;


public final class AppHper {

    public static void putAppUser(UserModel userModel){
        PerfManager.getInstance().put(Configer.PERF_APP,Configer.PERF_USER, HJson.toJson(userModel));
    }

    public static UserModel getAppUser(){
        //String o = (String)PerfManager.getInstance().get(Configer.PERF_APP,Configer.PERF_USER, "");
        String user = PerfManager.getInstance().get(Configer.PERF_APP,Configer.PERF_USER,null,String.class);
        if(user!=null){
            return HJson.fromJson(user,UserModel.class);
        }else{
            return null;
        }
    }

    public static boolean clearAppUser(){
        return PerfManager.getInstance().remove(Configer.PERF_APP, Configer.PERF_USER);
    }

}
