package com.dyf.wenbo.delegate;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dyf.wenbo.R;
import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.presenter.LoginActivity;
import com.dyf.wenbo.presenter.find.FindActivity;
import com.dyf.wenbo.presenter.home.HomeActivity;
import com.dyf.wenbo.presenter.mine.MineActivity;
import com.dyf.wenbo.utils.HuanXinUtil;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.zhonghua.dileber.tools.SFont;
import com.zhonghua.dileber.view.dialog.DialogLinstener;

public abstract class TitleViewDelegate extends AppViewDelegate implements View.OnClickListener{


    @Override
    public void initWidget() {
        super.initWidget();
        TextView title_title = get(R.id.title_title);
        String title = "";
        if(getActivity() instanceof FindActivity){
            title = "文播广场";
        }else if(getActivity() instanceof HomeActivity){
            title = "文播－文字直播首页";
        }else if(getActivity() instanceof MineActivity){
            title = "我的房间";
        }
        title_title.setText(title);
        setOnClickListener(this, R.id.title_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_btn:
                btnAllPageTitlePopmenuOnClick(view);
                break;
        }
    }

    WindowManager wm;

    private void btnAllPageTitlePopmenuOnClick(View view){
        wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        View popView = getActivity().getLayoutInflater().inflate(
                R.layout.pop_menu, null);
        final PopupWindow popupWindow = new PopupWindow(popView,
                width / 2, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0],
                location[1] + 53);

        Button pop_exit = (Button) popView.findViewById(R.id.pop_exit);

        Button pop_me = (Button) popView.findViewById(R.id.pop_me);

        pop_exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String title = "";
                if(AppHper.getAppUser()!=null){
                    title = "您确定要退出登录么";
                }
                dialogOk(title, new DialogLinstener() {
                    @Override
                    public void confirm(Dialog dialog) {
                        AppHper.clearAppUser();
                        Intent it = new Intent();
                        it.setClass(getActivity(), LoginActivity.class);
                        getActivity().startActivity(it);
                        getActivity().finish();
                        dialog.dismiss();
                        HuanXinUtil.logOut(null);
                    }

                    @Override
                    public void cancel(Dialog dialog) {
                        dialog.dismiss();
                    }
                });

                popupWindow.dismiss();
            }
        });

        pop_me.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showAlert(null, "DrCoSu工作室\n董艺菲");
                popupWindow.dismiss();



            }
        });


    }
}
