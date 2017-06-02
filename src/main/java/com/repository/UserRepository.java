package com.repository;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findById(Long id);
    User findUserByPhoneNumAndPassword(String PhoneNum,String password);
    User findUserByPhoneNum(String PhoneNum);
    User findUserByName(String name);
    User findUserByPhoneNumAndVerifyCode(String PhoneNum,String verifyCode);
    User save(User user);
}
