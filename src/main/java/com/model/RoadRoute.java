package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.io.Serializable;

/**
 * Created by zhangliang on 2016-9-17. 货车途径地对象
 */

@Entity
public class RoadRoute implements Serializable {

	private static final long serialVersionUID = 4974286391931851702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 途径城市编码
	 */
	private String cityCode;
	/**
	 * 途径城市名称
	 */
	private String cityName;

	/**
	 * 途径城市类型，0代表出发站，1代表途径地，2代表目的地
	 */
	private int cityType;

	/**
	 * 途径地顺序，C--D，那么C的cityIndex要小于D的cityIndex
	 */
	private int cityIndex;

	/**
	 * 关联的车辆信息
	 */
	@ManyToOne
//	@ManyToOne(cascade = {CascadeType.ALL})
	private CarMessage carMessage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getCityType() {
		return cityType;
	}

	public void setCityType(int cityType) {
		this.cityType = cityType;
	}

	public int getCityIndex() {
		return cityIndex;
	}

	public void setCityIndex(int cityIndex) {
		this.cityIndex = cityIndex;
	}

	public CarMessage getCarMessage() {
		return carMessage;
	}

	public void setCarMessage(CarMessage carMessage) {
		this.carMessage = carMessage;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
