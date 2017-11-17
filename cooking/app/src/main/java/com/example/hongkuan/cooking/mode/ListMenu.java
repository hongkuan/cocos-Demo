package com.example.hongkuan.cooking.mode;

import android.text.TextUtils;
import android.util.Log;

import com.example.hongkuan.cooking.mode.entity.Menu;
import com.example.hongkuan.cooking.mode.entity.ParentArray;
import com.example.hongkuan.cooking.network.http.IndexRequest;
import com.example.hongkuan.cooking.util.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongk on 2017/11/13.
 */

public class ListMenu {

    private static final String TAG = "ListMenu";

    // TODO
    private int mCid ;
    private String mPn ;
    private String mRn ;
    private List<Menu> mMenuList = null;

    protected List<Menu> parseListMenu(JSONObject jsonObj, int cid, String pn, String rn){
        if (this.mMenuList != null){
            if (mCid == cid && pn.equals(mPn) && rn.equals(mRn)){
                return mMenuList;
            } else {
                mMenuList.clear();
            }
        }
        if (this.mMenuList == null){
            mMenuList = new ArrayList<>();
        }
        mCid = cid;
        mPn = pn;
        mRn = rn;
        try {
            if (jsonObj != null && !jsonObj.isNull("data")){
                JSONArray data = jsonObj.getJSONArray("data");
                if (data != null && data.length() > 0){
                    for (int i =0 ; i < data.length() ; i++){
                        Menu menu = JSONHelper.parseObject(data.getJSONObject(i), Menu.class);
                        mMenuList.add(menu);
                    }
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
            Log.e(TAG, "parseListMenu: JSONException", e);
        }
        return mMenuList;
    }

    protected Menu getMene(int position){
        return mMenuList.get(position);
    }

    protected String getNetData(int cid, String pn, String rn){
        try {
            return IndexRequest.getIndexRequest().setRequestParams(cid, pn, rn, "").request();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(TAG, "getNetData: ", e);
        }
        return "";
    }
}
