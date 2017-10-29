package com.repository;

import com.model.SecondHandGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecondHandGoodsRepository extends JpaRepository<SecondHandGoods, Long> {
    List<SecondHandGoods> findById(Long id);
    List<SecondHandGoods> findAll();
    SecondHandGoods save(SecondHandGoods secondHandGoods);
    Page<SecondHandGoods> findAll(Specification<SecondHandGoods> specification, Pageable pageable);
}
