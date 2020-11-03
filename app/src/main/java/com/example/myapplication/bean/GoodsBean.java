package com.example.myapplication.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;




public class GoodsBean extends DataSupport implements Serializable {

    private int goods_id;
    private String goods_name;
    private double goods_price;
    private int goods_num;
    private int goods_pic;
    private String kll;
    private String zf;
    private String dbz;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    private String mUrl;

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

    public int getGoods_id() {
        return goods_id;
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

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(int goods_pic) {
        this.goods_pic = goods_pic;
    }
}
