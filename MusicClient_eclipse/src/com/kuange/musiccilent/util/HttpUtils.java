package com.kuange.musiccilent.util;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpUtils {
	public static final int METHOD_GTE=0;
	public static final int METHOD_POST=1;
	
	/**
	 *发送http请求的工具类 
	 * @param method 请求方式
	 * @param uri 请求资源路径
	 * @param pairs 请求参数列表
	 * @return 响应实体 HttpEntity
	 * @throws Exception
	 */
	public static HttpEntity getEntity(int method,String uri,List<NameValuePair> pairs)throws Exception {
		HttpResponse response=getResponse(method, uri, pairs);
		return response.getEntity();
	}
	/**
	 * 发送http请求的工具类
	 * @param method 请求方式
	 * @param uri 请求资源路径
	 * @param pairs 请求参数列表
	 * @return 响应实体 HttpEntity
	 * @throws Exception
	 */
	public static HttpResponse getResponse(int method,String uri,List<NameValuePair> pairs)throws Exception{
		HttpClient client=new DefaultHttpClient();
		HttpResponse response=null;
		switch (method) {
		case METHOD_GTE:
			HttpGet get=new HttpGet(uri);
			response=client.execute(get);
			break;
		case METHOD_POST:
			HttpPost post=new HttpPost(uri);
			post.setHeader("Content-Type","application/x-www-forom-urlencoded");
			HttpEntity reqeEntity=new UrlEncodedFormEntity(pairs);
			response=client.execute(post);
			break;
		}
		return response;
	}
}
