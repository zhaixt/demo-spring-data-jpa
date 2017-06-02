package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaixiaotong on 2016-11-7.
 */

@Entity
public class ContainerMessage implements Serializable {

	private static final long serialVersionUID = 1261106834799864881L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
   	 *20尺普柜，40尺普柜，冻柜，其他
   	 */
      private String containerMode;
  	
      /**
  	 * 港口，青岛港（默认），上海港，宁波港，天津港，深圳港，广州港，厦门港，中山港，连云港，其他（输入框）
  	 */
  	 private String port;
  	 

     /**
 	 * 进口，出口，内贸装，内贸卸
 	 */
  	 private String transportType;
  	 
  	/**
 	 * 如点击“20尺普柜”，此栏目为3项：单背，双背，配柜。其他柜型则此栏目不显示
     * 如点击“冻柜”，显示，是否需要制冷挂机，是，否
     * 如点击其他：显示选项：危险品，框架，开顶，45尺，挂衣，其他
 	 */
 	private String containerType;

	/**
	 * 集装箱重量
	 * 自己填单位吨，或者选择：（不超重）
	 */
	private String containerWeight;
	/**
	 * 装卸货地址
	 */
	private String landingPlace;
	/**
	 * 装卸货时间
	 */
	private String landingDate;
	/**
	 * 品名（可不填）或选择普货
	 */
	private String goodsName;
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

	/**
	 * 举报次数
	 */
	private Long reportNum;

	public String getContainerMode() {
		return containerMode;
	}

	public void setContainerMode(String containerMode) {
		this.containerMode = containerMode;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getContainerWeight() {
		return containerWeight;
	}

	public void setContainerWeight(String containerWeight) {
		this.containerWeight = containerWeight;
	}

	public String getLandingPlace() {
		return landingPlace;
	}

	public void setLandingPlace(String landingPlace) {
		this.landingPlace = landingPlace;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
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
