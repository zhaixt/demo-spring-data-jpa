package com.service;


import com.comm.response.BaseResponse;
import com.model.DriverRecruit;
import org.springframework.data.domain.Page;

public interface DriverRecruitService {
	/**
	 * 发布驾驶员信息
	 * @param driverRecruit
	 */
	public BaseResponse<String> publishDriverRecruitInfo(DriverRecruit driverRecruit);

	/**
	 * 删除驾驶员信息
	 * @param id
	 */
	public BaseResponse<String> deleteDriverRecruitInfo(Long id);


	/**
	 * 分页查询,根据不同条件
	 *
	 * @param title
	 * @param details
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<DriverRecruit> findByPage(String title, String details, int currentPage, int pageSize);
}
