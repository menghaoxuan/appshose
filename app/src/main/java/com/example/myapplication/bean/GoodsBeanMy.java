package com.example.myapplication.bean;

import org.litepal.crud.DataSupport;


public class GoodsBeanMy extends DataSupport {

    private int goods_id;
    private String goods_name;
    private double goods_price;
    private int goods_num;
    private int goods_pic;
    private String goods_type;
    private String goods_ladu;
    private String remark;
    private String kll;
    private String zf;
    private String dbz;
    private String mTime;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    private String userName;
    private String mUrl;


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }


    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }



    public int getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(int goods_pic) {
        this.goods_pic = goods_pic;
    }


    public String getKll() {
        return kll;
    }

    public void setKll(String kll) {
        this.kll = kll;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    public String getDbz() {
        return dbz;
    }

    public void setDbz(String dbz) {
        this.dbz = dbz;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
