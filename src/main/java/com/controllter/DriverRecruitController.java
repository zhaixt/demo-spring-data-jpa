package com.controllter;

import com.comm.response.BaseResponse;
import com.model.DriverRecruit;
import com.model.SecondHandGoods;
import com.model.request.DriverRecruitRequest;
import com.model.request.SecondHandGoodsRequest;
import com.service.DriverRecruitService;
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
@RequestMapping(value = "/driver_recruit")
public class DriverRecruitController implements Serializable {

	private static final long serialVersionUID = 3419626567522525664L;

	@Autowired
	public DriverRecruitService driverRecruitService;

	@RequestMapping(value = "/publish")
	public BaseResponse<String> publicProductInfo(@RequestBody DriverRecruit driverRecruit) {
		return driverRecruitService.publishDriverRecruitInfo(driverRecruit);
	}



	/**
	 * 根据标题、详细信息分页查询司机招聘信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<DriverRecruit> queryProducts(@RequestBody DriverRecruitRequest driverRecruitRequest) {
		
		String title = driverRecruitRequest.getTitle();
		String details = driverRecruitRequest.getDetails();

		int currentPage = driverRecruitRequest.getCurrentPage();
		
		int pageSize = driverRecruitRequest.getPageSize();
//		return productMessageService.findByDeparturePlaceAndDestinationPlaceAndLoadTime(startPlace,endPlace,loadDate,currentPage,pageSize);
		return driverRecruitService.findByPage(title, details, currentPage, pageSize);
//		return null;
	}


	@RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
	public BaseResponse<String> deleteProductInfo(@PathVariable Long id) {
		return driverRecruitService.deleteDriverRecruitInfo(id);
	}

}
