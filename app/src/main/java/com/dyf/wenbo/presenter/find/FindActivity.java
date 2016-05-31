package com.dyf.wenbo.presenter.find;

import android.os.Bundle;
import android.view.View;
import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.scm.home.HomeScm;
import com.dyf.wenbo.scm.home.IHomeScm;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.dyf.wenbo.R;
import com.dyf.wenbo.delegate.find.FindViewDelegate;
import com.dyf.wenbo.scm.find.IFindScm;
import com.dyf.wenbo.scm.find.FindScm;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.annotation.CloseTitle;

import java.util.List;

@CloseTitle
public class FindActivity extends ActivityPresenter<FindViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IFindScm findSrc = new FindScm();

    }

    @Override
    protected Class<FindViewDelegate> getDelegateClass() {
        return FindViewDelegate.class;
    }
    IHomeScm homeSrc = null;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        homeSrc = new HomeScm();
        ChatModel chatModel = new ChatModel();
        chatModel.setChatstate(0);
        homeSrc.findChat(null, chatModel, new INetListener<List<ChatModel>>() {
            @Override
            public void before() {
                viewDelegate.loading();
            }

            @Override
            public void success(List<ChatModel> model) {
                viewDelegate.loadDialogDismiss();
                viewDelegate.showList(model);
            }

            @Override
            public void failed() {
                viewDelegate.loadDialogDismiss();
            }

            @Override
            public void errMsg(String msg) {
                viewDelegate.loadDialogDismiss();
            }
        });
    }




    @Override
    public void onClick(View view) {

    }
}