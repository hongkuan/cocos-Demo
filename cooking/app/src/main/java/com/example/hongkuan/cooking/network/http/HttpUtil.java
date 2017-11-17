package com.example.hongkuan.cooking.network.http;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hongk on 2017/11/2.
 */

public class HttpUtil {

    private static final String TAG = "HttpUtil";

    public static byte[] httpGetResponse(String methodName,String urlParam){
        Log.i(TAG, "start httpGetResponse: methodName:" + methodName + " urlParam:"+urlParam);
        byte[] responseByteArr = null;
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(urlParam);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(methodName);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            responseByteArr = getBytesByInputStream(inputStream);
        } catch (MalformedURLException e) {
            Log.e(TAG, "httpGetResponse: ", e);
            e.printStackTrace();
        } catch (IOException e){
            Log.e(TAG, "httpGetResponse: ", e);
            e.printStackTrace();
        } finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        Log.i(TAG, "end httpGetResponse: responseByteArr:" + new String(responseByteArr));
        return responseByteArr;
    }

    //从InputStream中读取数据，转换成byte数组，最后关闭InputStream
    private static byte[] getBytesByInputStream(InputStream is) throws IOException{
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

}
