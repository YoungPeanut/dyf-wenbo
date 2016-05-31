package com.dyf.wenbo.delegate.mine;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;

/**
 * Created by  on 16/1/17.
 */
public class NowFragmentViewDelegate extends AppViewDelegate implements INowFragmentView{


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_now;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}