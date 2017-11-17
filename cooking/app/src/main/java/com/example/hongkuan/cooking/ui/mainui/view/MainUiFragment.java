package com.example.hongkuan.cooking.ui.mainui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.ui.categoryui.view.CategoryFragment;
import com.example.hongkuan.cooking.ui.mainui.contract.MainUiContract;
import com.example.hongkuan.cooking.ui.mainui.presenter.MainUiPresenter;
import com.example.hongkuan.cooking.ui.stepsui.view.StepsUiActivity;

/**
 * Created by hongk on 2017/11/13.
 */

public class MainUiFragment extends Fragment implements MainUiContract.View, CategoryFragment.OnMenuItemClickCallback {
    private static final String TAG = "MainUiFragment";
    private static final String KEY_INDEX_CID = "key_index_cid";
    private MainUiContract.Presenter mPresenter;
    private ListView mListView;
    private int mCid = 1;
    private DrawerLayout mDrawerLayout;


    public static MainUiFragment getInstance(int cid){
        MainUiFragment mainUiFragment = new MainUiFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX_CID, cid);
        mainUiFragment.setArguments(args);
        return mainUiFragment;
    }

    @Override
    public void setPresenter(MainUiContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        this.mCid = bundle.getInt(KEY_INDEX_CID, 1);
        new MainUiPresenter(this);
        this.mPresenter.start();
        return initView(inflater, container);
    }

    private View initView(LayoutInflater inflater, ViewGroup container ){
        View view = inflater.inflate(R.layout.main_ui_fragment, container, false);
        this.mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        this.mListView = (ListView) view.findViewById(R.id.main_ui_list_view);
        this.mPresenter.initAdapter(this.getActivity(), this.mCid);
        setListener();
        return view;
    }

    private void setListener(){
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onListOnItemClick(view, position, id);
            }
        });
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        this.mListView.setAdapter(adapter);
    }

    @Override
    public void startStepsUi(int position) {
        Intent intent = StepsUiActivity.newIntent(this.getActivity(), position);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMenuItemClickCallback(int cid, String pn, String rn) {
        mPresenter.updateListData(cid, pn, rn);
    }
}
