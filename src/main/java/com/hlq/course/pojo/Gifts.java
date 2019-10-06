package com.hlq.course.pojo;

public class Gifts {
    private Integer id;

    private String name;

    private Integer gnum;

    private Double gintergral;

    private String imgurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public Double getGintergral() {
        return gintergral;
    }

    public void setGintergral(Double gintergral) {
        this.gintergral = gintergral;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }
}