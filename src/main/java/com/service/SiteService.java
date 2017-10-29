package com.service;


import com.comm.response.BaseResponse;
import com.model.Site;
import com.model.User;

import java.util.List;

public interface SiteService {
	public BaseResponse<List<Site>> findAllSiteInfo();
	/**
	 * 发布场站信息
	 * @param site
	 */
	public BaseResponse<String> publishSiteInfo(Site site);

	/**
	 * 删除场站信息
	 * @param id
	 */
	public BaseResponse<String> deleteSiteInfo(Long id);
}
