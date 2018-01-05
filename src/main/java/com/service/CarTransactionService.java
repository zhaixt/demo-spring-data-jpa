package com.service;


import com.comm.response.BaseResponse;
import com.model.CarTransaction;
import org.springframework.data.domain.Page;

public interface CarTransactionService {
	/**
	 * 发布二手车信息
	 * @param carTransaction
	 */
	public BaseResponse<String> publisCarTransactionInfo(CarTransaction carTransaction);

	/**
	 * 删除二手车信息
	 * @param id
	 */
	public BaseResponse<String> deleteCarTransactionInfo(Long id);


	/**
	 * 分页查询,根据不同条件
	 *
	 * @param title
	 * @param details
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<CarTransaction> findByPage(String title, String details,Integer axisNum,String type,Integer year, int currentPage, int pageSize);
}
