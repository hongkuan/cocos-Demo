package com.example.hongkuan.cooking.ui.categoryui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.mode.entity.Children;

/**
 * Created by hongk on 2017/11/13.
 */

public class CategoryGridAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private Children[] mChildrens;

    public CategoryGridAdapter(LayoutInflater layoutInflater, Children[] childrens){
        this.mInflater = layoutInflater;
        this.mChildrens = childrens;
    }

    public void update(Children[] childrens){
        this.mChildrens = childrens;
    }

    @Override
    public int getCount() {
        return mChildrens.length;
    }

    @Override
    public Object getItem(int position) {
        return mChildrens[position];
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
            holder = ( ViewHolder) convertView.getTag();
        }
        holder.mCategoryNameTextView.setText(mChildrens[position].getName());
        return convertView;
    }

    private View getItemView(ViewGroup parent){
        return mInflater.inflate(R.layout.category_ui_grid_item, parent, false);
    }

    private static class ViewHolder{
        private TextView mCategoryNameTextView;

        public void initViewHolder(View view){
            this.mCategoryNameTextView = (TextView) view.findViewById(R.id.category_ui_grid_item_text_view);
        }
    }
}
