package com.service.impl;

import com.comm.response.BaseResponse;
import com.model.ProductMessage;
import com.model.SecondHandGoods;
import com.repository.ProductInfoRepository;
import com.repository.SecondHandGoodsRepository;
import com.service.ProductMessageService;
import com.service.SecondHandGoodsService;
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
public class SecondHandGoodsServiceImpl implements SecondHandGoodsService {

	@Autowired
	private SecondHandGoodsRepository secondHandGoodsRepository;


	@Override
	public BaseResponse<String> publishSecondHandGoodsInfo(SecondHandGoods secondHandGoods) {
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		secondHandGoods.setGmtCreated(new Date());
		secondHandGoods.setGmtCreated(new Date());
		try {
			secondHandGoodsRepository.save(secondHandGoods);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("发布二手货物信息失败");
		}
		return response;
	}

	/**
	 * 删除货源信息
	 *
	 * @param id
	 */
	@Override
	public BaseResponse<String> deleteSecondHandGoodInfo(Long id){
		BaseResponse<String> response = new BaseResponse<>(Boolean.TRUE);
		try {
			secondHandGoodsRepository.delete(id);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("删除二手货物信息失败");

		}
		return response;
	}


	@Override
	public Page<SecondHandGoods> findByPage(final String title,final String details,int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "gmtCreated");
        PageRequest pageRequest =  new PageRequest(currentPage, pageSize, sort);
        return secondHandGoodsRepository.findAll(new Specification<SecondHandGoods>() {

			@Override
			public Predicate toPredicate(Root<SecondHandGoods> root,
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
