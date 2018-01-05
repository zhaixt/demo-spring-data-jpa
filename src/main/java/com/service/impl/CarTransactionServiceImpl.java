package com.service.impl;

import com.comm.response.BaseResponse;
import com.model.CarTransaction;
import com.model.DriverRecruit;
import com.repository.CarTransactionRepository;
import com.repository.DriverRecruitRepository;
import com.service.CarTransactionService;
import com.service.DriverRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarTransactionServiceImpl implements CarTransactionService {

	@Autowired
	private CarTransactionRepository carTransactionRepository;


	@Override
	public BaseResponse<String> publisCarTransactionInfo(CarTransaction carTransaction) {
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		carTransaction.setGmtCreated(new Date());
		carTransaction.setGmtCreated(new Date());
		try {
			carTransactionRepository.save(carTransaction);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("发布二手车信息失败");
		}
		return response;
	}

	/**
	 * 删除货源信息
	 *
	 * @param id
	 */
	@Override
	public BaseResponse<String> deleteCarTransactionInfo(Long id){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		try {
			carTransactionRepository.delete(id);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除二手车信息失败");

		}
		return response;
	}


	@Override
	public Page<CarTransaction> findByPage(final String title, final String details, final Integer axisNum, final String type, final Integer year, int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "gmtCreated");
        PageRequest pageRequest =  new PageRequest(currentPage, pageSize, sort);
        return carTransactionRepository.findAll(new Specification<CarTransaction>() {

			@Override
			public Predicate toPredicate(Root<CarTransaction> root,
										 CriteriaQuery<?> query, CriteriaBuilder builder) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(title)) {
					predicates.add(builder.like((Path)root.get("title"), "%" +title+ "%"));
				}
				if (!StringUtils.isEmpty(type)) {
					predicates.add(builder.equal(root.get("type"),type ));
				}
				if (null != axisNum && axisNum >0) {
					predicates.add(builder.equal(root.get("axisNum"),axisNum ));
				}
				if (null != year && year >0) {
					predicates.add(builder.equal(root.get("year"),year ));
				}
				if (!StringUtils.isEmpty(details)) {
					predicates.add(builder.like((Path)root.get("details"), "%" + details+ "%"));
				}

				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageRequest);
//        return null;
	}




}
