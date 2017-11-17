package com.example.hongkuan.cooking.ui.categoryui.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.hongkuan.cooking.base.mvp.activity.BaseSingleFragmentActivity;

/**
 * Created by hongk on 2017/11/13.
 */

public class CategoryActivity extends BaseSingleFragmentActivity {
    private static final String TAG = "CategoryActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected Fragment createFragment() {
        Log.i(TAG, "createFragment: ");
        return new CategoryFragment();
    }
}
