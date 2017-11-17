package com.example.hongkuan.cooking.network.http;

import com.example.hongkuan.cooking.network.http.core.RequsetUrl;

import java.io.UnsupportedEncodingException;

/**
 * Created by hongk on 2017/11/2.
 */

public class QueryRequest extends BaseRequest {

    protected QueryRequest(){
        super();
    }

    public static QueryRequest getQueryRequest(){
        return new QueryRequest();
    }

    /**
     * menu	string	是	需要查询的菜谱名
     * pn	string	否	数据返回起始下标
     * rn	string	否	数据返回条数，最大30
     * albums	int	否	albums字段类型，1字符串，默认数组
     * @return
     */
    public QueryRequest setRequestParams(String menu, String pn, String rn, int albums){
        mParams.put("menu", menu);
        mParams.put("pn", pn);
        mParams.put("rn", rn);
        mParams.put("albums", albums);
        return this;
    }

    @Override
    public String getUrl() throws UnsupportedEncodingException {
        return RequsetUrl.query + "?"+ map2URLParam(mParams);
    }
}
