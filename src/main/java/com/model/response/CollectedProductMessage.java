package com.model.response;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangliang on 2016-9-17.
 * 专门用来标识收藏条目的bean，需要加上PersonalCollection中的id，否则没法删除
 */

@Entity
public class CollectedProductMessage implements Serializable {

	private static final long serialVersionUID = 1261106834799864881L;
	/*
	* 标示收藏表的id
	* */
	@Id
	private Long collectionId;
	/**
	* 货源id
	* */

	private Long id;
	/**
	 *货主id
	 */
     private long userId;
     
     /**
 	 *货主手机号码
 	 */
      private String phoneNum;
      
     /**
   	 *普货、大件、冷藏、危险品
   	 */
      private String productMode;
	/**
	 * 出发地
	 */
	private String departurePlace;

	/**
	 * 目的地
	 */
	private String destinationPlace;

	/**
	 * 装货时间
	 */
	private String loadDate;

	/**
	 * 需要车型
	 */
	private String carType;

	/**
	 * 车长
	 */
	private String carLength;

	/**
	 * 货物重量
	 */
	private String goodsWeight;

	/**
	 * 货物体积
	 */
	private String goodsVolume;

	/**
	 * 货物名称
	 */
	private String goodsName;

	/**
	 * 货物类型
	 */
	private String goodsType;

	/**
	 * 结费方式（现金结，回单结，票结，月结）
	 */
	private String payWay;

	/**
	 * 详细信息，非必填
	 */
	private String detailInfo;
	/**
	 * 经度
	 */
	private String longitude;
	
	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 举报次数
	 */
	private Long reportNum;

	/**
	 * 创建时间
	 */
	private Date gmtCreated;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	public String getDestinationPlace() {
		return destinationPlace;
	}

	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}

	public String getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarLength() {
		return carLength;
	}

	public void setCarLength(String carLength) {
		this.carLength = carLength;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getGoodsVolume() {
		return goodsVolume;
	}

	public void setGoodsVolume(String goodsVolume) {
		this.goodsVolume = goodsVolume;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getProductMode() {
		return productMode;
	}

	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getReportNum() {
		return reportNum;
	}

	public void setReportNum(Long reportNum) {
		this.reportNum = reportNum;
	}


}
