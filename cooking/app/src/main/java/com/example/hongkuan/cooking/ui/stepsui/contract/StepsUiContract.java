package com.example.hongkuan.cooking.ui.stepsui.contract;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongkuan.cooking.base.mvp.BasePresenter;
import com.example.hongkuan.cooking.base.mvp.BaseView;

/**
 * Created by hongk on 2017/11/14.
 */

public interface StepsUiContract {

    interface View extends BaseView<Presenter> {
        void setAdapter(BaseAdapter adapter);
        void showToast(String msg);
        void backUi();
        TextView getIngredientsView();
        TextView getBurdenView();
        ImageView getMenuIconView();
    }

    interface Presenter extends BasePresenter {
        void initAdapter(Context context, int position);
        void initViewData(Context context, int position);
        void leftButtonClick();
        void rightButtonClick();
    }

}
