package com.model.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zhaixiaotong on 2017-5-14.
 */
public class PersonalCollectionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     *收藏者电话号码
     */
    private String phoneNum;

    /**
     *收藏的项目id
     */
    private long collectOptionId;

    /**
     *收藏的项目的种类，product，car，container
     */
    private String collectOptionType;

    /**
     * 当前页面
     */
    private int currentPage;

    /**
     * 每个页面的条目数量
     */
    private int pageSize;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long getCollectOptionId() {
        return collectOptionId;
    }

    public void setCollectOptionId(long collectOptionId) {
        this.collectOptionId = collectOptionId;
    }

    public String getCollectOptionType() {
        return collectOptionType;
    }

    public void setCollectOptionType(String collectOptionType) {
        this.collectOptionType = collectOptionType;
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
