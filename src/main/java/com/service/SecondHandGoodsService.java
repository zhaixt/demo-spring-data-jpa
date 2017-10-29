package com.service;


import com.comm.response.BaseResponse;
import com.model.ProductMessage;
import com.model.SecondHandGoods;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SecondHandGoodsService {
	/**
	 * 发布二手物品信息
	 * @param secondHandGoods
	 */
	public BaseResponse<String> publishSecondHandGoodsInfo(SecondHandGoods secondHandGoods);

	/**
	 * 删除二手物品信息
	 * @param id
	 */
	public BaseResponse<String> deleteSecondHandGoodInfo(Long id);


	/**
	 * 分页查询,根据不同条件
	 *
	 * @param title
	 * @param details
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<SecondHandGoods> findByPage(String title, String details,int currentPage, int pageSize);
}
