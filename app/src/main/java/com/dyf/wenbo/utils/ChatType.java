package com.dyf.wenbo.utils;

import android.widget.Switch;

public class ChatType {

    //0: 生活 1:赛事 2:感情 3:技能 4:杂谈

    /**
     * leixing 1为 文字，2为图片
     * @param type
     * @param leixing
     * @return
     */
    public static String getType(int type,int leixing){
        String temp = "";
        String temp_url = "";
        switch(type){
            case 0:
                temp = "生活";
                temp_url = "http://7xj92l.com1.z0.glb.clouddn.com/image_u%3D1452885184%2C1557043122%26fm%3D21%26gp%3D0.jpg";
                break;
            case 1:
                temp = "赛事";
                temp_url = "http://7xj92l.com1.z0.glb.clouddn.com/image_u%3D1879500545%2C2615424728%26fm%3D21%26gp%3D0.jpg";
                break;
            case 2:
                temp = "感情";
                temp_url = "http://7xj92l.com1.z0.glb.clouddn.com/image_u%3D1145073986%2C3327995390%26fm%3D21%26gp%3D0.jpg";
                break;
            case 3:
                temp = "技能";
                temp_url = "http://7xj92l.com1.z0.glb.clouddn.com/image_u%3D1064507919%2C3210895071%26fm%3D11%26gp%3D0.jpg";
                break;
            case 4:
                temp = "杂谈";
                temp_url = "http://7xj92l.com1.z0.glb.clouddn.com/image_u%3D1862740718%2C3882366884%26fm%3D21%26gp%3D0.jpg";
                break;
        }
        if(leixing==1){
            return temp;
        }else{
            return temp_url;
        }

    }

}
