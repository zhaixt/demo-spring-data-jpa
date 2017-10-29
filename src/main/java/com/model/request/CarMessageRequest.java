package com.model.request;

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


public class CarMessageRequest implements Serializable {

	/**
  	 *专车、回程车、顺风车
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
	 * 车型
	 */
	private String carType;


	/**
	 * 装载时间（默认随时）
	 */
	private String loadTime;

	/**
	 * 满足按照被举报处理条目的举报数目
	 */
	private Integer reportNum;
	/**
	 * 当前页面
	 */
	private int currentPage;
	
	/**
	 * 每个页面的条目数量
	 */
	private int pageSize;
	

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

	public String getCarMode() {
		return carMode;
	}

	public void setCarMode(String carMode) {
		this.carMode = carMode;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getReportNum() {
		return reportNum;
	}

	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}
}
