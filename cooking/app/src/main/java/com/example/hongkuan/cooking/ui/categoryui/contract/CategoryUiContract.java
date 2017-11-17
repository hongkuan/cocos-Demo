package com.example.hongkuan.cooking.ui.categoryui.contract;

import android.content.Context;
import android.widget.BaseAdapter;

import com.example.hongkuan.cooking.base.mvp.BaseView;
import com.example.hongkuan.cooking.base.mvp.BasePresenter;

/**
 * Created by hongk on 2017/11/8.
 */

public interface CategoryUiContract {

    interface View extends BaseView<Presenter>{
        void setAdapter(BaseAdapter adapter);
        void showToast(String msg);
        /**
         *
         * @param cid   int	    是	标签ID 分类标签id
         * @param pn    string	否	数据返回起始下标，默认0
         * @param rn    string	否	数据返回条数，最大30，默认10
         * @return
         */
        void startMainUi(int cid, String pn, String rn);
        void onMenuItemClick(int cid, String pn, String rn);
    }

    interface Presenter extends BasePresenter{
        void initAdapter(Context context);
        void onDestroy();
    }
}
