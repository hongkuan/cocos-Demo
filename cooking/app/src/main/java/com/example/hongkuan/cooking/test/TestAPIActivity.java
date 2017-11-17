package com.example.hongkuan.cooking.test;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hongkuan.cooking.R;
import com.example.hongkuan.cooking.network.http.CategoryRequest;
import com.example.hongkuan.cooking.network.http.IndexRequest;
import com.example.hongkuan.cooking.network.http.QueryIdRequest;
import com.example.hongkuan.cooking.network.http.QueryRequest;
import com.example.hongkuan.cooking.ui.categoryui.view.CategoryFragment;

import java.io.UnsupportedEncodingException;

public class TestAPIActivity extends FragmentActivity {

    private TextView mTextView;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (mTextView != null){
                Object obj = msg.obj;
                mTextView.setText(obj.toString());
            }
        }
    };
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drawer);

        mTextView = (TextView) findViewById(R.id.textView);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.drawer_layout_fragment);
        if (fragment == null){
            fragment = new CategoryFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.drawer_layout_fragment,fragment)
                    .commit();
        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                String msg = null;
                try {
                    //msg = CategoryRequest.getCategoryRequest().setRequestParams(0).request();
                    //msg = QueryRequest.getQueryRequest().setRequestParams("包菜", "1", "10", 0).request();
                    msg = IndexRequest.getIndexRequest().setRequestParams(1, "1", "5", "").request();
                    //msg = QueryIdRequest.getQueryIdRequest().setRequestParams(1).request();

                } catch (UnsupportedEncodingException e) {
                    msg = e.getMessage();
                    e.printStackTrace();
                }

                Message message = new Message();
                message.obj = msg;
                mHandler.sendMessage(message);
            }
        }).start();*/
    }
}
