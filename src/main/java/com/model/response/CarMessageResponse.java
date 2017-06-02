package com.model.response;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.model.RoadRoute;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangliang on 2016-9-17. 货源信息
 */

public class CarMessageResponse implements Serializable {

	private static final long serialVersionUID = 4974286391931851702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 发货人id
	 */
	private long userId;
	
	/**
	 * 发货人手机号码
	 */
	private String phoneNum;
	
	/**
	 * 专车、回程车、顺风车
	 */
	private String carMode;
	/**
	 * 出发地
	 */
	private String departurePlace;

	/**
	 * 目的地
	 */
	private String destinationPlace;

	/**
	 * 装载时间（默认随时）
	 */
	private String loadTime;

	/**
	 * 车型
	 */
	private String carType;

	/**
	 * 车长
	 */
	private String carLength;

	/**
	 * 期望运费
	 */
	private String exceptFee;

	/**
	 * 详细信息
	 */
	private String detailInfo;


	/**
	 * 中转地（包括出发地和目的地）
	 */
//	@OneToMany(mappedBy = "carMessage", cascade = {CascadeType.ALL})
//	@OneToMany(mappedBy = "carMessage")
//	private List<RoadRoute> routes;
	private List<RoadRouteResponse> routes;

//	public List<RoadRoute> getRoutes() {
//		return routes;
//	}
//
//	public void setRoutes(List<RoadRoute> routes) {
//		this.routes = routes;
//	}

	public Long getId() {
		return id;
	}

	public List<RoadRouteResponse> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RoadRouteResponse> routes) {
		this.routes = routes;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getCarMode() {
		return carMode;
	}

	public void setCarMode(String carMode) {
		this.carMode = carMode;
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

	public String getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
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

	public String getExceptFee() {
		return exceptFee;
	}

	public void setExceptFee(String exceptFee) {
		this.exceptFee = exceptFee;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
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

	


}
