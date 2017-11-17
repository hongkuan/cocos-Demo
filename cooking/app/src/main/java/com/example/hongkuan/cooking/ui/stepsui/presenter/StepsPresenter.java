package com.example.hongkuan.cooking.ui.stepsui.presenter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.mode.DataHelper;
import com.example.hongkuan.cooking.ui.stepsui.adapter.StepsListAdapter;
import com.example.hongkuan.cooking.ui.stepsui.contract.StepsUiContract;

/**
 * Created by hongk on 2017/11/14.
 */

public class StepsPresenter implements StepsUiContract.Presenter {
    private final static String TAG = "StepsPresenter";
    private final StepsUiContract.View mView;
    private StepsListAdapter mStepListAdapter;

    public StepsPresenter(StepsUiContract.View view){
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void initAdapter(Context context, int position) {
        this.mStepListAdapter = new StepsListAdapter(context, DataHelper.getInstance().getMenu(position).getSteps());
        this.mView.setAdapter(this.mStepListAdapter);
    }

    @Override
    public void initViewData(Context context, int position) {
        this.mView.getIngredientsView().setText(DataHelper.getInstance().getMenu(position).getIngredients());
        this.mView.getBurdenView().setText(DataHelper.getInstance().getMenu(position).getBurden());
        Glide.with(context)
                .load(DataHelper.getInstance().getMenu(position).getOneAlbum(0))
                .placeholder(R.mipmap.ic_launcher)
                .into(this.mView.getMenuIconView());
    }

    @Override
    public void leftButtonClick() {
        this.mView.backUi();
    }

    @Override
    public void rightButtonClick() {
        this.mView.showToast("收藏功能");
    }
}
