package com.study.model.bean;

public class GoodsListBean {

    /**
     * id : 1181000
     * name : 母亲节礼物-舒适安睡组合
     * list_pic_url : http://yanxuan.nosdn.127.net/1f67b1970ee20fd572b7202da0ff705d.png
     * retail_price : 2598
     */

    private int id;
    private String name;
    private String list_pic_url;
    private int retail_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getList_pic_url() {
        return list_pic_url;
    }

    public void setList_pic_url(String list_pic_url) {
        this.list_pic_url = list_pic_url;
    }

    public int getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(int retail_price) {
        this.retail_price = retail_price;
    }
}
