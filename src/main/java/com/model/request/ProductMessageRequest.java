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


public class ProductMessageRequest implements Serializable {

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
	 * 装载时间（默认随时）
	 */
	private String loadDate;
	/**
	 * 需要车型
	 */
	private String carType;

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

	

	public String getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
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

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getProductMode() {
		return productMode;
	}

	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}

	
	

}
