package com.demo.service;

import java.util.HashMap;
import java.util.Map;

import com.demo.bean.Admin;
import com.demo.bean.Category;
import com.demo.dao.AdminDao;

public class AdminService {
	
	private static Map<String, String> map = new HashMap<>();
	/**
	 * 登录方式
	 * 	1.怎么才算登录成功 -》那用户名和密码去与数据库比对
	 * 		只要登录成功，那么就往map中存入严格标志key：用户名 value：true
	 * @param userName
	 * @param password
	 */
	public void login(String userName,String password) {
		//1判空
		if(null == userName || null == password || "".equals(userName) || "".equals(password)) {
			throw new RuntimeException("参数为空");
		}
		//2.去数据库里面比对
		AdminDao dao = new AdminDao();
		//admin为空查询不到数据
		Admin admin = dao.selectUsernameAndPassword(userName, password);
		if(admin == null) {
			throw new RuntimeException("用户名或者密码错误");
		}
		map.put(userName, "true");
		System.out.println("登录成功");
	}
	
	public void logout() {
		map.clear();
	}
	/**
	 * 是否登录
	 * 	如果map中有标志位那么就登录成功
	 * @return
	 */
	public boolean isLogin() {
		return map.size() > 0;
	}
}
