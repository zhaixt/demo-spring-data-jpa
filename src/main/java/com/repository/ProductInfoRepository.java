package com.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.model.ProductMessage;

import java.util.List;


/**
 * 
 * @author liangz
 *
 */
public interface ProductInfoRepository extends PagingAndSortingRepository<ProductMessage, Long> {
	/**
	 * 根据出发地，目的地和装载时间查找车辆
	 * 
	 * @param departurePlace
	 * @param destinationPlace
	 * @param loadDate
	 * @param pageable
	 * @return
	 */
	public Page<ProductMessage> findByDeparturePlaceAndDestinationPlaceAndLoadDate(String departurePlace,
			String destinationPlace, String loadDate, Pageable pageable);

	public Page<ProductMessage> findByPhoneNum(String phoneNum, Pageable pageable);

	public Page<ProductMessage> findByDeparturePlaceAndDestinationPlaceAndCarTypeAndLoadDate(
			String departurePlace, String destinationPlace, String carType,
			String loadTime, Pageable pageable);
	
	
	public Page<ProductMessage> findAll(Specification<ProductMessage> specification, Pageable pageable);
	//todo 这样不行
//	public Page<ProductMessage> findAll(Iterable<Long> ids, Pageable pageable); //根据id列表 查询所有的对象，返回List


	public Page<ProductMessage> findAll(Pageable pageable); //根据id列表 查询所有的对象，返回List
	public List<ProductMessage> findAll(Iterable<Long> ids); //根据id列表 查询所有的对象，返回List

}
