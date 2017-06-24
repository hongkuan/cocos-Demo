package com.example.hongk.testfragmentreplace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hongk on 2017/6/25.
 */

public class ViewPagerFragment extends Fragment {

    private final int mCount;
    private TextView mTextView;

    private ViewPagerFragment(int count){
        this.mCount = count;
    }

    public static Fragment newInstance(int count){
        return new ViewPagerFragment(count);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_item, container, false);
        this.mTextView = (TextView)view.findViewById(R.id.view_pager_item_text_view);
        mTextView.setText(mCount+"");
        return view;
    }
}
