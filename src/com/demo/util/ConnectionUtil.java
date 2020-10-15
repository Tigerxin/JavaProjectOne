package com.demo.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

public class ConnectionUtil {
	private static DruidDataSource dataSource;
	
	static {
		dataSource = new DruidDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("lx");
		dataSource.setPassword("lx");
		
		dataSource.setInitialSize(3);
		dataSource.setMaxActive(6);
	}
	
	public static Connection getConnection(boolean autoCommit){
		try {
			Connection connection = dataSource.getConnection();
			connection.setAutoCommit(autoCommit);
			return connection;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public static Connection getConnection() {
		return getConnection(false);
	}
}
