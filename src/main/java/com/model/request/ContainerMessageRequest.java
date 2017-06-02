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
 * Created by zhaixt on 2016-11-9. 集装箱信息
 */


public class ContainerMessageRequest implements Serializable {

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
 	 * 装卸货地址
 	 */
 	private String landingPlace;
 	/**
 	 * 装卸货时间
 	 */
 	private String landingDate;
 	

	/**
	 * 当前页面
	 */
	private int currentPage;
	
	/**
	 * 每个页面的条目数量
	 */
	private int pageSize;
	
	
 	
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
	public String getLandingPlace() {
		return landingPlace;
	}
	public void setLandingPlace(String landingPlace) {
		this.landingPlace = landingPlace;
	}
	public String getLandingDate() {
		return landingDate;
	}
	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
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
