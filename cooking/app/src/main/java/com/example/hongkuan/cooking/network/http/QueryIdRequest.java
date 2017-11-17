package com.example.hongkuan.cooking.network.http;

import com.example.hongkuan.cooking.network.http.core.RequsetUrl;

import java.io.UnsupportedEncodingException;

/**
 * Created by hongk on 2017/11/2.
 */

public class QueryIdRequest extends BaseRequest {

    protected QueryIdRequest(){
        super();
    }

    public static QueryIdRequest getQueryIdRequest(){
        return new QueryIdRequest();
    }

    /**
     * id	int	是	菜谱的ID
     * @return
     */
    public QueryIdRequest setRequestParams(int id){
        mParams.put("id", id);
        return this;
    }

    @Override
    public String getUrl() throws UnsupportedEncodingException {
        return RequsetUrl.queryid + "?"+ map2URLParam(mParams);
    }
}
