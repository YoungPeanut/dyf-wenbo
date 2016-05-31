package com.dyf.wenbo.presenter;

import android.os.Bundle;
import android.view.View;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.dyf.wenbo.R;
import com.dyf.wenbo.delegate.MainViewDelegate;
import com.dyf.wenbo.scm.IMainScm;
import com.dyf.wenbo.scm.MainScm;

public class MainActivity extends ActivityPresenter<MainViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMainScm mainSrc = new MainScm();

    }

    @Override
    protected Class<MainViewDelegate> getDelegateClass() {
        return MainViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    public void onClick(View view) {

    }
}