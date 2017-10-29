package com.repository;

import com.model.Site;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findById(Long id);
    List<Site> findAll();
    Site save(Site site);
}
