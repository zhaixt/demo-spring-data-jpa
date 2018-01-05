package com.model.request;

/**
 * Created by zhaixt on 2017/11/24.
 */
public class CarTransactionRequest {
    private String topic;
    private String type;//车型
    private String mali;//马力
    private Integer year;//年份
    private Integer axisNum;//轴数
    private String pictures;//照片
    private String details;//详情

    /**
     * 发布者名字
     **/
    private String publishName;

    private String phoneNum;
    /**
     * 当前页面
     */
    private int currentPage;

    /**
     * 每个页面的条目数量
     */
    private int pageSize;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMali() {
        return mali;
    }

    public void setMali(String mali) {
        this.mali = mali;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getAxisNum() {
        return axisNum;
    }

    public void setAxisNum(Integer axisNum) {
        this.axisNum = axisNum;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
