package com.dyf.wenbo.scm.home;
import com.android.volley.VolleyError;
import com.dyf.wenbo.app.Api;
import com.dyf.wenbo.app.Configer;
import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.model.ChatWrapper;
import com.zhonghua.dileber.http.HttpListener;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.mvp.scm.Scm;
import com.dyf.wenbo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  on 16/1/17.
 */
public class HomeScm extends Scm implements IHomeScm {

    @Override
    public void findChat(String like, ChatModel chatModel, final INetListener<List<ChatModel>> iNetListener) {
        iNetListener.before();
        Map<String,Object> map = new HashMap<String, Object>();
        if(like!=null){
        map.put("like", like);
        }
        Integer userid = chatModel.getChatuserid();
        if(userid!=null){
            map.put("userId",userid);
        }
        Integer state = chatModel.getChatstate();
        if(state!=null){
            map.put("state",state);
        }
        Integer type = chatModel.getChattype();
        if(type!=null){
            map.put("type", type);
        }
        netWork(Configer.HTTP_URL + Api.GET_CHATROOM, map, ChatWrapper.class, new HttpListener<ChatWrapper>() {
            @Override
            protected void onSuccess(ChatWrapper response) {
                if(response!=null){
                    if(response.getState()==1){
                        List<ChatModel> chatModels = response.getData();
                        if(chatModels!=null){
                            iNetListener.success(chatModels);
                        }
                    }
                }
                iNetListener.errMsg("数据为空");
            }

            @Override
            protected void onFailure(VolleyError error) {
                iNetListener.failed();
            }
        },TYPE_GET);
    }
}
