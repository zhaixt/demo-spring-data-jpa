package com.controllter;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.request.CarMessageRequest;
import com.model.response.CarMessageResponse;
import com.service.CarMessageService;


/**
 * 发布车辆信息，找车 controller
 * 
 * @author liangz
 * 
 */

@RestController
@RequestMapping(value = "/message/car/")
public class CarMessageController implements Serializable {

	private static final long serialVersionUID = 3419626567522525664L;

	@Autowired
	public CarMessageService carMessageService;

//	@RequestMapping(value = "query", method = RequestMethod.GET)
//	public Page<CarMessage> queryCarIngosWithoutCarType(@RequestParam String departurePlace,
//			@RequestParam String destinationPlace, @RequestParam String loadTime, @RequestParam int currentPage,
//			@RequestParam int pageSize) {
//
//		return null;
//	}
	/**
	 * 根据出发地、目的地、装车时间分页查询车源信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<CarMessage> queryCarIngosWithoutCarType(@RequestBody CarMessageRequest queryCarMessageRequest) {

//		String startPlace = queryCarMessageRequest.getDeparturePlace();
//		String endPlace = queryCarMessageRequest.getDestinationPlace();
//		return carMessageService.findCarByStartAndEndPlace(startPlace, endPlace);
		return null;
	}
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public BaseResponse<String> publishCarInfo(@RequestBody CarMessage carMessage) {
		return carMessageService.publishCarInfo(carMessage);
	}

//	/**
//	 * 根据出发地和目的地查询车源信息
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/findByStartEnd", method = RequestMethod.GET)
//	public BaseResponse<List<CarMessage>> findCarMsgByStartAndEndPlace(@RequestParam String startPlace,
//			@RequestParam String endPlace) {
//		return carMessageService.findCarByStartAndEndPlace(startPlace, endPlace);
//	}
	/**
	 * 根据出发地和目的地查询车源信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findByStartEnd", method = RequestMethod.POST)
	public BaseResponse<List<CarMessageResponse>> findCarMsgByStartAndEndPlace(@RequestBody CarMessageRequest queryCarMessageRequest) {
		String startPlace = queryCarMessageRequest.getDeparturePlace();
		String endPlace = queryCarMessageRequest.getDestinationPlace();
		String carMode = queryCarMessageRequest.getCarMode();
		String carType = queryCarMessageRequest.getCarType();
		String loadTime = queryCarMessageRequest.getLoadTime();
		Integer reportNum = queryCarMessageRequest.getReportNum();
		int currentPage = queryCarMessageRequest.getCurrentPage();
		int pageSize = queryCarMessageRequest.getPageSize(); 
		BaseResponse<List<CarMessageResponse>> result = carMessageService.findCarByStartAndEndPlace(carMode,startPlace, endPlace,carType,loadTime,reportNum,currentPage,pageSize);
		
		return result;
	}

	@RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
	public BaseResponse<String> deleteCarMessageInfo(@PathVariable Long id) {
		return carMessageService.deleteCarMessageInfo(id);
	}

	@RequestMapping(value = "/report/id/{id}",method = RequestMethod.GET)
	public BaseResponse<Long> reportCarMessageInfo(@PathVariable Long id) {
		return carMessageService.reportCarMessageInfo(id);
	}

}
