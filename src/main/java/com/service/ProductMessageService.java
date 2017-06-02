package com.service;

import org.springframework.data.domain.Page;

import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.ProductMessage;

import java.util.List;

/**
 * 
 * @author liangz 货源信息service
 *
 */
public interface ProductMessageService {

	/**
	 * 分页查询
	 * 
	 * @param departurePlace
	 * @param destinationPlace
	 * @param loadDate
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<ProductMessage> findByDeparturePlaceAndDestinationPlaceAndLoadTime(String departurePlace,
			String destinationPlace, String loadDate, int currentPage, int pageSize);


	/**
	 * 分页查询
	 *
	 * @param phoneNum
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<ProductMessage> findByPhoneNum(String phoneNum, int currentPage, int pageSize);
	/**
	 * 分页查询
	 * 
	 * @param departurePlace
	 * @param destinationPlace
	 * @param loadTime
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	//public Page<ProductMessage> findByDeparturePlaceAndDestinationPlaceAndLoadTimefindByDeparturePlaceAndDestinationPlaceAndLoadTime(String departurePlace,
	//		String destinationPlace, String loadTime,String carType, int currentPage, int pageSize);
	/**
	 * 发布货源信息
	 * 
	 * @param message
	 */
	public BaseResponse<String> publishProductInfo(ProductMessage message);

	/**
	 * 删除货源信息
	 *
	 * @param id
	 */
	public BaseResponse<String> deleteProductInfo(Long id);

	/**
	 * 查询货源详细信息
	 * 
	 * @param id
	 * @return
	 */
	public BaseResponse<ProductMessage> findById(Long id);
	
	/**
	 * 分页查询,根据不同条件
	 * 
	 * @param departurePlace
	 * @param destinationPlace
	 * @param loadDate
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<ProductMessage> findByPage(String productMode,String departurePlace,
			String destinationPlace, String carType,String loadDate, int currentPage, int pageSize);

	/**
	 * 举报货源信息
	 *
	 * @param id
	 */
	public BaseResponse<Long> reportProductInfo(Long id);

	/**
	 * 根据货源id列表分页查找货源信息
	 * @param idList
	 * @param currentPage
	 * @param pageSize
	 */
//	public Page<ProductMessage> findByIdList(List<Long> idList, int currentPage, int pageSize);


	}
