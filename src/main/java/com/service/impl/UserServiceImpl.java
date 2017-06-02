package com.service.impl;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comm.response.BaseResponse;
import com.model.User;
import com.model.VerifyCode;
import com.repository.UserRepository;
import com.repository.VerifyCodeRepository;
import com.service.UserService;
import com.util.VerifyCodeUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VerifyCodeRepository verifyCodeRepository;
	@Override
	public BaseResponse<User> findUserByPhoneNumAndPassword(String phoneNum,String password) {
		BaseResponse<User> response = new BaseResponse<>(Boolean.TRUE);
		   
		try {
			User user = userRepository.findUserByPhoneNumAndPassword(phoneNum,password);
			if(user == null){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("登录失败，请检查手机号码和密码");
				return response;
			}
			response.setResult(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("根据手机号码和密码查询用户异常");
		}
		return response;
	}@Override
	public BaseResponse<User> resetPassword(String phoneNum,String password,String verifyCode) {
		BaseResponse<User> response = new BaseResponse<>(Boolean.TRUE);
		   
		try {
			User user = userRepository.findUserByPhoneNum(phoneNum);
			if(user == null){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("重置密码失败，根据手机号码找不到用户");
				return response;
			}
			
			VerifyCode verifyCodeStorage = verifyCodeRepository.findVerifyCodeByPhoneNum(phoneNum);
			if(verifyCodeStorage !=null){
				if(!verifyCodeStorage.getVerifyCode().equals(verifyCode)){
					response.setSuccess(Boolean.FALSE);
					response.setFailMessage("重置密码失败，验证码不正确");
					return response;
				}

			}else{
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("重置密码失败,该手机号码验证码不存在");
				return response;

			}
			user.setPassword(password);
			
			response.setResult(userRepository.save(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("根据手机号码用户失败");
		}
		return response;
	}
	@Override
	public BaseResponse<User> saveUser(User user){
		BaseResponse<User> response = new BaseResponse<>(Boolean.TRUE);
		user.setGmtCreated(new Date());
		String phoneNum = user.getPhoneNum();
		String name = user.getName();
		try {
			User userStorage = userRepository.findUserByPhoneNum(phoneNum);
			if(userStorage!=null){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("注册用户失败,该手机号码已存在");
				return response;
			}
			userStorage = userRepository.findUserByName(name);
			if(userStorage!=null){
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("注册用户失败,该用户已存在");
				return response;
			}
			VerifyCode verifyCodeStorage = verifyCodeRepository.findVerifyCodeByPhoneNum(phoneNum);
			if(verifyCodeStorage !=null){
				if(!verifyCodeStorage.getVerifyCode().equals(user.getVerifyCode())){
					response.setSuccess(Boolean.FALSE);
					response.setFailMessage("注册用户失败,验证码不正确");
					return response;
				}

			}else{
				response.setSuccess(Boolean.FALSE);
				response.setFailMessage("注册用户失败,该手机号码验证码不存在");
				return response;
			}
			userRepository.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("注册用户失败");
		}
		return response;
	}
	@Override
	public BaseResponse sendVerifyCode(String phoneNum){
		BaseResponse<VerifyCode> response = new BaseResponse<>(Boolean.TRUE);
		
		try {
			VerifyCode verifyCode = verifyCodeRepository.findVerifyCodeByPhoneNum(phoneNum);
			if(verifyCode !=null){
				verifyCode.setVerifyCode(VerifyCodeUtil.generateVerifyCode(phoneNum));
				verifyCodeRepository.save(verifyCode);
			}else{
				VerifyCode newVerifyCode = new VerifyCode();
				newVerifyCode.setPhoneNum(phoneNum);
				newVerifyCode.setVerifyCode(VerifyCodeUtil.generateVerifyCode(phoneNum));
				verifyCodeRepository.save(newVerifyCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("发送验证码失败");
		}
		return response;
	}
}
