package com.example.hongkuan.cooking.network.http;

import com.example.hongkuan.cooking.GlobalVariable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongk on 2017/11/2.
 */

public abstract class BaseRequest {

    public static final String REQUEST_METHOD_NAME_GET = "GET";
    public static final String REQUEST_METHOD_NAME_POST = "POST";

    protected Map<String, Object> mParams = new HashMap<>();

    /**
     * key	string	是	应用APPKEY(应用详细页查询)
     * dtype	string	否	返回数据的格式,xml或json，默认json
     * @return
     */
    public String request() throws UnsupportedEncodingException {
        mParams.put("key", GlobalVariable.APP_KEY);
        mParams.put("dtype", "");
        String url = getUrl();
        byte[] response = HttpUtil.httpGetResponse(REQUEST_METHOD_NAME_GET, url);
        return new String(response);
    }

    public abstract String getUrl() throws UnsupportedEncodingException ;

    protected String map2URLParam(Map<String, Object> params) throws UnsupportedEncodingException {
        if (params == null){
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()){
            sb.append(entry.getKey() + "=" + URLEncoder.encode(String.valueOf(entry.getValue()) , "utf-8"));
            sb.append("&");
        }

        String s = sb.toString();
        if (s.endsWith("&")){
            s = s.substring(0, s.length()-1);
        }

        return s;
    }
}
