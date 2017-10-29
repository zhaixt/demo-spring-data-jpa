package com.controllter;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.ProductMessage;
import com.model.request.ProductMessageRequest;
import com.service.ProductMessageService;

/**
 * 发布货源信息
 * 
 * @author liangz
 * 
 */

@RestController
@RequestMapping(value = "/products")
public class ProductInfoController implements Serializable {

	private static final long serialVersionUID = 3419626567522525664L;

	@Autowired
	public ProductMessageService productMessageService;

	@RequestMapping(value = "/publish")
	public BaseResponse<String> publicProductInfo(@RequestBody ProductMessage productMessage) {
		return productMessageService.publishProductInfo(productMessage);
	}


	@RequestMapping(value = "/id/{id}")
	public BaseResponse<ProductMessage> findDetailById(@PathVariable Long id) {
		return productMessageService.findById(id);
	}
	
	/**
	 * 根据出发地、目的地、装车时间分页查询货源信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<ProductMessage> queryProducts(@RequestBody ProductMessageRequest productMessageRequest) {
		
		String productMode = productMessageRequest.getProductMode();
		String startPlace = productMessageRequest.getDeparturePlace();
		String endPlace = productMessageRequest.getDestinationPlace();
		String loadDate = productMessageRequest.getLoadDate();
		String carType = productMessageRequest.getCarType();
		Integer reportNum = productMessageRequest.getReportNum();
		int currentPage = productMessageRequest.getCurrentPage();
		
		int pageSize = productMessageRequest.getPageSize();
//		return productMessageService.findByDeparturePlaceAndDestinationPlaceAndLoadTime(startPlace,endPlace,loadDate,currentPage,pageSize);
		return productMessageService.findByPage(productMode, startPlace, endPlace, carType, loadDate,reportNum,currentPage, pageSize);
//		return null;
	}


	@RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
	public BaseResponse<String> deleteProductInfo(@PathVariable Long id) {
		return productMessageService.deleteProductInfo(id);
	}

	@RequestMapping(value = "/report/id/{id}",method = RequestMethod.GET)
	public BaseResponse<Long> reportProductInfo(@PathVariable Long id) {
		return productMessageService.reportProductInfo(id);
	}
}
