package com.bignerdranch.android.musicclient;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MusicListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MusicListFragment.newInstance();
    }
}
