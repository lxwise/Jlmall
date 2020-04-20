package com.jl.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jl.common.pojo.TaotaoResult;
import com.jl.common.utils.PhoneFormatCheckUtils;
import com.jl.pojo.TbUser;
import com.jl.sso.service.UserRegisterService;

import entity.Result;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
	@Autowired
	private UserRegisterService registerservice;
	/**
	 * url：/user/check/{param}/{type}
	 * 
	 * @param param
	 * @param type  1  2 3 
	 * @return
	 */
	@RequestMapping(value="/user/check/{param}/{type}",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult checkData(@PathVariable String param,@PathVariable Integer type){
		//1.引入服务
		//2.注入
		//3.调用
		return registerservice.checkData(param, type);
	}
	/*
	 * url:/user/register
	 *  参数：
	 *  username //用户名
		password //密码
		phone //手机号
		email //邮箱
	请求的方法：post
	返回值：json
	 */
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult register(TbUser user){
		//
		TaotaoResult result = registerservice.register(user);
		return result;
	}
	
	
	@RequestMapping("/sendCode")
	public Result sendCode(String phone){
		
		if(!PhoneFormatCheckUtils.isPhoneLegal(phone)){
			return new Result(false, "手机格式不正确");
		}
		
		try {
			registerservice.createSmsCode(phone);
			return new Result(true, "验证码发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "验证码发送失败");
		}
	}
	
}
