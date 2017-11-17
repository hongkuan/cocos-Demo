package com.example.hongkuan.cooking.ui.categoryui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.base.mvp.BaseView;
import com.example.hongkuan.cooking.ui.categoryui.contract.CategoryUiContract;
import com.example.hongkuan.cooking.ui.categoryui.presenter.CategoryPresenter;
import com.example.hongkuan.cooking.ui.mainui.view.MainUiActivity;

/**
 * Created by hongk on 2017/11/10.
 */

public class CategoryFragment extends Fragment implements CategoryUiContract.View {
    private final static String TAG = "CategoryFragment";

    private CategoryUiContract.Presenter mPresenter = null;
    private ListView mListView;

    @Override
    public void setPresenter(CategoryUiContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new CategoryPresenter(this);
        mPresenter.start();

        return initView(inflater, container);
    }

    private View initView(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.category_ui_fragment, container, false);
        this.mListView = (ListView) view.findViewById(R.id.category_ui_list_view);
        this.mPresenter.initAdapter(this.getActivity());
        return view;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        this.mListView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        this.mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainUi(int cid, String pn, String rn) {
        Intent intent = MainUiActivity.newIntent(this.getActivity(), cid);

        startActivity(intent);
    }

    @Override
    public void onMenuItemClick(int cid, String pn, String rn) {
        try {
            OnMenuItemClickCallback onMenuItemClickCallback = (OnMenuItemClickCallback) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            onMenuItemClickCallback.onMenuItemClickCallback(cid, pn, rn);
        } catch (ClassCastException e){
            throw new ClassCastException("fragment must implement OnMenuItemClickCallback");
        }
    }

    public interface OnMenuItemClickCallback{
        void onMenuItemClickCallback(int cid, String pn, String rn);
    }
}
