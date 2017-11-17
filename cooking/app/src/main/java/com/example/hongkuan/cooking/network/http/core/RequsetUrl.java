package com.example.hongkuan.cooking.network.http.core;

/**
 * Created by hongk on 2017/11/2.
 */

public class RequsetUrl {

    private static final String url = "http://apis.juhe.cn/cook/";

    // 菜谱大全
    public static final String query = url + "query";
    // 分类标签列表
    public static final String category = url + "category";
    // 按标签检索菜谱
    public static final String index = url + "index";
    // 按菜单ID查看详细
    public static final String queryid = url + "queryid";

}
