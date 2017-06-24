package com.example.hongk.testfragmentreplace;

import android.support.v4.app.Fragment;

/**
 * Created by hongk on 2017/6/24.
 */

public class FragmentActivity extends SingleFragmentActivity implements OneFragment.OneFragmentCallback, TwoFragment.TwoFragmentCallback {
    @Override
    public Fragment createFragment() {
        return OneFragment.newInstance("");
    }

    @Override
    public void onOneFragmentCallback(String data) {
        Fragment twoFragment = TwoFragment.newInstance(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, twoFragment)
                .commit();

    }

    @Override
    public void onTwoFragmentCallback(String data) {
        Fragment oneFragment = OneFragment.newInstance(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, oneFragment)
                .commit();
    }
}
