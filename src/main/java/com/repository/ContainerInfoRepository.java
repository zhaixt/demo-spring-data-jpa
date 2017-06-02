package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.model.ContainerMessage;


/**
 * 
 * @author zhaixt
 *
 */
public interface ContainerInfoRepository extends PagingAndSortingRepository<ContainerMessage, Long> {
	public Page<ContainerMessage> findAll(Specification<ContainerMessage> specification,Pageable pageable);
	public Page<ContainerMessage> findByPhoneNum(String phoneNum,Pageable pageable);


}
