package com.dyf.wenbo.model;

import com.zhonghua.dileber.mvp.model.SWrapper;
import java.util.List;

/**
 * Created by shidawei on 16/3/26.
 */
public class ChatWrapper extends SWrapper{

    List<ChatModel> data;

    public List<ChatModel> getData() {
        return data;
    }

    public void setData(List<ChatModel> data) {
        this.data = data;
    }
}
