package com.example.hongkuan.cooking.mode.entity;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

/**
 * Created by hongk on 2017/11/9.
 */

public class Menu {
    private static final String TAG ="Menu";

    /**
     * {
     "id":"1571",
     "title":"炝拌空心菜",
     "tags":"家常菜;痛风;夏季;快手菜;利湿止血",
     "imtro":"空心菜是老公的最爱，每到夏季老公都会说买菜多买一些空心菜回来，我每次都会加一些大蒜清炒。有次和老公一起去外面吃饭，那餐厅的有炝拌空心菜开胃好吃。只是实在太辣了，女儿就没吃几根，回家就对我说，妈妈你要做那样的空心菜，不要太辣了。我第二天去市场买空心菜，只是空心菜价格一直居高不下，都是论把卖的，一把大约有200克2元，我干脆买了两把，让女儿吃个够。 空心菜性寒、味甘，归肝、心、大肠、小肠经； 可清热凉血，利尿，润肠通便，清热解毒，利湿止血等功效； 主治鼻衄、便秘、淋浊、便血、痔疮、痈肿、折伤、蛇虫咬伤等病症。",
     "ingredients":"空心菜,300g",
     "burden":"油,适量;盐,适量;大蒜,2g;白糖,1g;胡椒粉,2g;花椒粒,1g;辣皮子,4个",
     "albums":[
     "http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/t\/2\/1571_563278.jpg"
     ],
     "steps":[
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_498e57b2ce4de646.jpg",
     "step":"1.主材：空心菜 辅材：植物油 盐 大蒜 香醋 白糖、胡椒粉 辣皮子 花椒粒"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_b71734bf66e9a661.jpg",
     "step":"2.把空心菜去掉老叶和老的梗部，煮锅加水，加空心菜植物油盐烫软捞起"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_f63c3be613144fa3.jpg",
     "step":"3.过凉水捞起"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_13341ed7a94041e2.jpg",
     "step":"4.沥干水分摆在盘里"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_1642a65a0d3b14ab.jpg",
     "step":"5.辣皮子泡泡切末，把大蒜捣泥"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_481ddff1e82d3406.jpg",
     "step":"6.用白糖盐胡椒粉调成汁 ，浇在空心菜上面"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_cd474697c6cecf56.jpg",
     "step":"7.热锅凉油，油热6成左右，加花椒粒和辣椒末爆出香味"
     },
     {
     "img":"http:\/\/juheimg.oss-cn-hangzhou.aliyuncs.com\/cookbook\/s\/16\/1571_c140dced4c5fd329.jpg",
     "step":"8.浇在空心菜上即可"
     }
     ]
     }
     */

    private String id;
    private String title;
    private String tags;
    private String imtro;
    private String ingredients;
    private String burden;
    private String albums;
    private CookingStep[] steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

    public String getOneAlbum(int index){
        if (TextUtils.isEmpty(albums)){
            return "";
        }
        try {
            JSONArray jsonArray = new JSONArray(albums);
            return jsonArray.getString(index);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "getOneAlbum: ", e);
        }
        return "";
    }

    public CookingStep[] getSteps() {
        return steps;
    }

    public void setSteps(CookingStep[] steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", imtro='" + imtro + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", burden='" + burden + '\'' +
                ", albums='" + albums + '\'' +
                ", steps=" + Arrays.toString(steps) +
                '}';
    }
}
