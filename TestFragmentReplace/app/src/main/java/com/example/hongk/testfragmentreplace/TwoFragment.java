package com.example.hongk.testfragmentreplace;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hongk on 2017/6/24.
 */

public class TwoFragment extends Fragment {

    private static final String KEY_DATA = "TwoData";

    private View mStartOneButton;
    private TwoFragmentCallback mCallback;
    private String mOneData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (TwoFragmentCallback) context;
    }

    private TwoFragment(){}

    public static Fragment newInstance(String data){
        Bundle bundle = null;
        if (!TextUtils.isEmpty(data)){
            bundle = new Bundle();
            bundle.putSerializable(KEY_DATA, data);
        }
        Fragment fragment = new TwoFragment();
        if (bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        this.mStartOneButton = view.findViewById(R.id.start_one_button);
        getOneDATA();
        setOnClick();
        return view;
    }

    private void getOneDATA(){
        Bundle bundle = getArguments();
        if (bundle != null && bundle.get(KEY_DATA) != null){
            this.mOneData = (String) bundle.get(KEY_DATA);
        }
    }

    private void setOnClick() {
        mStartOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onTwoFragmentCallback(mOneData+ "第二个fragment发回来的data");
            }
        });
    }

    public interface TwoFragmentCallback {
        void onTwoFragmentCallback(String data);
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }
}
