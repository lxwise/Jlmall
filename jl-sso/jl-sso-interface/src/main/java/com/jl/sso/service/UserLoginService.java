package com.jl.sso.service;

import com.jl.common.pojo.TaotaoResult;

/**
 * 登录的接口
 * @author liuxin
 *
 */
public interface UserLoginService {
	/**
	 * 根据用户名和密码登录
	 * @param username
	 * @param password
	 * @return
	 * taotaoresult 登录成功 返回200 并且包含一个token数据
	 *登录失败：返回400
	 */
	public TaotaoResult login(String username,String password);
	/**
	 * 根据token获取用户的信息
	 * @param token
	 * @return  TaotaoResult 应该包含用户的信息
	 */
	public TaotaoResult getUserByToken(String token);
	/**
	 * 删除token 安全退出
	 * @param token
	 * @return
	 */
	
	public TaotaoResult logout(String token);
}
