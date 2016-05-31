package com.dyf.wenbo.scm.home;

import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.model.ChatWrapper;
import com.zhonghua.dileber.mvp.scm.INetListener;

import java.util.List;

/**
 * Created by  on 16/1/17.
 */
public interface IHomeScm {

    void findChat(String like, ChatModel chatModel, INetListener<List<ChatModel>> iNetListener);

}
