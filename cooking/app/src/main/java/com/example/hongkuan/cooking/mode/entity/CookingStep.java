package com.example.hongkuan.cooking.mode.entity;

/**
 * Created by hongk on 2017/11/9.
 */

public class CookingStep {
    /**
     * {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_c140dced4c5fd329.jpg",
     "step":"8.浇在空心菜上即可"
     }
     */

    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "CookingStep{" +
                "img='" + img + '\'' +
                ", step='" + step + '\'' +
                '}';
    }
}
