package com.dyf.wenbo.presenter.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.presenter.utils.CarouselFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.dyf.wenbo.R;
import com.dyf.wenbo.delegate.home.HomeViewDelegate;
import com.dyf.wenbo.scm.home.IHomeScm;
import com.dyf.wenbo.scm.home.HomeScm;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.annotation.CloseTitle;

import java.util.List;

@CloseTitle
public class HomeActivity extends ActivityPresenter<HomeViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(R.id.home_carousel, new CarouselFragment())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showList();
    }

    @Override
    protected Class<HomeViewDelegate> getDelegateClass() {
        return HomeViewDelegate.class;
    }
    IHomeScm homeSrc = null;
    PullToRefreshListView home_list;
    String like = "";

    EditText home_search_text;
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        homeSrc = new HomeScm();
        home_list = viewDelegate.get(R.id.home_list);
        home_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = android.text.format.DateUtils.formatDateTime(
                        HomeActivity.this.getApplicationContext(),
                        System.currentTimeMillis(),
                        android.text.format.DateUtils.FORMAT_SHOW_TIME
                                | android.text.format.DateUtils.FORMAT_SHOW_DATE
                                | android.text.format.DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(label);
                //home_list.onRefreshComplete();
                like = "";
                viewDelegate.cleraSearch();
                showList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                home_list.getLoadingLayoutProxy(false, true)
                        .setLastUpdatedLabel("上拉加载");
                home_list.getLoadingLayoutProxy(false, true)
                        .setPullLabel("");
                home_list.getLoadingLayoutProxy(false, true)
                        .setRefreshingLabel("正在加载...");
                home_list.getLoadingLayoutProxy(false, true)
                        .setReleaseLabel("放开以加载");
                showList();
            }
        });

        home_search_text = viewDelegate.get(R.id.home_search_text);
        home_search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    like = home_search_text.getText().toString();
                    showList();
                    return true;
                }
                return false;
            }
        });

    }

    private void showList() {
        ChatModel chatModel = new ChatModel();
        chatModel.setChatstate(0);
        homeSrc.findChat(like, chatModel, new INetListener<List<ChatModel>>() {
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
                home_list.onRefreshComplete();
            }

            @Override
            public void errMsg(String msg) {
                viewDelegate.loadDialogDismiss();
                home_list.onRefreshComplete();
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}