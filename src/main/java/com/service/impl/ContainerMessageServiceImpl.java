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
import com.model.ContainerMessage;
import com.model.ProductMessage;
import com.repository.ContainerInfoRepository;
import com.service.ContainerMessageService;

@Service
public class ContainerMessageServiceImpl implements ContainerMessageService {

	@Autowired
	private ContainerInfoRepository containerInfoRepository;

	@Override
	public BaseResponse<String> publishContainerInfo(ContainerMessage message) {
		BaseResponse<String> resposne = new BaseResponse<>(Boolean.TRUE);
		message.setGmtCreated(new Date());
		try {
			containerInfoRepository.save(message);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("发布集装箱信息失败");
		}
		return resposne;
	}
	
	@Override
	 public Page<ContainerMessage> findByPage(final String containerMode,final String port,
											  final String transportType,final String landingPlace, final String landingDate,final Integer reportNum, int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "landingDate");
		PageRequest pageRequest =  new PageRequest(currentPage, pageSize, sort);
		return containerInfoRepository.findAll(new Specification<ContainerMessage>(){

			@Override
			public Predicate toPredicate(Root<ContainerMessage> root,
										 CriteriaQuery<?> query, CriteriaBuilder builder) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(containerMode)){
					predicates.add(builder.equal(root.get("containerMode"), containerMode));
				}
				if(!StringUtils.isEmpty(port)){
					predicates.add(builder.equal(root.get("port"), port));
				}
				if(!StringUtils.isEmpty(transportType)){
					predicates.add(builder.equal(root.get("transportType"),transportType ));
				}
				if(!StringUtils.isEmpty(landingPlace)){
					predicates.add(builder.equal(root.get("landingPlace"),landingPlace ));
				}
				if(!StringUtils.isEmpty(landingDate)){
					predicates.add(builder.equal(root.get("landingDate"),landingDate ));
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

	@Override
	public Page<ContainerMessage> findByPhoneNumByPage(final String phoneNum,int currentPage, int pageSize) {
		Sort sort = new Sort(Direction.DESC, "landingDate");
		PageRequest pageRequest =  new PageRequest(currentPage, pageSize, sort);
		return containerInfoRepository.findByPhoneNum(phoneNum, pageRequest);
//        return null;
	}
	/**
	 * 删除货源信息
	 * @param id
	 */
	public BaseResponse<String> deleteContainerInfo(Long id){
		BaseResponse<String> resposne = new BaseResponse<>(Boolean.TRUE);
		try {
			containerInfoRepository.delete(id);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("删除集装箱信息失败");

		}
		return resposne;
	}


	/**
	 * 举报集装箱信息
	 * @param id
	 */
	public BaseResponse<Long> reportContainerInfo(Long id){
		BaseResponse<Long> response = new BaseResponse<>(Boolean.TRUE);
		try {
			ContainerMessage containerMessage = containerInfoRepository.findOne(id);
			Long reportNum;
			if(null == containerMessage.getReportNum()){
				reportNum = 1L;
			}else {
				reportNum = containerMessage.getReportNum() + 1;
			}

			containerMessage.setReportNum(reportNum);
			containerInfoRepository.save(containerMessage);
			response.setResult(reportNum);
		} catch (Exception e) {
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("举报集装箱信息失败");

		}
		return response;
	}
}
