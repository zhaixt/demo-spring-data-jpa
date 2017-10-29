package com.repository;

import com.model.DriverRecruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRecruitRepository extends JpaRepository<DriverRecruit, Long> {
    List<DriverRecruit> findById(Long id);
    List<DriverRecruit> findAll();
    DriverRecruit save(DriverRecruit secondHandGoods);
    Page<DriverRecruit> findAll(Specification<DriverRecruit> specification, Pageable pageable);
}
