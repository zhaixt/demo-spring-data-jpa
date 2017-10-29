package com.controllter;

import com.comm.response.BaseResponse;
import com.model.ProductMessage;
import com.model.SecondHandGoods;
import com.model.request.ProductMessageRequest;
import com.model.request.SecondHandGoodsRequest;
import com.service.ProductMessageService;
import com.service.SecondHandGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 发布二手货物信息
 * 
 * @author zhaixt
 * 
 */

@RestController
@RequestMapping(value = "/second_hand")
public class SecondHandGoodsController implements Serializable {

	private static final long serialVersionUID = 3419626567522525664L;

	@Autowired
	public SecondHandGoodsService secondHandGoodsService;

	@RequestMapping(value = "/publish")
	public BaseResponse<String> publicProductInfo(@RequestBody SecondHandGoods secondHandGoods) {
		return secondHandGoodsService.publishSecondHandGoodsInfo(secondHandGoods);
	}



	/**
	 * 根据标题、详细信息分页查询二手货物信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<SecondHandGoods> queryProducts(@RequestBody SecondHandGoodsRequest secondHandGoodsRequest) {
		
		String title = secondHandGoodsRequest.getTitle();
		String details = secondHandGoodsRequest.getDetails();

		int currentPage = secondHandGoodsRequest.getCurrentPage();
		
		int pageSize = secondHandGoodsRequest.getPageSize();
//		return productMessageService.findByDeparturePlaceAndDestinationPlaceAndLoadTime(startPlace,endPlace,loadDate,currentPage,pageSize);
		return secondHandGoodsService.findByPage(title, details, currentPage, pageSize);
//		return null;
	}


	@RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
	public BaseResponse<String> deleteProductInfo(@PathVariable Long id) {
		return secondHandGoodsService.deleteSecondHandGoodInfo(id);
	}

}
