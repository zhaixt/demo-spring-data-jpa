package com.controllter;

import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.ContainerMessage;
import com.model.PersonalCollection;
import com.model.ProductMessage;
import com.model.request.ContainerMessageRequest;
import com.model.request.PersonalCollectionRequest;
import com.model.request.ProductMessageRequest;
import com.model.response.CarMessageResponse;
import com.service.CarMessageService;
import com.service.ContainerMessageService;
import com.service.PersonalCollectionService;
import com.service.ProductMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhaixiaotong on 2017-4-25.
 */
@RestController
@RequestMapping(value = "/personal")
public class PersonalController {
    private static final long serialVersionUID = 3419626567522525664L;

    @Autowired
    public ProductMessageService productMessageService;
    @Autowired
    public CarMessageService carMessageService;
    @Autowired
    public ContainerMessageService containerMessageService;

    @Autowired
    public PersonalCollectionService personalCollectionService;

    /**
     * 根据电话号码分页查询个人发布的货物信息
     * @return
     */
    @RequestMapping(value = "/product/phoneNum/{phoneNum}/currentPage/{currentPage}/pageSize/{pageSize}")
    public Page<ProductMessage> queryProducts(@PathVariable String phoneNum,@PathVariable int currentPage,@PathVariable int pageSize) {
       return productMessageService.findByPhoneNum(phoneNum,currentPage,pageSize);
    }

    @RequestMapping(value = "/car/phoneNum/{phoneNum}/currentPage/{currentPage}/pageSize/{pageSize}")
    public BaseResponse<List<CarMessageResponse>> queryCarMessages(@PathVariable String phoneNum,@PathVariable int currentPage,@PathVariable int pageSize) {
        return carMessageService.findByPhoneNum(phoneNum, currentPage, pageSize);
    }

    /**
     * 根据电话号码分页查询个人发布的集装箱信息
     * @return
     */
    @RequestMapping(value = "/container/phoneNum/{phoneNum}/currentPage/{currentPage}/pageSize/{pageSize}")
    public Page<ContainerMessage> queryContainers(@PathVariable String phoneNum,@PathVariable int currentPage,@PathVariable int pageSize) {
        return containerMessageService.findByPhoneNumByPage(phoneNum, currentPage, pageSize);
//		return null;
    }
    //收藏条目（货物、集装箱和车辆）
    @RequestMapping(value = "/collect_item")
    public BaseResponse<String> collectItem(@RequestBody PersonalCollection personalCollection) {
        return personalCollectionService.savePersonalCollection(personalCollection);
    }
    /*
    * 查询收藏的条目
    * */
    @RequestMapping(value = "/collection/product")
    public Page<ProductMessage> queryCollectedProducts(@RequestBody PersonalCollectionRequest personalCollectionRequest) {
        return personalCollectionService.findCollectedProduct(personalCollectionRequest);
    }
    @RequestMapping(value = "/collection/car")
    public BaseResponse<List<CarMessageResponse>> queryCollectedCar(@RequestBody PersonalCollectionRequest personalCollectionRequest) {
        return personalCollectionService.findCollectedCar(personalCollectionRequest);
    }
    @RequestMapping(value = "/collection/container")
    public Page<ContainerMessage> queryCollectedContainers(@RequestBody PersonalCollectionRequest personalCollectionRequest) {
        return personalCollectionService.findCollectedContainer(personalCollectionRequest);
    }
    /*
    * 删除收藏的条目
    * */
//    @RequestMapping(value = "/collection/product/delete/phoneNum/{phoneNum}/collectOptionId/{collectOptionId}/collectOptionTye/{collectOptionTye}")
    @RequestMapping(value = "/collection/product/delete")
    public BaseResponse<String> deleteCollectedProducts(@RequestBody PersonalCollection personalCollection) {
        return personalCollectionService.deleteCollectedProduct(personalCollection);
    }

    @RequestMapping(value = "/collection/car/delete")
    public BaseResponse<String> deleteCollectedCar(@RequestBody PersonalCollection personalCollection) {
        return personalCollectionService.deleteCollectedCar(personalCollection);
    }

    @RequestMapping(value = "/collection/container/delete")
    public BaseResponse<String> deleteCollectedContainer(@RequestBody PersonalCollection personalCollection) {
        return personalCollectionService.deleteCollectedContainer(personalCollection);
    }


}
