package com.example.hongkuan.cooking.ui.categoryui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.mode.entity.Children;
import com.example.hongkuan.cooking.mode.entity.ParentArray;
import com.example.hongkuan.cooking.ui.categoryui.view.widget.ChildrenGridView;

import java.util.List;

/**
 * Created by hongk on 2017/11/13.
 */

public class CategoryListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private List<ParentArray> mParents;
    private final OnGridViewItemClickCallback mOnGridViewItemClickCallback;

    public CategoryListAdapter(Context context, 
                               List<ParentArray> parents,
                               OnGridViewItemClickCallback callback){
        this.mInflater = LayoutInflater.from(context);
        this.mParents = parents;
        this.mOnGridViewItemClickCallback = callback;
    }

    public void updateData(List<ParentArray> parents){
        //mParents = parents;
        mParents.addAll(parents);
    }

    @Override
    public int getCount() {
        return mParents.size();
    }

    @Override
    public Object getItem(int position) {
        return mParents.get(position);
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

            // TODO 此处adapter重用的原则
            BaseAdapter adapter = new CategoryGridAdapter(mInflater, mParents.get(position).getList());
            holder.mCategoryGridView.setAdapter(adapter);

            convertView = view;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            ((CategoryGridAdapter)holder.mCategoryGridView.getAdapter()).update(mParents.get(position).getList());
            ((CategoryGridAdapter) holder.mCategoryGridView.getAdapter()).notifyDataSetChanged();
        }

        // TODO 此处adapter会创建多个 没达到重用的原则
        /*BaseAdapter adapter = new CategoryGridAdapter(mInflater, mParents.get(position).getList());
        holder.mCategoryGridView.setAdapter(adapter);*/

        holder.mCategoryTitleNameTextView.setText(mParents.get(position).getName());
        holder.setGridViewItemClick(mParents.get(position), mOnGridViewItemClickCallback);
        return convertView;
    }

    private View getItemView(ViewGroup parent){
        return mInflater.inflate(R.layout.category_ui_list_item, parent, false);
    }

    private static class ViewHolder{
        private TextView mCategoryTitleNameTextView;
        private GridView mCategoryGridView;

        public void initViewHolder(View view){
            this.mCategoryTitleNameTextView = (TextView) view.findViewById(R.id.category_ui_list_item_text_view);
            this.mCategoryGridView = (ChildrenGridView) view.findViewById(R.id.category_ui_list_item_grid_view);
        }

        public void setGridViewItemClick(final ParentArray parentArray, final OnGridViewItemClickCallback callback){
            if (this.mCategoryGridView != null && callback != null) {
                this.mCategoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Children children = parentArray.getList()[position];
                        callback.onGridViewItemClick(children);
                    }
                });
            }


        }
    }

    public interface OnGridViewItemClickCallback {
        void onGridViewItemClick(Children children);
    }
}
