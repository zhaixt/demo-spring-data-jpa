package com.service;

import java.util.List;

import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.response.CarMessageResponse;
import org.springframework.data.domain.Page;

/**
 * 
 * @author liangz 货源信息service
 *
 */
public interface CarMessageService {

	/**
	 * 根据手机号码查询货源详细信息
	 * 
	 * @param phoneNum
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public BaseResponse<List<CarMessageResponse>> findByPhoneNum(String phoneNum, int currentPage, int pageSize);

	/**
	 * 查询货源详细信息
	 *
	 * @param id
	 * @return
	 */
	public BaseResponse<CarMessage> findById(Long id);
	/**
	 * 发布车辆信息
	 * 
	 * @param carMessage
	 * @return
	 */
	public BaseResponse<String> publishCarInfo(CarMessage carMessage);

	/**
	 * 根据出发地和目的地查询车源信息
	 * 
	 * @param startPlace
	 *            出发地
	 * @param endPlace
	 *            目的地
	 * @return
	 */
	public BaseResponse<List<CarMessageResponse>> findCarByStartAndEndPlace(String carMode,String startPlace,String endPlace,String carType,String loadTime,Integer reportNum,int currentPage,int pageSize);
	/**
	 * 删除车源信息
	 *
	 * @param id
	 */
	public BaseResponse<String> deleteCarMessageInfo(Long id);

	/**
	 * 举报车源信息
	 *
	 * @param id
	 */
	public BaseResponse<Long> reportCarMessageInfo(Long id);
}
