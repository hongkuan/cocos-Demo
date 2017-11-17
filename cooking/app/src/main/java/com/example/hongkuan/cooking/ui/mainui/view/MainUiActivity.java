package com.example.hongkuan.cooking.ui.mainui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.base.mvp.activity.BaseSingleFragmentActivity;
import com.example.hongkuan.cooking.ui.categoryui.view.CategoryFragment;

/**
 * Created by hongk on 2017/11/13.
 */

public class MainUiActivity extends BaseSingleFragmentActivity {
    private static final String KEY_INDEX_CID = "key_index_cid";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_ui;
    }

    public static Intent newIntent(Context context, int cid){
        Intent intent = new Intent(context, MainUiActivity.class);
        intent.putExtra(KEY_INDEX_CID, cid);
        return intent;
    }

    protected Fragment createFragment() {
        Intent intent = getIntent();
        int cid = intent.getIntExtra(KEY_INDEX_CID, 1);
        return MainUiFragment.getInstance(cid);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.drawer_layout_fragment);
        if (fragment == null){
            fragment = new CategoryFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.drawer_layout_fragment,fragment)
                    .commit();
        }

    }
}
