package com.repository;

import com.model.CarTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarTransactionRepository extends JpaRepository<CarTransaction, Long> {
    List<CarTransaction> findById(Long id);
    List<CarTransaction> findAll();
    CarTransaction save(CarTransaction secondHandGoods);
    Page<CarTransaction> findAll(Specification<CarTransaction> specification, Pageable pageable);
}
