package com.dyf.wenbo.presenter.mine;

import android.view.View;
import com.zhonghua.dileber.mvp.presenter.FragmentPresenter;
import com.dyf.wenbo.delegate.mine.NowFragmentViewDelegate;
import com.dyf.wenbo.R;
import com.dyf.wenbo.scm.mine.INowFragmentScm;
import com.dyf.wenbo.scm.mine.NowFragmentScm;

public class NowFragment extends FragmentPresenter<NowFragmentViewDelegate> {
    @Override
    protected Class<NowFragmentViewDelegate> getDelegateClass() {
        return NowFragmentViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }

    @Override
    public void onClick(View v) {
    }
}
