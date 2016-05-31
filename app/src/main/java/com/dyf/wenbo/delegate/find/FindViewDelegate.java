package com.dyf.wenbo.delegate.find;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.dyf.wenbo.delegate.MenuViewDelegate;
import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.presenter.ActiveActivity;
import com.dyf.wenbo.presenter.find.FindActivity;
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
public class FindViewDelegate extends MenuViewDelegate implements IFindView{


    ListView find_list;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_find;
    }


    FindListAdapter findListAdapter;
    List<ChatModel> chatModels_ = new ArrayList<ChatModel>();
    @Override
    public void initWidget() {
        super.initWidget();
        homeSrc = new HomeScm();
        find_list = get(R.id.find_list);
        findListAdapter = new FindListAdapter(getActivity());
        findListAdapter.setChatModels(chatModels_);
        find_list.setAdapter(findListAdapter);
        setOnClickListener(this,R.id.find_shenhuo,R.id.find_siashi,R.id.find_qinggan,R.id.find_jineng,R.id.find_zatan);
    }

    public void showList(List<ChatModel> chatModels){
        findListAdapter.setChatModels(chatModels);
        findListAdapter.notifyDataSetChanged();
    }

    public class FindListAdapter extends BaseAdapter{
        private LayoutInflater mInflater;

        class CellHolder{
            View right;
            View left;
            TextView list_find_type_left;
            NetworkImageView find_image_left;
            TextView find_title_left;
            TextView list_find_type_right;
            NetworkImageView find_image_right;
            TextView find_title_right;
        }

        public FindListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        public void setChatModels(List<ChatModel> chatModels) {
            chatModels_ = chatModels;
        }


        @Override
        public int getCount() {
            int temp = chatModels_.size();
            int count = temp/2+1;
            return count;
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
                convertView = mInflater.inflate(R.layout.list_find_two, null);
                _cellHolder = new CellHolder();

                _cellHolder.left = convertView.findViewById(R.id.find_list_left);
                _cellHolder.right = convertView.findViewById(R.id.find_list_right);
                _cellHolder.list_find_type_left = (TextView) _cellHolder.left.findViewById(R.id.list_find_type);
                _cellHolder.find_image_left = (NetworkImageView) _cellHolder.left.findViewById(R.id.find_image);
                _cellHolder.find_title_left = (TextView) _cellHolder.left.findViewById(R.id.find_title);
                _cellHolder.list_find_type_right = (TextView) _cellHolder.right.findViewById(R.id.list_find_type);
                _cellHolder.find_image_right = (NetworkImageView) _cellHolder.right.findViewById(R.id.find_image);
                _cellHolder.find_title_right = (TextView) _cellHolder.right.findViewById(R.id.find_title);
                convertView.setTag(_cellHolder);
            } else {
                _cellHolder = (CellHolder) convertView.getTag();
            }
            int mypos = position*2;
            if(mypos>=chatModels_.size()){
                _cellHolder.left.setVisibility(View.GONE);
                _cellHolder.right.setVisibility(View.GONE);
                return convertView;
            }else {
                _cellHolder.left.setVisibility(View.VISIBLE);
                _cellHolder.right.setVisibility(View.VISIBLE);
            }

            final int i = position;

            final ChatModel chatModel = chatModels_.get(mypos);
            if(chatModel!=null){
                _cellHolder.list_find_type_left.setText(ChatType.getType(chatModel.getChattype(), 1));
                _cellHolder.find_image_left.setImageUrl(chatModel.getChatimage(), R.drawable.icon);
                _cellHolder.find_title_left.setText(chatModel.getChatname());
                _cellHolder.left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it = new Intent();
                        it.setClass(getActivity(), ActiveActivity.class);
                        //it.putExtra(ArticleActivity.ARTICLE, chatModels_.get(i - 1));
                        it.putExtra(ActiveActivity.ROOM_ID,chatModel.getChathxid());
                        it.putExtra(ActiveActivity.ID, chatModel.getId());
                        it.putExtra(ActiveActivity.ROOM_TYPE, 3);
                        getActivity().startActivity(it);
                    }
                });
            }else{
                _cellHolder.left.setVisibility(View.GONE);
            }

            if(mypos+1>=chatModels_.size()){
                _cellHolder.right.setVisibility(View.GONE);
            }else{
                final ChatModel chatModel2 = chatModels_.get(mypos+1);
                if(chatModel!=null){
                    _cellHolder.list_find_type_right.setText(ChatType.getType(chatModel2.getChattype(), 1));
                    _cellHolder.find_image_right.setImageUrl(chatModel2.getChatimage(), R.drawable.icon);
                    _cellHolder.find_title_right.setText(chatModel2.getChatname());
                    _cellHolder.right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent it = new Intent();
                            it.setClass(getActivity(), ActiveActivity.class);
                            //it.putExtra(ArticleActivity.ARTICLE, chatModels_.get(i - 1));
                            it.putExtra(ActiveActivity.ROOM_ID,chatModel2.getChathxid());
                            it.putExtra(ActiveActivity.ID, chatModel2.getId());
                            it.putExtra(ActiveActivity.ROOM_TYPE, 3);
                            getActivity().startActivity(it);
                        }
                    });
                }else{
                    _cellHolder.right.setVisibility(View.GONE);
                }
            }

            return convertView;
        }


    }

    TextView text_temp = null;
    SFont font_temp = null;


    @Override
    public void onClick(View view) {
        TextView text = null;
        SFont font = null;
        int type = 0;
        super.onClick(view);
        switch (view.getId()){
            case R.id.find_shenhuo:
                text = get(R.id.find_shenhuo_text);
                font= get(R.id.find_shenhuo_font);
                type = 0;
                break;
            case R.id.find_siashi:
                text = get(R.id.find_siashi_text);
                font= get(R.id.find_siashi_font);
                type = 1;
                break;
            case R.id.find_qinggan:
                text = get(R.id.find_qinggan_text);
                font= get(R.id.find_qinggan_font);
                type = 2;
                break;
            case R.id.find_jineng:
                text = get(R.id.find_jineng_text);
                font= get(R.id.find_jineng_font);
                type = 3;
                break;
            case R.id.find_zatan:
                text = get(R.id.find_zatan_text);
                font= get(R.id.find_zatan_font);
                type = 4;
                break;
        }
        if(text!=null&&font!=null) {
            if (text_temp != null && font_temp != null) {
                if (text_temp.equals(text)) {
                    return;
                }
                text_temp.setTextColor(getActivity().getResources().getColor(R.color.black));
                font_temp.setTextColor(getActivity().getResources().getColor(R.color.black));
            }
            text_temp = text;
            font_temp = font;
            text.setTextColor(getActivity().getResources().getColor(R.color.red));
            font.setTextColor(getActivity().getResources().getColor(R.color.red));
            clickList(type);

        }

    }
    IHomeScm homeSrc = null;
    private void clickList(int type){

        ChatModel chatModel = new ChatModel();
        chatModel.setChattype(type);
        chatModel.setChatstate(0);
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
}