package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.bean.Admin;
import com.demo.util.ConnectionUtil;

public class AdminDao {
	/*
	 * 根据用户名和密码去数据库里面查询数据
	 */
	public Admin selectUsernameAndPassword(String username,String password) {
		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "select * from admin where username = ? and password = ?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			
			ResultSet resultSet = prepareStatement.executeQuery();
			
			boolean result = resultSet.next();
			if(result) {
				Admin admin = new Admin();
				admin.setId(resultSet.getLong("id"));
				admin.setUserName(resultSet.getString("username"));
				admin.setPassword(resultSet.getString("password"));
				return admin;
			}
			return null;
			
		} catch (SQLException e) {
			return null;
		}
	}
}
