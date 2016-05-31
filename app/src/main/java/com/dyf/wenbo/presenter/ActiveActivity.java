package com.dyf.wenbo.presenter;

import android.os.Bundle;
import android.view.View;
import com.dyf.wenbo.model.TypeWrapper;
import com.dyf.wenbo.utils.MyEaseChatFragment;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.dyf.wenbo.R;
import com.dyf.wenbo.delegate.ActiveViewDelegate;
import com.dyf.wenbo.scm.IActiveScm;
import com.dyf.wenbo.scm.ActiveScm;
import com.zhonghua.dileber.mvp.presenter.FragmentActivityPresenter;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.annotation.CloseTitle;

@CloseTitle
public class ActiveActivity extends FragmentActivityPresenter<ActiveViewDelegate> {
    private EaseChatFragment chatFragment;
    public static final String ROOM_ID = "userId";
    public static final String ID = "id";
    public static final String ROOM_TYPE = "chatType";
    String roomId;
    Integer id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Class<ActiveViewDelegate> getDelegateClass() {
        return ActiveViewDelegate.class;
    }

    IActiveScm activeSrc ;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        roomId = getIntent().getExtras().getString(ROOM_ID);
        id = getIntent().getExtras().getInt(ID);
        chatFragment = new MyEaseChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.active_layout, chatFragment).commit();
        activeSrc = new ActiveScm();
        activeSrc.getType(id, new INetListener<TypeWrapper>() {
            @Override
            public void before() {

            }

            @Override
            public void success(TypeWrapper model) {
                viewDelegate.setColor(model.getCollect(),1);
                viewDelegate.setColor(model.getGood(),0);
            }

            @Override
            public void failed() {

            }

            @Override
            public void errMsg(String msg) {

            }
        });

        viewDelegate.get(R.id.active_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeSrc.setType(id, 0, new INetListener<Integer>() {
                    @Override
                    public void before() {

                    }

                    @Override
                    public void success(Integer model) {
                        viewDelegate.setColor(model,0);
                    }

                    @Override
                    public void failed() {

                    }

                    @Override
                    public void errMsg(String msg) {

                    }
                });
            }
        });
        viewDelegate.get(R.id.active_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeSrc.setType(id,1, new INetListener<Integer>() {
                    @Override
                    public void before() {

                    }

                    @Override
                    public void success(Integer model) {
                        viewDelegate.setColor(model,1);
                    }

                    @Override
                    public void failed() {

                    }

                    @Override
                    public void errMsg(String msg) {

                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}