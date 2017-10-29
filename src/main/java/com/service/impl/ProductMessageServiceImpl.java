package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import org.springframework.util.StringUtils;

import com.comm.response.BaseResponse;
import com.model.ProductMessage;
import com.repository.ProductInfoRepository;
import com.service.ProductMessageService;

@Service
public class ProductMessageServiceImpl implements ProductMessageService {

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Override
	public Page<ProductMessage> findByDeparturePlaceAndDestinationPlaceAndLoadTime(String departurePlace,
			String destinationPlace, String loadDate, int currentPage, int pageSize) {
		Page<ProductMessage> productMessages = productInfoRepository.findByDeparturePlaceAndDestinationPlaceAndLoadDate(
				departurePlace, destinationPlace,loadDate, new PageRequest(currentPage, pageSize));
		return productMessages;
	}


	@Override
	public Page<ProductMessage> findByPhoneNum(String phoneNum, int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "loadDate");
		Page<ProductMessage> productMessages = productInfoRepository.findByPhoneNum(phoneNum, new PageRequest(currentPage, pageSize, sort));
		return productMessages;
	}
	@Override
	public BaseResponse<String> publishProductInfo(ProductMessage message) {
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		message.setGmtCreated(new Date());
		try {
			productInfoRepository.save(message);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("发布货源信息失败");
		}
		return response;
	}

	/**
	 * 删除货源信息
	 *
	 * @param id
	 */
	@Override
	public BaseResponse<String> deleteProductInfo(Long id){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		try {
			productInfoRepository.delete(id);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除货源信息失败");

		}
		return response;
	}

	@Override
	public BaseResponse<ProductMessage> findById(Long id) {
		BaseResponse<ProductMessage> resposne = new BaseResponse<ProductMessage>(Boolean.TRUE);
		try {
			ProductMessage product = productInfoRepository.findOne(id);
			resposne.setResult(product);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("发布货源信息失败");
		}
		return resposne;
	}
	
	@Override
	public Page<ProductMessage> findByPage(final String productMode,final String departurePlace,
			final String destinationPlace,final String carType, final String loadDate, final Integer reportNum, int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "loadDate");  
        PageRequest pageRequest =  new PageRequest(currentPage, pageSize, sort);
        return productInfoRepository.findAll(new Specification<ProductMessage>() {

			@Override
			public Predicate toPredicate(Root<ProductMessage> root,
										 CriteriaQuery<?> query, CriteriaBuilder builder) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(productMode)) {
					predicates.add(builder.equal(root.get("productMode"), productMode));
				}
				if (!StringUtils.isEmpty(departurePlace)) {
					predicates.add(builder.equal(root.get("departurePlace"), departurePlace));
				}
				if (!StringUtils.isEmpty(destinationPlace)) {
					predicates.add(builder.equal(root.get("destinationPlace"), destinationPlace));
				}
				if (!StringUtils.isEmpty(loadDate)) {
					predicates.add(builder.equal(root.get("loadDate"), loadDate));
				}
				if (!StringUtils.isEmpty(carType)) {
					predicates.add(builder.equal(root.get("carType"), carType));
				}
				if(null != reportNum && reportNum > 0){
					predicates.add(builder.gt(root.get("reportNum").as(Integer.class), reportNum-1));
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageRequest);
//        return null;
	}


	/**
	 * 举报货源信息
	 * @param id
	 */
	@Override
	public BaseResponse<Long> reportProductInfo(Long id){
		BaseResponse<Long> response = new BaseResponse<>(Boolean.TRUE);
		try {
			ProductMessage productMessage = productInfoRepository.findOne(id);
			Long reportNum;
			if(null == productMessage.getReportNum()){
				reportNum = 1L;
			}else {
				reportNum = productMessage.getReportNum() + 1;
			}

			productMessage.setReportNum(reportNum);
			productInfoRepository.save(productMessage);
			response.setResult(reportNum);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除货源信息失败");

		}
		return response;
	}

}
