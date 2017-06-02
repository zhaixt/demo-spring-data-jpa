package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.model.CarMessage;

/**
 * 
 * @author liangz
 *
 */
public interface CarInfoRepository extends PagingAndSortingRepository<CarMessage, Long> {
	/**
	 * 根据出发地，目的地和装载时间查找车辆
	 * 
	 * @param departurePlace
	 * @param destinationPlace
	 * @param loadTime
	 * @param pageable
	 * @return
	 */
	public Page<CarMessage> findByDeparturePlaceAndDestinationPlaceAndLoadTime(String departurePlace,
			String destinationPlace, String loadTime, Pageable pageable);

	/**
	 * 根据手机号码查找车辆
	 * @param phoneNum
	 * @param pageable
	 * @return
	 */
	public Page<CarMessage> findByPhoneNum( String phoneNum,Pageable pageable);

	public Page<CarMessage> findAll(Specification<CarMessage> specification, Pageable pageable);

}
