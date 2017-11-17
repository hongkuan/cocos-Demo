package com.example.hongkuan.cooking.ui.mainui.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.hongkuan.cooking.mode.DataHelper;
import com.example.hongkuan.cooking.mode.entity.Menu;
import com.example.hongkuan.cooking.mode.entity.ParentArray;
import com.example.hongkuan.cooking.ui.mainui.adapter.MainUiListAdapter;
import com.example.hongkuan.cooking.ui.mainui.contract.MainUiContract;
import com.example.hongkuan.cooking.workthread.DataAsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongk on 2017/11/10.
 */

public class MainUiPresenter implements MainUiContract.Presenter {
    private final static String TAG = "MainUiPresenter";
    private final MainUiContract.View mView;
    private MainUiListAdapter mListAdapter;
    private DataAsyncTask.OnResultCallback mOnResultCallback;

    public MainUiPresenter(MainUiContract.View view){
        Log.i(TAG, "MainUiPresenter: ");
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        this.mOnResultCallback = new DataAsyncTask.OnResultCallback() {
            @Override
            public void onResultCallback(Object results) {
                if (mListAdapter != null){
                    mListAdapter.updateData((List<Menu>)results);
                    mListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelledCallback(Object results) {

            }
        };
    }


    @Override
    public void onDestroy() {
        if (this.mOnResultCallback != null){
            this.mOnResultCallback = null;
        }
        if (this.mListAdapter != null){
            this.mListAdapter = null;
        }
    }

    @Override
    public void initAdapter(Context context, int cid) {
        // TODO CID pn rn
        DataHelper.getInstance().getListMenu(mOnResultCallback, cid, "0", "10");

        this.mListAdapter = new MainUiListAdapter(context, new ArrayList<Menu>());
        this.mView.setAdapter(mListAdapter);
    }

    @Override
    public void onListOnItemClick(View view, int position, long id) {
        this.mView.startStepsUi(position);
    }

    @Override
    public void updateListData(int cid, String pn, String rn) {
        DataHelper.getInstance().getListMenu(mOnResultCallback, cid, pn, rn);
    }
}
