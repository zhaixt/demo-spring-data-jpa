package com.service.impl;


import com.comm.response.BaseResponse;
import com.model.*;
import com.model.request.PersonalCollectionRequest;
import com.model.response.CarMessageResponse;
import com.model.response.RoadRouteResponse;
import com.repository.*;

import com.service.PersonalCollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonalCollectionServiceImpl implements PersonalCollectionService{
	@Autowired
	private PersonalCollectionRepository personalCollectionRepository;

	@Autowired
	private ProductInfoRepository productInfoRepository;
	@Autowired
	private ContainerInfoRepository containerInfoRepository;
	@Autowired
	private CarInfoRepository carInfoRepository;
	@Autowired
	private CommonRepository commonRepository;
	@Override
	public List<PersonalCollection> findByPhoneNumAndCollectOptionType(String phoneNum,String collectOptionType){
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionType(phoneNum,collectOptionType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return personalCollectionList;

	}
	@Override
	public BaseResponse<String> savePersonalCollection(PersonalCollection personalCollection){

		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionTypeAndCollectOptionId(personalCollection.getPhoneNum(),personalCollection.getCollectOptionType(),personalCollection.getCollectOptionId());
			if(personalCollectionList!= null && personalCollectionList.size()>1){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("已收藏此栏目");
				return response;
			}
			personalCollection.setDate(new Date());
			personalCollectionRepository.save(personalCollection);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("收藏失败");

		}
		return response;
	}
	@Override
	public Page<ProductMessage> findCollectedProduct(PersonalCollectionRequest personalCollectionRequest) {
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionType(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType());
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("获取收藏的货物信息项目失败");
			return null;
		}
		Sort sort = new Sort(Sort.Direction.DESC, "loadDate");
		final List<Long> idList = new ArrayList<>();
		for (PersonalCollection personalCollection : personalCollectionList) {
			idList.add(personalCollection.getCollectOptionId());
		}
		final PageRequest pageRequest = new PageRequest(personalCollectionRequest.getCurrentPage(), personalCollectionRequest.getPageSize(), sort);
		return productInfoRepository.findAll(new Specification<ProductMessage>() {

			@Override
			public Predicate toPredicate(Root<ProductMessage> root,
										 CriteriaQuery<?> query, CriteriaBuilder builder) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
//					Iterator iterator = idList.iterator();

				if (idList != null && idList.size() > 0) {
					Iterator iterator = idList.iterator();
					CriteriaBuilder.In in = builder.in(root.get("id"));
					while (iterator.hasNext()) {
						in.value(iterator.next());
					}
					predicates.add(in);
				}else{
					CriteriaBuilder.In in = builder.in(root.get("id"));
					in.value(-1);//就是为了查空
					predicates.add(in);
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageRequest);
	}

//	@Override
//	public Page<CarMessage> findCollectedCar(PersonalCollectionRequest personalCollectionRequest) {
//		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
//		List<PersonalCollection> personalCollectionList;
//		try {
//			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionType(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType());
//		} catch (Exception e) {
//			response.setSuccess(Boolean.FALSE);
//			response.setFailMessage("获取收藏的车辆信息项目失败");
//			return null;
//		}
//		Sort sort = new Sort(Sort.Direction.DESC, "loadTime");
//		final List<Long> idList = new ArrayList<>();
//		for (PersonalCollection personalCollection : personalCollectionList) {
//			idList.add(personalCollection.getCollectOptionId());
//		}
//		final PageRequest pageRequest = new PageRequest(personalCollectionRequest.getCurrentPage(), personalCollectionRequest.getPageSize(), sort);
//
//		Page<CarMessage> carMessagesListByPage = carInfoRepository.findAll(new Specification<CarMessage>() {
//
//			@Override
//			public Predicate toPredicate(Root<CarMessage> root,
//										 CriteriaQuery<?> query, CriteriaBuilder builder) {
//				// TODO Auto-generated method stub
//				List<Predicate> predicates = new ArrayList<Predicate>();
////					Iterator iterator = idList.iterator();
//
//				if (idList != null && idList.size() > 0) {
//					Iterator iterator = idList.iterator();
//					CriteriaBuilder.In in = builder.in(root.get("id"));
//					while (iterator.hasNext()) {
//						in.value(iterator.next());
//					}
//					predicates.add(in);
//				}
//
//				query.where(predicates.toArray(new Predicate[predicates.size()]));
//				return null;
//			}
//		}, pageRequest);
//		List<CarMessage> carMessageList = carMessagesListByPage.getContent();
//		if(carMessageList != null && carMessageList.size()>1){
//			for(CarMessage carMessage:carMessageList){
//				CarMessageResponse carMessageResponse = new CarMessageResponse();
//
//				if(carMessage.getUserId() != 0){
//					carMessageResponse.setUserId(carMessage.getUserId());
//				}
//				if(carMessage.getPhoneNum() != null){
//					carMessageResponse.setPhoneNum(carMessage.getPhoneNum());
//				}
//				if(carMessage.getCarMode() != null){
//					carMessageResponse.setCarMode(carMessage.getCarMode());
//				}
//				if(carMessage.getDeparturePlace() != null){
//					carMessageResponse.setDeparturePlace(carMessage.getDeparturePlace());
//				}
//				if(carMessage.getDestinationPlace() != null){
//					carMessageResponse.setDestinationPlace(carMessage.getDestinationPlace());
//				}
//				if(carMessage.getLoadTime() != null){
//					carMessageResponse.setLoadTime(carMessage.getLoadTime());
//				}
//				if(carMessage.getCarType() != null){
//					carMessageResponse.setCarType(carMessage.getCarType());
//				}
//				if(carMessage.getCarLength() != null){
//					carMessageResponse.setCarLength(carMessage.getCarLength());
//				}
//				if(carMessage.getExceptFee() != null){
//					carMessageResponse.setExceptFee(carMessage.getExceptFee());
//				}
//				if(carMessage.getDetailInfo() != null){
//					carMessageResponse.setDetailInfo(carMessage.getDetailInfo());
//				}
//
//				if(carMessage.getRoutes() != null){
//					if(carMessage.getRoutes().size() > 0){
//						List<RoadRouteResponse> roadRouteResponseList = new ArrayList<RoadRouteResponse>();
//						for(RoadRoute roadRoute : carMessage.getRoutes()){
//							RoadRouteResponse roadRouteResponse = new RoadRouteResponse();
//							if(roadRoute.getCityCode() != null){
//								roadRouteResponse.setCityCode(roadRoute.getCityCode());
//							}
//							if(roadRoute.getCityName() != null){
//								roadRouteResponse.setCityName(roadRoute.getCityName());
//							}
//							if(roadRoute.getCityType() > 0 ){
//								roadRouteResponse.setCityType(roadRoute.getCityType());
//							}
//							if(roadRoute.getCityIndex() > 0 ){
//								roadRouteResponse.setCityIndex(roadRoute.getCityIndex());
//							}
//							roadRouteResponseList.add(roadRouteResponse);
//						}
//						carMessageResponse.setRoutes(roadRouteResponseList);
//					}
//				}
//		}
//
////			List<CarMessage> carMessageList = carInfoRepository.findAll(idList);
////			StringBuffer nativeSqlBf = new StringBuffer();
////			nativeSqlBf.append("select car_msg.* from car_message car_msg ");
////
////
////
////			nativeSqlBf.append(" WHERE 1=1 ");
////			if(!StringUtils.isEmpty(phoneNum)){
////				nativeSqlBf.append(" AND phone_num = '"+phoneNum+"'");
////			}
////
////			nativeSqlBf.append(" ORDER BY car_msg.load_time DESC");
////			if(!StringUtils.isEmpty(currentPage)&&!StringUtils.isEmpty(currentPage)){
////				nativeSqlBf.append( " limit "+currentPage*pageSize+","+pageSize);
////			}
////			String nativeSql = String.format(nativeSqlBf.toString(), startPlace, endPlace);
////			List<CarMessage> carMessagesList = commonRepository.findItems(nativeSqlBf.toString(), CarMessage.class);
//
//
////				carMessageResponseList.add(carMessageResponse);
//			}
//
////			response.setResult(carMessageResponseList);
//
//		return carMessagesListByPage;
//	}

	@Override
	public BaseResponse<List<CarMessageResponse>> findCollectedCar(PersonalCollectionRequest personalCollectionRequest) {
		int currentPage = personalCollectionRequest.getCurrentPage();
		int pageSize = personalCollectionRequest.getPageSize();
		BaseResponse<List<CarMessageResponse>> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionType(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType());
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("获取收藏的集装箱信息项目失败");
			return null;
		}
		Sort sort = new Sort(Sort.Direction.DESC, "loadTime");
		final List<Long> idList = new ArrayList<>();
		for (PersonalCollection personalCollection : personalCollectionList) {
			idList.add(personalCollection.getCollectOptionId());
		}
		List<CarMessageResponse> carMessageResponseList = new ArrayList<>();
		if(idList == null || idList.size() == 0){
			response.setResult(carMessageResponseList);
		}else {

			try {
				StringBuffer nativeSqlBf = new StringBuffer();
				nativeSqlBf.append("select car_msg.* from car_message car_msg ");

				nativeSqlBf.append(" WHERE 1=1 ");

				nativeSqlBf.append(" and  id  in( ");
					for(int i=0;i<idList.size();i++){
						nativeSqlBf.append("'"+idList.get(i)+"'");
						if(idList.size()-1!=i){
							nativeSqlBf.append(",");
						}
					}
				nativeSqlBf.append(")");

				nativeSqlBf.append(" ORDER BY car_msg.load_time DESC");
				if (!StringUtils.isEmpty(currentPage) && !StringUtils.isEmpty(currentPage)) {
					nativeSqlBf.append(" limit " + currentPage * pageSize + "," + pageSize);
				}
				//			String nativeSql = String.format(nativeSqlBf.toString(), startPlace, endPlace);


				List<CarMessage> carMessagesList = commonRepository.findItems(nativeSqlBf.toString(), CarMessage.class);
				for (CarMessage carMessage : carMessagesList) {
					CarMessageResponse carMessageResponse = new CarMessageResponse();
					if(carMessage.getId() != null){
						carMessageResponse.setId(carMessage.getId());
					}
					if (carMessage.getUserId() != 0) {
						carMessageResponse.setUserId(carMessage.getUserId());
					}
					if (carMessage.getPhoneNum() != null) {
						carMessageResponse.setPhoneNum(carMessage.getPhoneNum());
					}
					if (carMessage.getCarMode() != null) {
						carMessageResponse.setCarMode(carMessage.getCarMode());
					}
					if (carMessage.getDeparturePlace() != null) {
						carMessageResponse.setDeparturePlace(carMessage.getDeparturePlace());
					}
					if (carMessage.getDestinationPlace() != null) {
						carMessageResponse.setDestinationPlace(carMessage.getDestinationPlace());
					}
					if (carMessage.getLoadTime() != null) {
						carMessageResponse.setLoadTime(carMessage.getLoadTime());
					}
					if (carMessage.getCarType() != null) {
						carMessageResponse.setCarType(carMessage.getCarType());
					}
					if (carMessage.getCarLength() != null) {
						carMessageResponse.setCarLength(carMessage.getCarLength());
					}
					if (carMessage.getExceptFee() != null) {
						carMessageResponse.setExceptFee(carMessage.getExceptFee());
					}
					if (carMessage.getDetailInfo() != null) {
						carMessageResponse.setDetailInfo(carMessage.getDetailInfo());
					}

					if (carMessage.getRoutes() != null) {
						if (carMessage.getRoutes().size() > 0) {
							List<RoadRouteResponse> roadRouteResponseList = new ArrayList<RoadRouteResponse>();
							for (RoadRoute roadRoute : carMessage.getRoutes()) {
								RoadRouteResponse roadRouteResponse = new RoadRouteResponse();
								if (roadRoute.getCityCode() != null) {
									roadRouteResponse.setCityCode(roadRoute.getCityCode());
								}
								if (roadRoute.getCityName() != null) {
									roadRouteResponse.setCityName(roadRoute.getCityName());
								}
								if (roadRoute.getCityType() > 0) {
									roadRouteResponse.setCityType(roadRoute.getCityType());
								}
								if (roadRoute.getCityIndex() > 0) {
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
		}
		return response;

	}

	@Override
	public Page<ContainerMessage> findCollectedContainer(PersonalCollectionRequest personalCollectionRequest) {
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionType(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType());
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("获取收藏的集装箱信息项目失败");
			return null;
		}
		Sort sort = new Sort(Sort.Direction.DESC, "landingDate");
		final List<Long> idList = new ArrayList<>();
		for (PersonalCollection personalCollection : personalCollectionList) {
			idList.add(personalCollection.getCollectOptionId());
		}
		final PageRequest pageRequest = new PageRequest(personalCollectionRequest.getCurrentPage(), personalCollectionRequest.getPageSize(), sort);

		return containerInfoRepository.findAll(new Specification<ContainerMessage>() {

			@Override
			public Predicate toPredicate(Root<ContainerMessage> root,
										 CriteriaQuery<?> query, CriteriaBuilder builder) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
//					Iterator iterator = idList.iterator();

				if (idList != null && idList.size() > 0) {
					Iterator iterator = idList.iterator();
					CriteriaBuilder.In in = builder.in(root.get("id"));
					while (iterator.hasNext()) {
						in.value(iterator.next());
					}
					predicates.add(in);
				}else{
					CriteriaBuilder.In in = builder.in(root.get("id"));
					in.value(-1);//就是为了查空
					predicates.add(in);
				}

				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageRequest);
	}


	/**
	 * 删除收藏的货源信息
	 *
	 * @param
	 */
	@Override
	public BaseResponse<String> deleteCollectedProduct(PersonalCollection personalCollectionRequest){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionTypeAndCollectOptionId(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType(), personalCollectionRequest.getCollectOptionId());
			if(personalCollectionList == null || personalCollectionList.size() > 1){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("删除收藏的货源信息失败,查出的条数为：" + personalCollectionList.size());
//				return response;
			}
			personalCollectionRepository.delete(personalCollectionList.get(0).getId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除收藏的货源信息失败");

		}
		return response;
	}
	/**
	 * 删除收藏的车辆信息
	 *
	 * @param
	 */
	@Override
	public BaseResponse<String> deleteCollectedCar(PersonalCollection personalCollectionRequest){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionTypeAndCollectOptionId(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType(), personalCollectionRequest.getCollectOptionId());
			if(personalCollectionList == null || personalCollectionList.size() > 1){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("删除收藏的车辆信息失败,查出的条数为：" + personalCollectionList.size());
//				return response;
			}
			personalCollectionRepository.delete(personalCollectionList.get(0).getId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除收藏的车辆信息失败");

		}
		return response;
	}
	/**
	 * 删除收藏的集装箱信息
	 *
	 * @param
	 */
	@Override
	public BaseResponse<String> deleteCollectedContainer(PersonalCollection personalCollectionRequest){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		List<PersonalCollection> personalCollectionList;
		try {
			personalCollectionList = personalCollectionRepository.findByPhoneNumAndCollectOptionTypeAndCollectOptionId(personalCollectionRequest.getPhoneNum(), personalCollectionRequest.getCollectOptionType(), personalCollectionRequest.getCollectOptionId());
			if(personalCollectionList == null || personalCollectionList.size() > 1){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("删除收藏的集装箱信息失败,查出的条数为：" + personalCollectionList.size());
//				return response;
			}
			personalCollectionRepository.delete(personalCollectionList.get(0).getId());
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除收藏的集装箱信息失败");

		}
		return response;
	}
}
