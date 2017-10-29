package com.service.impl;

import com.comm.response.BaseResponse;
import com.model.DriverRecruit;
import com.model.SecondHandGoods;
import com.repository.DriverRecruitRepository;
import com.repository.SecondHandGoodsRepository;
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
public class DriverRecruitServiceImpl implements DriverRecruitService {

	@Autowired
	private DriverRecruitRepository driverRecruitRepository;


	@Override
	public BaseResponse<String> publishDriverRecruitInfo(DriverRecruit driverRecruit) {
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		driverRecruit.setGmtCreated(new Date());
		driverRecruit.setGmtCreated(new Date());
		try {
			driverRecruitRepository.save(driverRecruit);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("发布司机信息失败");
		}
		return response;
	}

	/**
	 * 删除货源信息
	 *
	 * @param id
	 */
	@Override
	public BaseResponse<String> deleteDriverRecruitInfo(Long id){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		try {
			driverRecruitRepository.delete(id);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除司机信息失败");

		}
		return response;
	}


	@Override
	public Page<DriverRecruit> findByPage(final String title,final String details,int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "gmtCreated");
        PageRequest pageRequest =  new PageRequest(currentPage, pageSize, sort);
        return driverRecruitRepository.findAll(new Specification<DriverRecruit>() {

			@Override
			public Predicate toPredicate(Root<DriverRecruit> root,
										 CriteriaQuery<?> query, CriteriaBuilder builder) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(title)) {
					predicates.add(builder.like((Path)root.get("title"), "%" +title+ "%"));
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
