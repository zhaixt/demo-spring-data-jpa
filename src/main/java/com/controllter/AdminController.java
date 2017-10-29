package com.controllter;

import com.comm.response.BaseResponse;
import com.model.ContainerMessage;
import com.model.PersonalCollection;
import com.model.ProductMessage;
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
@RequestMapping(value = "/admin")
public class AdminController {
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
     * 根据出发地、目的地、装车时间分页查询货源信息
     *
     * @return
     */
//    @RequestMapping(value = "/product/query", method = RequestMethod.POST)
//    public Page<ProductMessage> queryProducts(@RequestBody ProductMessageRequest productMessageRequest) {

//        String productMode = productMessageRequest.getProductMode();
//        String startPlace = productMessageRequest.getDeparturePlace();
//        String endPlace = productMessageRequest.getDestinationPlace();
//        String loadDate = productMessageRequest.getLoadDate();
//        String carType = productMessageRequest.getCarType();
//        int currentPage = productMessageRequest.getCurrentPage();
//
//        int pageSize = productMessageRequest.getPageSize();
//		return productMessageService.findByDeparturePlaceAndDestinationPlaceAndLoadTime(startPlace,endPlace,loadDate,currentPage,pageSize);
//        return productMessageService.findByPage(productMode, startPlace, endPlace, carType, loadDate, currentPage, pageSize);
//		return null;
//    }


}
