package com.xhui.recmd.spark.data;

import java.io.Serializable;

/**
 * Created by littlehui on 2016/9/8.
 */
public class CommodityPair implements Serializable {

    private String ip;

    private String code;

    /**
     * 10: 浏览
     * 11：下单
     */
    private String visitType;

    private Integer score;

    private Long startTimeMills;

    private Long endTimeMills;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getStartTimeMills() {
        return startTimeMills;
    }

    public void setStartTimeMills(Long startTimeMills) {
        this.startTimeMills = startTimeMills;
    }

    public Long getEndTimeMills() {
        return endTimeMills;
    }

    public void setEndTimeMills(Long endTimeMills) {
        this.endTimeMills = endTimeMills;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public CommodityPair() {

    }

    public CommodityPair(String reduceStr) {
        String commodityPair = reduceStr.substring(1, reduceStr.indexOf(")"));
        String[] pairs = commodityPair.split(",");
        String[] objStrs = pairs[0].split("_");
        this.ip = objStrs[0];
        this.code = objStrs[1];
        this.score = Integer.parseInt(pairs[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityPair that = (CommodityPair) o;

        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return !(score != null ? !score.equals(that.score) : that.score != null);
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ip + "_" + code;
    }

    public String toRowKey() {
        return ip + "_" + code;
    }
}
