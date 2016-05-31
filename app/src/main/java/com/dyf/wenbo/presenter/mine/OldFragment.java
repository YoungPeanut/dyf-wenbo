package com.dyf.wenbo.presenter.mine;

import android.view.View;
import com.zhonghua.dileber.mvp.presenter.FragmentPresenter;
import com.dyf.wenbo.delegate.mine.OldFragmentViewDelegate;
import com.dyf.wenbo.R;
import com.dyf.wenbo.scm.mine.IOldFragmentScm;
import com.dyf.wenbo.scm.mine.OldFragmentScm;

public class OldFragment extends FragmentPresenter<OldFragmentViewDelegate> {
    @Override
    protected Class<OldFragmentViewDelegate> getDelegateClass() {
        return OldFragmentViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }

    @Override
    public void onClick(View v) {
    }
}
