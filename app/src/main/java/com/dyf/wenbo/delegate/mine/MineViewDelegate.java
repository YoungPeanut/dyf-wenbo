package com.dyf.wenbo.delegate.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.android.volley.toolbox.NetworkImageView;
import com.dyf.wenbo.app.AppHper;
import com.dyf.wenbo.delegate.MenuViewDelegate;
import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.presenter.ActiveActivity;
import com.dyf.wenbo.scm.home.HomeScm;
import com.dyf.wenbo.scm.home.IHomeScm;
import com.dyf.wenbo.utils.ChatType;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;
import com.zhonghua.dileber.tools.SFont;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 16/1/17.
 */
public class MineViewDelegate extends MenuViewDelegate implements IMineView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_mine;
    }


    ListView mine_list;
    List<ChatModel> chatModels_ = new ArrayList<ChatModel>();
    MineListAdapter mineListAdapter;

    @Override
    public void initWidget() {
        super.initWidget();
        homeSrc = new HomeScm();
        mine_list = get(R.id.mine_list);
        setOnClickListener(this,R.id.mine_linear_old,R.id.mine_linear_ing);
        mineListAdapter = new MineListAdapter(getActivity());
        mine_list.setAdapter(mineListAdapter);
        onClick(get(R.id.mine_linear_ing));
        mine_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent();
                it.setClass(getActivity(), ActiveActivity.class);
                //it.putExtra(ArticleActivity.ARTICLE, chatModels_.get(i - 1));
                it.putExtra(ActiveActivity.ROOM_ID, chatModels_.get(i).getChathxid());
                it.putExtra(ActiveActivity.ID, chatModels_.get(i).getId());
                it.putExtra(ActiveActivity.ROOM_TYPE,3);
                getActivity().startActivity(it);
            }
        });
    }

    public void showList(List<ChatModel> chatModels){
        mineListAdapter.setChatModels(chatModels);
        mineListAdapter.notifyDataSetChanged();
    }

    TextView text_temp = null;
    SFont font_temp = null;
    LinearLayout linearLayout_temp = null;

    @Override
    public void onClick(View view) {
        TextView text = null;
        SFont font = null;
        LinearLayout linearLayout = null;
        int state = 0;
        super.onClick(view);
        switch (view.getId()){
            case R.id.mine_linear_old:
                text = get(R.id.mine_linear_old_text);
                font= get(R.id.mine_linear_old_font);
                linearLayout = get(R.id.mine_linear_old);
                state = -1;
                break;
            case R.id.mine_linear_ing:
                text = get(R.id.mine_linear_ing_text);
                font= get(R.id.mine_linear_ing_font);
                linearLayout = get(R.id.mine_linear_ing);
                state = 0;
                break;
        }
        if(text!=null&&font!=null) {
            if (text_temp != null && font_temp != null) {
                if (text_temp.equals(text)) {
                    return;
                }
                text_temp.setTextColor(getActivity().getResources().getColor(R.color.black));
                font_temp.setTextColor(getActivity().getResources().getColor(R.color.black));
                linearLayout_temp.setBackgroundColor(Color.TRANSPARENT);
            }
            text_temp = text;
            font_temp = font;
            linearLayout_temp = linearLayout;
            text.setTextColor(getActivity().getResources().getColor(R.color.red));
            font.setTextColor(getActivity().getResources().getColor(R.color.red));
            linearLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.gray_deep));
            clickList(state);
        }

    }

    IHomeScm homeSrc = null;
    private void clickList(int state){
        ChatModel chatModel = new ChatModel();
        chatModel.setChatstate(state);
        int userid = 0;
        if(AppHper.getAppUser()==null){
            userid = 1;
        }else{
            userid = AppHper.getAppUser().getId();
        }
        chatModel.setChatuserid(userid);
        homeSrc.findChat(null, chatModel, new INetListener<List<ChatModel>>() {
            @Override
            public void before() {
                loading();
            }

            @Override
            public void success(List<ChatModel> model) {
                loadDialogDismiss();
                showList(model);
            }

            @Override
            public void failed() {
                loadDialogDismiss();
            }

            @Override
            public void errMsg(String msg) {
                loadDialogDismiss();
            }
        });
    }


    public class MineListAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        class CellHolder{
            TextView list_mine_title;
            NetworkImageView list_mine_image;
            TextView list_mine_content;
        }


        public MineListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        public void setChatModels(List<ChatModel> chatModels) {
            chatModels_ = chatModels;
        }


        @Override
        public int getCount() {
            return chatModels_.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CellHolder _cellHolder = null;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_mine, null);
                _cellHolder = new CellHolder();
                _cellHolder.list_mine_title = (TextView) convertView.findViewById(R.id.list_mine_title);
                _cellHolder.list_mine_image = (NetworkImageView) convertView.findViewById(R.id.list_mine_image);
                _cellHolder.list_mine_image.setImageShapeType(NetworkImageView.CIRCLE_IMAGE);
                _cellHolder.list_mine_content = (TextView) convertView.findViewById(R.id.list_mine_content);
                convertView.setTag(_cellHolder);
            } else {
                _cellHolder = (CellHolder) convertView.getTag();
            }
            ChatModel chatModel = chatModels_.get(position);
            UserModel userModel = chatModel.getUser();
            if(userModel!=null){

                _cellHolder.list_mine_title.setText(chatModel.getChatname()+"-"+userModel.getUsername());
                _cellHolder.list_mine_image.setImageUrl(userModel.getUserimage(), R.drawable.icon);
            }
            _cellHolder.list_mine_content.setText(chatModel.getChatcontent());
            return convertView;
        }
    }

}