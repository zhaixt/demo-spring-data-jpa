package com.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.model.RoadRoute;

/**
 * 
 * @author liangz
 *
 */
public interface RoadRouteRepository extends PagingAndSortingRepository<RoadRoute, Long> {
	
}
