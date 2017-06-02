package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhaixiaotong on 2017-5-14.
 */
@Entity
public class PersonalCollection {
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
     *收藏的时间
     */
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *收藏的项目的种类，product，car，container
     */

    private String collectOptionType;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
