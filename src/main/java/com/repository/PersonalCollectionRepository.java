package com.repository;

import com.model.PersonalCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalCollectionRepository extends JpaRepository<PersonalCollection, Long> {
    List<PersonalCollection> findByPhoneNumAndCollectOptionType(String phoneNum,String collectOptionType);
    List<PersonalCollection> findByPhoneNumAndCollectOptionTypeAndCollectOptionId(String phoneNum,String collectOptionType,Long collectOptionId);
    PersonalCollection save(PersonalCollection personalCollection);
    long deleteByPhoneNumAndCollectOptionTypeAndCollectOptionId(String phoneNum,String collectOptionType,Long collectOptionId);

}
