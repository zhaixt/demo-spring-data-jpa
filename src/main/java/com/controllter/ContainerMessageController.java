package com.controllter;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.comm.response.BaseResponse;
import com.model.ContainerMessage;
import com.model.request.ContainerMessageRequest;
import com.service.ContainerMessageService;

/**
 * 发布货源信息
 * 
 * @author zhaixt
 * 2016-11-8
 * 
 */

@RestController
@RequestMapping(value = "/container")
public class ContainerMessageController implements Serializable {

	private static final long serialVersionUID = 3419626567522525664L;

	@Autowired
	public ContainerMessageService containerMessageService;

	@RequestMapping(value = "/publish")
	public BaseResponse<String> publicContainerInfo(@RequestBody ContainerMessage containerMessage) {
		return containerMessageService.publishContainerInfo(containerMessage);
	}
	/**
	 * 根据集装箱模式、出发地、目的地、装车时间等分页查询货源信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<ContainerMessage> queryContainers(@RequestBody ContainerMessageRequest containerMessageRequest) {
		String containerMode = containerMessageRequest.getContainerMode();
		String port = containerMessageRequest.getPort();
		String transportType = containerMessageRequest.getTransportType();
		String landingPlace = containerMessageRequest.getLandingPlace();
		String landingDate = containerMessageRequest.getLandingDate();
		int currentPage = containerMessageRequest.getCurrentPage();
		int pageSize = containerMessageRequest.getPageSize();
		return containerMessageService.findByPage(containerMode, port, transportType, landingPlace, landingDate, currentPage, pageSize);
//		return null;
	}

	@RequestMapping(value = "/delete/id/{id}",method = RequestMethod.GET)
	public BaseResponse<String> deleteContainerInfo(@PathVariable Long id) {
		return containerMessageService.deleteContainerInfo(id);
	}

	@RequestMapping(value = "/report/id/{id}",method = RequestMethod.GET)
	public BaseResponse<Long> reportContainerInfo(@PathVariable Long id) {
		return containerMessageService.reportContainerInfo(id);
	}
}
