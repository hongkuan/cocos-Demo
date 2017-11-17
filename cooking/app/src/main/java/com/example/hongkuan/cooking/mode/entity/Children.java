package com.example.hongkuan.cooking.mode.entity;

/**
 * Created by hongk on 2017/11/13.
 */

public class Children {
    /**
     * {
     "id":"9",
     "name":"自制调味料",
     "parentId":"10001"
     }
     */
    private String id;
    private String name;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Children{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
