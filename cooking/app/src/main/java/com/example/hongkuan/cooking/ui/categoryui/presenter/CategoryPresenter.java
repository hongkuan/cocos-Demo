package com.example.hongkuan.cooking.ui.categoryui.presenter;

import android.content.Context;
import android.util.Log;

import com.example.hongkuan.cooking.mode.DataHelper;
import com.example.hongkuan.cooking.mode.entity.Children;
import com.example.hongkuan.cooking.mode.entity.ParentArray;
import com.example.hongkuan.cooking.ui.categoryui.adapter.CategoryListAdapter;
import com.example.hongkuan.cooking.ui.categoryui.contract.CategoryUiContract;
import com.example.hongkuan.cooking.workthread.DataAsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongk on 2017/11/10.
 */

public class CategoryPresenter implements CategoryUiContract.Presenter {
    private final static String TAG = "CategoryPresenter";
    private final CategoryUiContract.View mView;

    private CategoryListAdapter.OnGridViewItemClickCallback mOnGridViewItemClickCallback = null;
    private CategoryListAdapter mListAdapter;
    private DataAsyncTask.OnResultCallback mOnResultCallback;

    public CategoryPresenter(CategoryUiContract.View view){
        Log.i(TAG, "CategoryPresenter: ");
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        mOnGridViewItemClickCallback = new CategoryListAdapter.OnGridViewItemClickCallback() {
            @Override
            public void onGridViewItemClick(Children children) {
                // TODO
                mView.showToast(children.toString());
                // TODO
                mView.startMainUi(Integer.valueOf(children.getId()), "0", "5");
                mView.onMenuItemClick(Integer.valueOf(children.getId()), "0", "10");
            }
        };

        this.mOnResultCallback = new DataAsyncTask.OnResultCallback() {
            @Override
            public void onResultCallback(Object results) {
                if (mListAdapter != null){
                    mListAdapter.updateData((List<ParentArray>)results);
                    mListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelledCallback(Object results) {

            }
        };
    }

    @Override
    public void initAdapter(Context context) {
        DataHelper.getInstance().getAllCategory(mOnResultCallback);
        this.mListAdapter = new CategoryListAdapter(context, new ArrayList<ParentArray>(), mOnGridViewItemClickCallback);
        mView.setAdapter(mListAdapter);
    }

    @Override
    public void onDestroy() {
        if (this.mOnResultCallback != null){
            this.mOnResultCallback = null;
        }
        if (this.mListAdapter != null){
            this.mListAdapter = null;
        }
        if (mOnGridViewItemClickCallback != null){
            mOnGridViewItemClickCallback = null;
        }
    }
}
