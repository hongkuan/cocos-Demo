package com.example.hongk.testfragmentreplace;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongk on 2017/6/24.
 */

public class OneFragment extends Fragment {

    private static final String KEY_DATA = "OneData";

    private View mStartTwoButton;
    private OneFragmentCallback mCallback;
    private View mUpdateData;
    private TextView mTextView;
    private String mOneData;
    private ViewPager mViewPager;

    private OneFragment(){}

    public static Fragment newInstance(String data){
        Bundle bundle = null;
        if (!TextUtils.isEmpty(data)){
            bundle = new Bundle();
            bundle.putSerializable(KEY_DATA, data);
        }
        Fragment fragment = new OneFragment();
        if (bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;

    }

    private void getOneDATA(){
        Bundle bundle = getArguments();
        if (bundle != null && bundle.get(KEY_DATA) != null){
            this.mOneData = (String) bundle.get(KEY_DATA);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OneFragmentCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        this.mTextView = (TextView)view.findViewById(R.id.one_text_view);
        this.mStartTwoButton = view.findViewById(R.id.start_two_button);
        this.mUpdateData = view.findViewById(R.id.update_data_button);

        setOnClick();

        getOneDATA();

        if (!TextUtils.isEmpty(mOneData)){
            this.mTextView.setText(mOneData);
        }

        initViewPager(view);
        return view;
    }

    private void setOnClick() {
        mStartTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mTextView.getText().toString();
                mCallback.onOneFragmentCallback(data);
            }
        });

        mUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText("宽哥更新了数据");
            }
        });
    }

    public interface OneFragmentCallback {
        void onOneFragmentCallback(String data);
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    private void initViewPager(View view){
        this.mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(ViewPagerFragment.newInstance(1));
        fragments.add(ViewPagerFragment.newInstance(2));
        fragments.add(ViewPagerFragment.newInstance(3));
        fragments.add(ViewPagerFragment.newInstance(4));
        fragments.add(ViewPagerFragment.newInstance(5));
        fragments.add(ViewPagerFragment.newInstance(6));
        fragments.add(ViewPagerFragment.newInstance(7));
        fragments.add(ViewPagerFragment.newInstance(8));
        fragments.add(ViewPagerFragment.newInstance(9));
        fragments.add(ViewPagerFragment.newInstance(10));
        fragments.add(ViewPagerFragment.newInstance(11));
        fragments.add(ViewPagerFragment.newInstance(12));
        fragments.add(ViewPagerFragment.newInstance(13));
        mViewPager.setAdapter(new FragmentPagerAdapter(this.getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        this.mViewPager.setOffscreenPageLimit(fragments.size());
        this.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }

}
