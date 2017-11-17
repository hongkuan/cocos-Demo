package com.example.hongkuan.cooking.ui.stepsui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.mode.entity.CookingStep;

/**
 * Created by hongk on 2017/11/14.
 */

public class StepsListAdapter extends BaseAdapter {

    private final CookingStep[] mCookingSteps;
    private final LayoutInflater mLayoutInflater;

    public StepsListAdapter(Context context, CookingStep[] cookingSteps){
        this.mCookingSteps = cookingSteps;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mCookingSteps.length;
    }

    @Override
    public Object getItem(int position) {
        return mCookingSteps[position];
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

        Glide.with(convertView.getContext())
                .load(mCookingSteps[position].getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mStepImg);
        holder.mStepText.setText(mCookingSteps[position].getStep());

        return convertView;
    }

    private View getItemView(ViewGroup parent){
        return mLayoutInflater.inflate(R.layout.steps_ui_list_item, parent, false);
    }

    private static class ViewHolder{
        private ImageView mStepImg;
        private TextView mStepText;

        public void initViewHolder(View view){
            this.mStepImg = (ImageView) view.findViewById(R.id.steps_ui_list_item_icon);
            this.mStepText = (TextView) view.findViewById(R.id.steps_ui_list_item_step);
        }
    }
}
