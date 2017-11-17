package com.example.hongkuan.cooking.mode.entity;

import java.util.Arrays;

/**
 * Created by hongk on 2017/11/13.
 */

public class ParentArray {
    /**
     * {
     "parentId":"10001",
     "name":"菜式菜品",
     "list":[
     {
     "id":"1",
     "name":"家常菜",
     "parentId":"10001"
     },
     {
     "id":"2",
     "name":"快手菜",
     "parentId":"10001"
     },
     {
     "id":"3",
     "name":"创意菜",
     "parentId":"10001"
     },
     {
     "id":"4",
     "name":"素菜",
     "parentId":"10001"
     },
     {
     "id":"5",
     "name":"凉菜",
     "parentId":"10001"
     },
     {
     "id":"6",
     "name":"烘焙",
     "parentId":"10001"
     },
     {
     "id":"7",
     "name":"面食",
     "parentId":"10001"
     },
     {
     "id":"8",
     "name":"汤",
     "parentId":"10001"
     },
     {
     "id":"9",
     "name":"自制调味料",
     "parentId":"10001"
     }
     ]
     }
     */
    private String parentId;
    private String name;
    private Children[] list;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Children[] getList() {
        return list;
    }

    public void setList(Children[] list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ParentArray{" +
                "parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", list=" + Arrays.toString(list) +
                '}';
    }
}
