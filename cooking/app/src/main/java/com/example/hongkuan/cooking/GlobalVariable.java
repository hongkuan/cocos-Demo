package com.example.hongkuan.cooking;

/**
 * Created by hongk on 2017/11/2.
 */

public class GlobalVariable {

    public static final String APP_KEY = "5a9294e2abb97ebb8b4f3514a4296836";

    /**
     *      错误码
     204601	菜谱名称不能为空
     204602	查询不到相关信息
     204603	菜谱名过长
     204604	错误的标签ID
     204605	查询不到数据
     204606	错误的菜谱ID
     */
    public static final int ERROR_CODE_MENU_NAME_IS_NU = 204601;
    public static final int ERROR_CODE_MSG_IS_NULL = 204602;
    public static final int ERROR_CODE_MENU_NAME_TOO_LONG = 204603;
    public static final int ERROR_CODE_CID_IS_ERROR = 204604;
    public static final int ERROR_CODE_CANNOT_FIND = 204605;
    public static final int ERROR_CODE_MENUID_IS_ERROR = 204606;

}
