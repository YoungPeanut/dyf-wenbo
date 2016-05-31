package com.dyf.wenbo.delegate;

import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;

/**
 * Created by  on 16/1/17.
 */
public class MainViewDelegate extends AppViewDelegate implements IMainView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

}