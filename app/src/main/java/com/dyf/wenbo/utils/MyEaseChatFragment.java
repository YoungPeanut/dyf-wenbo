package com.dyf.wenbo.utils;

import android.view.View;
import android.widget.TextView;
import com.dyf.wenbo.R;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.EaseTitleBar;

public class MyEaseChatFragment extends EaseChatFragment{
    EaseTitleBar easeTitleBar;
    TextView titleView;
    @Override
    protected void initView() {
        super.initView();
        easeTitleBar = (EaseTitleBar) getView().findViewById(com.hyphenate.easeui.R.id.title_bar);
        easeTitleBar.setBackgroundColor(getResources().getColor(R.color.white));
        easeTitleBar.getRightLayout().setVisibility(View.GONE);
        titleView = (TextView) easeTitleBar.findViewById(com.hyphenate.easeui.R.id.title);
        titleView.setTextColor(getResources().getColor(R.color.black));
    }
}
