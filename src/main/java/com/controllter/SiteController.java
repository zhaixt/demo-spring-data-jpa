package com.controllter;


import com.comm.response.BaseResponse;
import com.model.Site;
import com.model.User;
import com.service.SiteService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/site")
public class SiteController {
	@Autowired
    private SiteService siteService;
	//查询所有场站
    @RequestMapping("/findAllSites")
    BaseResponse<List<Site>> findAllSites() {
        return siteService.findAllSiteInfo();
    }

    @RequestMapping(value = "/saveSite", method = RequestMethod.POST)
    BaseResponse<String> saveSite(@RequestBody final Site site) {
    	
      return siteService.publishSiteInfo(site);
    }
    @RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
    public BaseResponse<String> deleteSiteInfo(@PathVariable Long id) {
        return siteService.deleteSiteInfo(id);
    }
}
