package com.service;


import com.comm.response.BaseResponse;
import com.model.User;

import java.util.List;

public interface UserService {
	public BaseResponse<User> findUserByPhoneNumAndPassword(String phoneNum,String password);
	public BaseResponse<User> resetPassword(String phoneNum,String password,String verifyCode);
	public BaseResponse<User> saveUser(User user);//
	public BaseResponse sendVerifyCode(String phoneNum);
}
