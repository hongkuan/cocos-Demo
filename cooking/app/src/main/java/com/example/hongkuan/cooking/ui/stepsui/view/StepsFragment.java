package com.example.hongkuan.cooking.ui.stepsui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.mode.DataHelper;
import com.example.hongkuan.cooking.ui.stepsui.contract.StepsUiContract;
import com.example.hongkuan.cooking.ui.stepsui.presenter.StepsPresenter;

/**
 * Created by hongk on 2017/11/14.
 */

public class StepsFragment extends Fragment implements StepsUiContract.View {
    private final static String TAG = "StepsFragment";
    private static final String KEY_MENU_POSITION = "key_menu_position";
    private StepsUiContract.Presenter mPresenter;
    private ImageView mMenuIcon;
    private TextView mBurden;
    private TextView mIngredients;
    private ListView mStepsList;
    private int mPosition;
    private Button mCommonLeftButton;
    private Button mCommonRightButton;
    private TextView mCommonTitleText;

    public static StepsFragment getInstance(int position){
        StepsFragment stepsFragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_MENU_POSITION, position);
        stepsFragment.setArguments(args);
        return stepsFragment;
    }

    @Override
    public void setPresenter(StepsUiContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        this.mPosition = bundle.getInt(KEY_MENU_POSITION, 0);
        new StepsPresenter(this);
        this.mPresenter.start();
        return initView(inflater, container);
    }

    private View initView(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.steps_ui_fragment, container, false);
        initHandView(view);
        this.mMenuIcon = (ImageView) view.findViewById(R.id.steps_ui_fragment_icon);
        this.mIngredients = (TextView) view.findViewById(R.id.steps_ui_fragment_ingredients_v);
        this.mBurden = (TextView) view.findViewById(R.id.steps_ui_fragment_burden_v);
        this.mStepsList = (ListView) view.findViewById(R.id.steps_ui_list_view);
        this.mStepsList.setFocusable(false);
        this.mPresenter.initAdapter(this.getActivity(), mPosition);
        this.mPresenter.initViewData(this.getActivity(), mPosition);
        setListener();
        return view;
    }

    @Override
    public TextView getIngredientsView() {
        return this.mIngredients;
    }

    @Override
    public TextView getBurdenView() {
        return this.mBurden;
    }

    @Override
    public ImageView getMenuIconView() {
        return this.mMenuIcon;
    }

    private void initHandView(View view){
        this.mCommonLeftButton = (Button)view.findViewById(R.id.common_hand_view_left_button);
        this.mCommonRightButton = (Button)view.findViewById(R.id.common_hand_view_right_button);
        this.mCommonTitleText = (TextView)view.findViewById(R.id.common_hand_view_title_text);
        this.mCommonTitleText.setText(DataHelper.getInstance().getMenu(mPosition).getTitle());
        this.mCommonLeftButton.setText(R.string.steps_ui_left_text);
    }

    private void setListener(){
        this.mCommonLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.leftButtonClick();
            }
        });

        this.mCommonRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.rightButtonClick();
            }
        });
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        this.mStepsList.setAdapter(adapter);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backUi() {
        this.getActivity().finish();
    }

    @Override
    public void onDestroy() {
        this.mPresenter.onDestroy();
        super.onDestroy();
    }
}
