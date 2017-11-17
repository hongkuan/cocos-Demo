package com.example.hongkuan.cooking.ui.mainui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.mode.entity.Menu;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by hongk on 2017/11/14.
 */

public class MainUiListAdapter extends BaseAdapter {
    private final static String TAG = "MainUiListAdapter";
    private final LayoutInflater mInflater;
    private final List<Menu> mMenuList;

    public MainUiListAdapter(Context context, List<Menu> menuList){
        this.mMenuList = menuList;
        this.mInflater = LayoutInflater.from(context);
        Glide.with(context);
    }

    public void updateData(List<Menu> menuList){
        this.mMenuList.clear();
        this.mMenuList.addAll(menuList);
    }
    
    @Override
    public int getCount() {
        return mMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            View view = getItemView(parent);
            holder.initViewHolder(view);
            convertView = view;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i(TAG, "getView: menu:" +mMenuList.get(position));
        holder.mMenuTitle.setText(mMenuList.get(position).getTitle());
        holder.mMenuImtro.setText(mMenuList.get(position).getImtro());
        Glide.with(convertView.getContext())
                .load(mMenuList.get(position).getOneAlbum(0))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mMeneIcon);

        return convertView;
    }

    private View getItemView(ViewGroup parent){
        return mInflater.inflate(R.layout.main_ui_list_item, parent, false);
    }

    private static class ViewHolder{
        private ImageView mMeneIcon;
        private TextView mMenuTitle;
        private TextView mMenuImtro;

        public void initViewHolder(View view){
            this.mMeneIcon = (ImageView) view.findViewById(R.id.main_ui_list_item_icon);
            this.mMenuTitle= (TextView) view.findViewById(R.id.main_ui_list_item_title);
            this.mMenuImtro= (TextView) view.findViewById(R.id.main_ui_list_item_imtro);
        }
    }
}
