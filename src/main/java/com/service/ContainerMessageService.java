package com.service;

import org.springframework.data.domain.Page;

import com.comm.response.BaseResponse;
import com.model.ContainerMessage;
import com.model.ProductMessage;

/**
 * 
 * @author zhaixiaotong 集装箱信息service
 *
 */
public interface ContainerMessageService {

	/**
	 * 发布集装箱信息
	 * 
	 * @param message
	 */
	public BaseResponse<String> publishContainerInfo(ContainerMessage message);

	/**
	 * 分页查询,根据不同条件
	 * 
	 * @param containerMode
	 * @param port
	 * @param transportType
	 * @param landingPlace
	 * @param landingDate
	 * @return
	 */
	public Page<ContainerMessage> findByPage(String containerMode,String port,
			String transportType, String landingPlace,String landingDate, int currentPage, int pageSize);


	/**
	 * 分页查询,根据手机号码
	 *
	 * @param phoneNum
	 * @return
	 */
	public Page<ContainerMessage> findByPhoneNumByPage(String phoneNum, int currentPage, int pageSize);

	/**
	* 删除货源信息
	* @param id
	*/
	public BaseResponse<String> deleteContainerInfo(Long id);

	/**
	 * 举报集装箱信息
	 * @param id
	 */
	public BaseResponse<Long> reportContainerInfo(Long id);
}
