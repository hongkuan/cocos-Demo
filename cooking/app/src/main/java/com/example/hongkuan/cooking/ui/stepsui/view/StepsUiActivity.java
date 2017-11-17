package com.example.hongkuan.cooking.ui.stepsui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.hongkuan.cooking.base.mvp.activity.BaseSingleFragmentActivity;

/**
 * Created by hongk on 2017/11/13.
 */

public class StepsUiActivity extends BaseSingleFragmentActivity {

    private static final String TAG = "StepsUiActivity";
    private static final String KEY_MENU_POSITION = "key_menu_position";

    public static Intent newIntent(Context context, int position){
        Intent intent = new Intent(context, StepsUiActivity.class);
        intent.putExtra(KEY_MENU_POSITION, position);
        return intent;
    }

    protected Fragment createFragment() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(KEY_MENU_POSITION, 0);
        return StepsFragment.getInstance(position);
    }
}
