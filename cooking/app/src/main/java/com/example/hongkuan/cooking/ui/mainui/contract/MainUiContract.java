package com.example.hongkuan.cooking.ui.mainui.contract;

import android.content.Context;
import android.widget.BaseAdapter;

import com.example.hongkuan.cooking.base.mvp.BaseView;
import com.example.hongkuan.cooking.base.mvp.BasePresenter;

/**
 * Created by hongk on 2017/11/8.
 */

public interface MainUiContract {

    interface View extends BaseView<Presenter>{
        void setAdapter(BaseAdapter adapter);
        void startStepsUi(int position);
    }

    interface Presenter extends BasePresenter{
        void initAdapter(Context context, int cid);
        void onListOnItemClick(android.view.View view, int position, long id);
        void updateListData(int cid, String pn, String rn);
    }
}
