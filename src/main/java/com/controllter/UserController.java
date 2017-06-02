package com.controllter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comm.response.BaseResponse;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
    private UserService userService;
	//登录
    @RequestMapping("sign/phoneNum/{phoneNum}/password/{password}")
    BaseResponse<User> findByPhoneNumAndPassword(@PathVariable String phoneNum,@PathVariable String password) {
        return userService.findUserByPhoneNumAndPassword(phoneNum,password);
    }
    //重置密码
    @RequestMapping("resetPassword/phoneNum/{phoneNum}/password/{password}/verifyCode/{verifyCode}")
    BaseResponse<User> resetPassword(@PathVariable String phoneNum,@PathVariable String password,@PathVariable String verifyCode) {
        return userService.resetPassword(phoneNum,password,verifyCode);
    }
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    BaseResponse<User> saveUser(@RequestBody final User user) {
    	
      return userService.saveUser(user);
    }
  //发送验证码
    @RequestMapping("sendVerifyCode/phoneNum/{phoneNum}")
    BaseResponse sendVerifyCode(@PathVariable String phoneNum) {
        return userService.sendVerifyCode(phoneNum);
    }
}
