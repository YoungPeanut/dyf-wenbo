package com.dyf.wenbo.delegate.mine;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;

/**
 * Created by  on 16/1/17.
 */
public class OldFragmentViewDelegate extends AppViewDelegate implements IOldFragmentView{


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_old;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}