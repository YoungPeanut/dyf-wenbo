package com.dyf.wenbo.delegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import com.dyf.wenbo.R;
import com.dyf.wenbo.presenter.find.FindActivity;
import com.dyf.wenbo.presenter.home.HomeActivity;
import com.dyf.wenbo.presenter.mine.MineActivity;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.SFont;
import com.zhonghua.dileber.tools.SLog;


public abstract class MenuViewDelegate extends TitleViewDelegate {

    @Override
    public void initWidget() {
        super.initWidget();
        initMenu();
        setOnClickListener(this, R.id.all_find, R.id.all_index, R.id.all_me);
    }

    private void initMenu(){
        SFont font = null;
        TextView text = null;
        if(getActivity() instanceof FindActivity){
            font = get(R.id.find_font);
            text = get(R.id.find_text);
        }else if(getActivity() instanceof HomeActivity){
            font = get(R.id.index_font);
            text = get(R.id.index_text);
        }else if(getActivity() instanceof MineActivity){
            font = get(R.id.me_font);
            text = get(R.id.me_text);
        }
        if(font!=null&&text!=null){
            font.setTextColor(getActivity().getResources().getColor(R.color.menu_text_color_two));
            text.setTextColor(getActivity().getResources().getColor(R.color.menu_text_color_two));
        }

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent it = new Intent();
        boolean jump = true;
        switch (view.getId()){
            case R.id.all_find:
                it.setClass(getActivity(), FindActivity.class);
                break;
            case R.id.all_index:
                it.setClass(getActivity(), HomeActivity.class);
                break;
            case R.id.all_me:
                it.setClass(getActivity(), MineActivity.class);
                break;
            default:
                jump = false;
        }
        if(jump){
            getActivity().startActivity(it);
            getActivity().overridePendingTransition(0, 0);
            if(getActivity().getComponentName().compareTo(it.getComponent())!=0){
                getActivity().finish();
            }
        }


    }

}
