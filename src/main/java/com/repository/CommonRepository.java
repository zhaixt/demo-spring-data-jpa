package com.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by liang on 2015/7/13.
 */
@Repository
public class CommonRepository {
    @PersistenceContext
    private EntityManager em;

    public <T> List<T> findItems(String nativeSql, Class<T> clazz) {
        Query query = em.createNativeQuery(nativeSql, clazz);
        return query.getResultList();
    }
}
