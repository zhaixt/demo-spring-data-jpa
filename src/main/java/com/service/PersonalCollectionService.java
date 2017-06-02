package com.service;


import com.comm.response.BaseResponse;
import com.model.CarMessage;
import com.model.ContainerMessage;
import com.model.PersonalCollection;
import com.model.ProductMessage;
import com.model.request.PersonalCollectionRequest;
import com.model.response.CarMessageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonalCollectionService {
	public List<PersonalCollection> findByPhoneNumAndCollectOptionType(String phoneNum,String collectOptionType);

	public BaseResponse<String> savePersonalCollection(PersonalCollection personalCollection);

	public Page<ProductMessage> findCollectedProduct(PersonalCollectionRequest personalCollectionRequest);

	public BaseResponse<List<CarMessageResponse>> findCollectedCar(PersonalCollectionRequest personalCollectionRequest);

	public Page<ContainerMessage> findCollectedContainer(PersonalCollectionRequest personalCollectionRequest);

	public BaseResponse<String>  deleteCollectedProduct(PersonalCollection personalCollectionRequest);

	public BaseResponse<String>  deleteCollectedCar(PersonalCollection personalCollectionRequest);

	public BaseResponse<String>  deleteCollectedContainer(PersonalCollection personalCollectionRequest);

//	public Page<ProductMessage> findByIdList(List<Long> idList, int currentPage, int pageSize);

}
