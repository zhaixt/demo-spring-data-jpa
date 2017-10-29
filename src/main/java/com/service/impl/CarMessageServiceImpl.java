package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.RoadRoute;
import com.model.response.CarMessageResponse;
import com.model.response.RoadRouteResponse;
import com.repository.CarInfoRepository;
import com.repository.CommonRepository;
import com.repository.RoadRouteRepository;
import com.service.CarMessageService;
import com.util.RouteType;

@Service
public class CarMessageServiceImpl implements CarMessageService {

	@Autowired
	private CarInfoRepository carInfoRepository;

	@Autowired
	private CommonRepository commonRepository;

	@Autowired
	private RoadRouteRepository roadRouteRepository;

	@Override
	public BaseResponse<CarMessage> findById(Long id) {
		BaseResponse<CarMessage> resposne = new BaseResponse<CarMessage>(Boolean.TRUE);
		try {
			CarMessage product = carInfoRepository.findOne(id);
			resposne.setResult(product);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("发布货源信息失败");
		}
		return resposne;
	}

	@Override
	public BaseResponse<List<CarMessageResponse>> findByPhoneNum(String phoneNum, int currentPage, int pageSize) {
		BaseResponse<List<CarMessageResponse>> response = new BaseResponse<>(Boolean.TRUE);
		List<CarMessageResponse> carMessageResponseList = new ArrayList<>();
		try {
			StringBuffer nativeSqlBf = new StringBuffer();
			nativeSqlBf.append("select car_msg.* from car_message car_msg ");



			nativeSqlBf.append(" WHERE 1=1 ");
			if(!StringUtils.isEmpty(phoneNum)){
				nativeSqlBf.append(" AND phone_num = '"+phoneNum+"'");
			}

			nativeSqlBf.append(" ORDER BY car_msg.load_time DESC");
			if(!StringUtils.isEmpty(currentPage)&&!StringUtils.isEmpty(currentPage)){
				nativeSqlBf.append( " limit "+currentPage*pageSize+","+pageSize);
			}
//			String nativeSql = String.format(nativeSqlBf.toString(), startPlace, endPlace);


			List<CarMessage> carMessagesList = commonRepository.findItems(nativeSqlBf.toString(), CarMessage.class);
			for(CarMessage carMessage:carMessagesList){
				CarMessageResponse carMessageResponse = new CarMessageResponse();

				if(carMessage.getUserId() != 0){
					carMessageResponse.setUserId(carMessage.getUserId());
				}
				if(carMessage.getPhoneNum() != null){
					carMessageResponse.setPhoneNum(carMessage.getPhoneNum());
				}
				if(carMessage.getCarMode() != null){
					carMessageResponse.setCarMode(carMessage.getCarMode());
				}
				if(carMessage.getDeparturePlace() != null){
					carMessageResponse.setDeparturePlace(carMessage.getDeparturePlace());
				}
				if(carMessage.getDestinationPlace() != null){
					carMessageResponse.setDestinationPlace(carMessage.getDestinationPlace());
				}
				if(carMessage.getLoadTime() != null){
					carMessageResponse.setLoadTime(carMessage.getLoadTime());
				}
				if(carMessage.getCarType() != null){
					carMessageResponse.setCarType(carMessage.getCarType());
				}
				if(carMessage.getCarLength() != null){
					carMessageResponse.setCarLength(carMessage.getCarLength());
				}
				if(carMessage.getExceptFee() != null){
					carMessageResponse.setExceptFee(carMessage.getExceptFee());
				}
				if(carMessage.getDetailInfo() != null){
					carMessageResponse.setDetailInfo(carMessage.getDetailInfo());
				}

				if(carMessage.getRoutes() != null){
					if(carMessage.getRoutes().size() > 0){
						List<RoadRouteResponse> roadRouteResponseList = new ArrayList<RoadRouteResponse>();
						for(RoadRoute roadRoute : carMessage.getRoutes()){
							RoadRouteResponse roadRouteResponse = new RoadRouteResponse();
							if(roadRoute.getCityCode() != null){
								roadRouteResponse.setCityCode(roadRoute.getCityCode());
							}
							if(roadRoute.getCityName() != null){
								roadRouteResponse.setCityName(roadRoute.getCityName());
							}
							if(roadRoute.getCityType() > 0 ){
								roadRouteResponse.setCityType(roadRoute.getCityType());
							}
							if(roadRoute.getCityIndex() > 0 ){
								roadRouteResponse.setCityIndex(roadRoute.getCityIndex());
							}
							roadRouteResponseList.add(roadRouteResponse);
						}
						carMessageResponse.setRoutes(roadRouteResponseList);
					}
				}
				carMessageResponseList.add(carMessageResponse);
			}

			response.setResult(carMessageResponseList);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("查询失败");
			System.out.println("find car error: " + e);
		}
		return response;
	}
	@Override
	public BaseResponse<String> publishCarInfo(CarMessage carMessage) {
		BaseResponse<String> resposne = new BaseResponse<String>(Boolean.TRUE);
		try {
			CarMessage savedCarMsg = carInfoRepository.save(carMessage);

			// 保存出发地
			RoadRoute startRoute = new RoadRoute();
			startRoute.setCityType(RouteType.START_PLACE.getTypeCode());
			startRoute.setCityIndex(0);
			startRoute.setCityCode(carMessage.getDeparturePlace());
			startRoute.setCarMessage(savedCarMsg);
			roadRouteRepository.save(startRoute);

			// 保存途径地
			int index = 1;
			if (!CollectionUtils.isEmpty(carMessage.getRoutes())) {
				for (RoadRoute item : carMessage.getRoutes()) {
					RoadRoute middleRoute = new RoadRoute();
					middleRoute.setCityType(RouteType.MIDDLE_PLACE.getTypeCode());
					middleRoute.setCityIndex(index);
					middleRoute.setCityCode(item.getCityCode());
					middleRoute.setCityName(item.getCityName());
					middleRoute.setCarMessage(savedCarMsg);
					index++;
					roadRouteRepository.save(middleRoute);
				}

			}

			// 保存目的地
			RoadRoute endRoute = new RoadRoute();
			endRoute.setCityType(RouteType.END_PLACE.getTypeCode());
			endRoute.setCityIndex(index);
			endRoute.setCarMessage(savedCarMsg);
			endRoute.setCityCode(carMessage.getDestinationPlace());
			roadRouteRepository.save(endRoute);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("发布车辆信息失败");
		}
		return resposne;
	}

	@Override
	public BaseResponse<List<CarMessageResponse>> findCarByStartAndEndPlace(String carMode,String startPlace,String endPlace,String carType,String loadTime,Integer reportNum,int currentPage,int pageSize) {
		BaseResponse<List<CarMessageResponse>> response = new BaseResponse<>(Boolean.TRUE);
		List<CarMessageResponse> carMessageResponseList = new ArrayList<>();
		try {
			StringBuffer nativeSqlBf = new StringBuffer();
			nativeSqlBf.append("select car_msg.* from car_message car_msg ");
			
			
			if(StringUtils.isEmpty(startPlace)&&StringUtils.isEmpty(endPlace)){
//				nativeSqlBf.append("select car_msg.* from car_message car_msg ");
			}else if((!StringUtils.isEmpty(startPlace))&&StringUtils.isEmpty(endPlace)){//有出发地
				nativeSqlBf.append(" inner join ")
				.append("(SELECT r1.car_message_id FROM road_route r1 Where r1.city_code = '"+startPlace+"' AND r1.city_type < 2) ")
				.append("routes on car_msg.id=routes.car_message_id");
			}else if(StringUtils.isEmpty(startPlace)&&(!StringUtils.isEmpty(endPlace))){//有目的地
				nativeSqlBf.append(" inner join ")
				.append("(SELECT r1.car_message_id FROM road_route r1 Where r1.city_code = '"+endPlace+"' AND r1.city_type > 0) ")
				.append("routes on car_msg.id=routes.car_message_id");
			}else {
				nativeSqlBf.append(" inner join ")
					.append("(SELECT r1.car_message_id FROM road_route r1 INNER JOIN road_route r2 ON r1.car_message_id = r2.car_message_id ")
					.append("AND r1.city_code = '"+startPlace+"' AND r1.city_type < 2 AND r2.city_code = '"+endPlace+"' AND r2.city_type > 0 AND r1.city_index<r2.city_index) ")
					.append("routes on car_msg.id=routes.car_message_id");
			}
			nativeSqlBf.append(" WHERE 1=1 ");
			if(!StringUtils.isEmpty(carMode)){
				nativeSqlBf.append(" AND car_mode = '"+carMode+"'");
			}
			if(!StringUtils.isEmpty(loadTime)){
				nativeSqlBf.append(" AND load_time = '"+loadTime+"'");
			}
			if(!StringUtils.isEmpty(carType)){
				nativeSqlBf.append(" AND car_type = '"+carType+"'");
			}

			if(null != reportNum && reportNum > 0){
				nativeSqlBf.append(" AND report_num >= '"+reportNum+"'");
			}

			nativeSqlBf.append(" ORDER BY car_msg.load_time DESC");
			if(!StringUtils.isEmpty(currentPage)&&!StringUtils.isEmpty(currentPage)){
				nativeSqlBf.append( " limit "+currentPage*pageSize+","+pageSize);
			}
//			String nativeSql = String.format(nativeSqlBf.toString(), startPlace, endPlace);
			
			
			List<CarMessage> carMessagesList = commonRepository.findItems(nativeSqlBf.toString(), CarMessage.class);
			for(CarMessage carMessage:carMessagesList){
				CarMessageResponse carMessageResponse = new CarMessageResponse();
				if(carMessage.getId() != null){
					carMessageResponse.setId(carMessage.getId());
				}
				if(carMessage.getUserId() != 0){
					carMessageResponse.setUserId(carMessage.getUserId());
				}
				if(carMessage.getPhoneNum() != null){
					carMessageResponse.setPhoneNum(carMessage.getPhoneNum());
				}
				if(carMessage.getCarMode() != null){
					carMessageResponse.setCarMode(carMessage.getCarMode());
				}
				if(carMessage.getDeparturePlace() != null){
					carMessageResponse.setDeparturePlace(carMessage.getDeparturePlace());
				}
				if(carMessage.getDestinationPlace() != null){
					carMessageResponse.setDestinationPlace(carMessage.getDestinationPlace());
				}
				if(carMessage.getLoadTime() != null){
					carMessageResponse.setLoadTime(carMessage.getLoadTime());
				}
				if(carMessage.getCarType() != null){
					carMessageResponse.setCarType(carMessage.getCarType());
				}
				if(carMessage.getCarLength() != null){
					carMessageResponse.setCarLength(carMessage.getCarLength());
				}
				if(carMessage.getExceptFee() != null){
					carMessageResponse.setExceptFee(carMessage.getExceptFee());
				}
				if(carMessage.getDetailInfo() != null){
					carMessageResponse.setDetailInfo(carMessage.getDetailInfo());
				}
				if(carMessage.getReportNum() != null){
					carMessageResponse.setReportNum(carMessage.getReportNum());
				}
				if(carMessage.getRoutes() != null){
					if(carMessage.getRoutes().size() > 0){
						List<RoadRouteResponse> roadRouteResponseList = new ArrayList<RoadRouteResponse>();
						for(RoadRoute roadRoute : carMessage.getRoutes()){
							RoadRouteResponse roadRouteResponse = new RoadRouteResponse();
							if(roadRoute.getCityCode() != null){
								roadRouteResponse.setCityCode(roadRoute.getCityCode());
							}
							if(roadRoute.getCityName() != null){
								roadRouteResponse.setCityName(roadRoute.getCityName());
							}
							if(roadRoute.getCityType() > 0 ){
								roadRouteResponse.setCityType(roadRoute.getCityType());
							}
							if(roadRoute.getCityIndex() > 0 ){
								roadRouteResponse.setCityIndex(roadRoute.getCityIndex());
							}
							roadRouteResponseList.add(roadRouteResponse);
						}
						carMessageResponse.setRoutes(roadRouteResponseList);
					}
				}
				carMessageResponseList.add(carMessageResponse);
			}
			
			response.setResult(carMessageResponseList);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("查询失败");
			System.out.println("find car error: " + e);
		}
		return response;
	}

	/**
	 * 删除车源信息
	 *
	 * @param id
	 */
	public BaseResponse<String> deleteCarMessageInfo(Long id){
		BaseResponse<String> resposne = new BaseResponse<>(Boolean.TRUE);
		try {
			//级联删除
			carInfoRepository.delete(id);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("删除车源信息失败");

		}
		return resposne;
	}

	/**
	 * 举报车源信息
	 *
	 * @param id
	 */
	public BaseResponse<Long> reportCarMessageInfo(Long id){
		BaseResponse<Long> response = new BaseResponse<>(Boolean.TRUE);
		try {
			//级联删除
			CarMessage carMessage = carInfoRepository.findOne(id);
			Long reportNum;
			if(null == carMessage.getReportNum()){
				reportNum = 1L;
			}else {
				reportNum = carMessage.getReportNum() + 1;
			}

			carMessage.setReportNum(reportNum);
			carInfoRepository.save(carMessage);
			response.setResult(reportNum);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("举报车源信息失败");

		}
		return response;
	}
}
