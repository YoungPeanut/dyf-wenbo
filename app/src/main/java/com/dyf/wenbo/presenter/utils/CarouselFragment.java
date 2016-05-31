package com.dyf.wenbo.presenter.utils;

import android.view.View;
import com.zhonghua.dileber.mvp.presenter.FragmentPresenter;
import com.dyf.wenbo.delegate.utils.CarouselFragmentViewDelegate;
import com.dyf.wenbo.R;
import com.dyf.wenbo.scm.utils.ICarouselFragmentScm;
import com.dyf.wenbo.scm.utils.CarouselFragmentScm;

public class CarouselFragment extends FragmentPresenter<CarouselFragmentViewDelegate> {
    @Override
    protected Class<CarouselFragmentViewDelegate> getDelegateClass() {
        return CarouselFragmentViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.imageAdapter(null);
    }

    @Override
    public void onClick(View v) {

    }
}
