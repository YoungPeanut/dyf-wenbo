package com.dyf.wenbo.delegate;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.dyf.wenbo.scm.ActiveScm;
import com.dyf.wenbo.scm.IActiveScm;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;
import com.zhonghua.dileber.tools.SFont;

/**
 * Created by  on 16/1/17.
 */
public class ActiveViewDelegate extends AppViewDelegate implements IActiveView,View.OnClickListener
{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_active;
    }



    TextView active_good_text,active_collect_text;
    SFont active_good_font,active_collect_font;
    @Override
    public void initWidget() {
        super.initWidget();
        active_good_text = get(R.id.active_good_text);
        active_good_font= get(R.id.active_good_font);
        active_collect_text = get(R.id.active_collect_text);
        active_collect_font= get(R.id.active_collect_font);
        setOnClickListener(this,R.id.active_good,R.id.active_collect,R.id.active_money,R.id.active_share,R.id.active_common);
    }


    public void setColor(int temp,int type){
        TextView textView;
        SFont sFont;

        if(type==0){
            textView = active_good_text;
            sFont = active_good_font;
        }else{
            textView = active_collect_text;
            sFont = active_collect_font;
        }

        if(temp==1){
            textView.setTextColor(getActivity().getResources().getColor(R.color.red));
            sFont.setTextColor(getActivity().getResources().getColor(R.color.red));
        }else{
            textView.setTextColor(getActivity().getResources().getColor(R.color.black));
            sFont.setTextColor(getActivity().getResources().getColor(R.color.black));
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.active_money:
                toast("打赏成功",Toast.LENGTH_SHORT);
                break;
            case R.id.active_share:
                toast("分享成功",Toast.LENGTH_SHORT);
                break;
            case R.id.active_common:
                toast("直接在上面评论",Toast.LENGTH_SHORT);
                break;
        }
    }




}