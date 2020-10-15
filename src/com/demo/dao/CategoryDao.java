package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.demo.bean.Category;
import com.demo.util.ConnectionUtil;

public class CategoryDao {
	public void insertCategoryDate(Category category) {
		try {
			Connection connection = ConnectionUtil.getConnection();
			
			String sql = "insert into category values(common_seq.nextval,?,?)";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			
			prepareStatement.setString(1, category.getName());
			prepareStatement.setLong(2, category.getParentId());
			
			prepareStatement.execute();
			
			connection.commit();
			
			prepareStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
