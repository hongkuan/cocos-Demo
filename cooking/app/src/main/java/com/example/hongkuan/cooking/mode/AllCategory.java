package com.example.hongkuan.cooking.mode;

import android.util.Log;
import com.example.hongkuan.cooking.mode.entity.ParentArray;
import com.example.hongkuan.cooking.network.http.CategoryRequest;
import com.example.hongkuan.cooking.util.JSONHelper;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongk on 2017/11/13.
 */

public class AllCategory {

    private static final String TAG = "AllCategory";

    protected List<ParentArray> parseAllCategory(JSONArray jsonArr){
        List<ParentArray> result = new ArrayList<>();
        try {
            if (jsonArr != null && jsonArr.length() > 0){
                for (int i =0 ; i < jsonArr.length() ; i++){
                    result.add(JSONHelper.parseObject(jsonArr.getJSONObject(i), ParentArray.class));
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
            Log.e(TAG, "parseAllCategory: JSONException", e);
        }
        return result;
    }

    protected String getNetData(){
        try {
            return CategoryRequest.getCategoryRequest().setRequestParams(0).request();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(TAG, "getNetData: ", e);
        }
        return "";
    }

}
