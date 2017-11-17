package com.example.hongkuan.cooking.network.http;

import com.example.hongkuan.cooking.network.http.core.RequsetUrl;

import java.io.UnsupportedEncodingException;

/**
 * Created by hongk on 2017/11/2.
 */

public class IndexRequest extends BaseRequest {

    protected IndexRequest(){
        super();
    }

    public static IndexRequest getIndexRequest(){
        return new IndexRequest();
    }

    @Override
    public String getUrl() throws UnsupportedEncodingException {
        return RequsetUrl.index + "?"+ map2URLParam(mParams);
    }

    /**
     * cid	    int	    是	标签ID 分类标签id
     * pn	    string	否	数据返回起始下标，默认0
     * rn	    string	否	数据返回条数，最大30，默认10
     * format	string	否	steps字段屏蔽，默认显示，format=1时屏蔽
     */
    public IndexRequest setRequestParams(int cid, String pn, String rn, String format){
        mParams.put("cid", cid);
        mParams.put("pn", pn);
        mParams.put("rn", rn);
        mParams.put("format", format);
        return this;
    }
}
