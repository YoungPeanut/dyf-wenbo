package com.dyf.wenbo.delegate.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.dyf.wenbo.delegate.MenuViewDelegate;
import com.dyf.wenbo.model.ChatModel;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.presenter.ActiveActivity;
import com.dyf.wenbo.utils.ChatType;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhonghua.dileber.mvp.view.AppViewDelegate;
import com.dyf.wenbo.R;
import com.zhonghua.dileber.tools.UTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 16/1/17.
 */
public class HomeViewDelegate extends MenuViewDelegate implements IHomeView{


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_home;
    }

    HomeListAdapter homeListAdapter;

    PullToRefreshListView home_list;
    EditText home_search_text;

    @Override
    public void initWidget() {
        super.initWidget();

        home_list = get(R.id.home_list);
        home_list.setMode(PullToRefreshBase.Mode.BOTH);
        homeListAdapter = new HomeListAdapter(getActivity());
        home_search_text = get(R.id.home_search_text);
        homeListAdapter.setChatModels(chatModels_);
        home_list.setAdapter(homeListAdapter);
        home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent();
                it.setClass(getActivity(), ActiveActivity.class);
                //it.putExtra(ArticleActivity.ARTICLE, chatModels_.get(i - 1));
                it.putExtra(ActiveActivity.ROOM_ID, chatModels_.get(i - 1).getChathxid());
                it.putExtra(ActiveActivity.ID, chatModels_.get(i-1).getId());
                it.putExtra(ActiveActivity.ROOM_TYPE, 3);
                getActivity().startActivity(it);

            }
        });
    }

    public void cleraSearch(){
        home_search_text.setText("");
    }

    List<ChatModel> chatModels_ = new ArrayList<ChatModel>();

    public void showList(List<ChatModel> chatModels){

        homeListAdapter.setChatModels(chatModels);
        homeListAdapter.notifyDataSetChanged();
    }


    public void addList(List<ChatModel> chatModels){
        homeListAdapter.addChatModel(chatModels);
        homeListAdapter.notifyDataSetChanged();
    }

    public class HomeListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        class CellHolder{
            TextView list_home_name;
            NetworkImageView home_image;
            TextView list_home_message;
            TextView list_home_content;
            TextView list_home_type;
        }


        public HomeListAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        public void setChatModels(List<ChatModel> chatModels) {
            chatModels_ = chatModels;
        }


        public void addChatModel(List<ChatModel> chatModels){

            chatModels_.addAll(chatModels);

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
                convertView = mInflater.inflate(R.layout.list_home, null);
                _cellHolder = new CellHolder();
                _cellHolder.list_home_content = (TextView) convertView.findViewById(R.id.list_home_content);
                _cellHolder.list_home_name = (TextView) convertView.findViewById(R.id.list_home_name);
                _cellHolder.list_home_type = (TextView) convertView.findViewById(R.id.list_home_type);
                _cellHolder.list_home_message = (TextView) convertView.findViewById(R.id.list_home_message);
                _cellHolder.home_image = (NetworkImageView) convertView.findViewById(R.id.home_image);
                _cellHolder.home_image.setImageShapeType(NetworkImageView.CIRCLE_IMAGE);
                convertView.setTag(_cellHolder);
            } else {
                _cellHolder = (CellHolder) convertView.getTag();
            }
            ChatModel chatModel = chatModels_.get(position);
            _cellHolder.list_home_content.setText(chatModel.getChatcontent());

            _cellHolder.list_home_message.setText(chatModel.getOther());
            _cellHolder.list_home_type.setText(ChatType.getType(chatModel.getChattype(),1));
            UserModel userModel = chatModel.getUser();
            if(userModel!=null){
                _cellHolder.home_image.setImageUrl(userModel.getUserimage(), R.drawable.icon);
                _cellHolder.list_home_name.setText(userModel.getUsername());
            }

            return convertView;
        }
    }
    
}