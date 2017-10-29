package com.service.impl;

import com.comm.response.BaseResponse;
import com.model.ContainerMessage;
import com.model.Site;
import com.repository.ContainerInfoRepository;
import com.repository.SiteRepository;
import com.service.ContainerMessageService;
import com.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Override
	public BaseResponse<String> publishSiteInfo(Site site) {
		BaseResponse<String> resposne = new BaseResponse<>(Boolean.TRUE);
		site.setGmtCreated(new Date());
		try {
			siteRepository.save(site);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("发布场站信息失败");
		}
		return resposne;
	}


	/**
	 * 删除货源信息
	 * @param id
	 */
	public BaseResponse<String> deleteSiteInfo(Long id){
		BaseResponse<String> resposne = new BaseResponse<>(Boolean.TRUE);
		try {
			siteRepository.delete(id);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("删除场站信息失败");

		}
		return resposne;
	}


	/**
	 * 查询所有场站信息
	 * @param
	 */
	public BaseResponse<List<Site>> findAllSiteInfo(){
		BaseResponse<List<Site>> resposne = new BaseResponse<>(Boolean.TRUE);
		try {
			List<Site> sites = siteRepository.findAll();
			resposne.setResult(sites);
		} catch (Exception e) {
			resposne.setSuccess(Boolean.FALSE);
			resposne.setFailMessage("查询场站信息失败");

		}
		return resposne;
	}


}
