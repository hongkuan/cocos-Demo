package com.example.hongkuan.cooking.network.http;

import com.example.hongkuan.cooking.network.http.core.RequsetUrl;

import java.io.UnsupportedEncodingException;

/**
 * Created by hongk on 2017/11/2.
 */

public class CategoryRequest extends BaseRequest {

    protected CategoryRequest(){
        super();
    }

    public static CategoryRequest getCategoryRequest(){
        return new CategoryRequest();
    }

    @Override
    public String getUrl() throws UnsupportedEncodingException {
        return RequsetUrl.category + "?"+ map2URLParam(mParams);
    }

    /**
     * parentid	int	    否	分类ID，默认全部
     */
    public CategoryRequest setRequestParams(int parentId){
        mParams.put("parentid", parentId);
        return this;
    }
}
