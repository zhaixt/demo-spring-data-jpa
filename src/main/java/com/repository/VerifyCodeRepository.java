package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.model.VerifyCode;

import java.util.List;

public interface VerifyCodeRepository extends JpaRepository<VerifyCode, Long> {

    VerifyCode findVerifyCodeByPhoneNum(String PhoneNum);
    VerifyCode save(VerifyCode verifyCode);

}
