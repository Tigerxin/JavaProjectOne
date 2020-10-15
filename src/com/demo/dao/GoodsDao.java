package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.bean.Category;
import com.demo.bean.Goods;
import com.demo.util.ConnectionUtil;

public class GoodsDao {
	public void insertData(Goods goods) {
		try {
			Connection connection = ConnectionUtil.getConnection();
			
			String sql = "insert into goods values(common_seq.nextval,?,?,?,?,?)";
			
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, goods.getName());
			prepareStatement.setDouble(2, goods.getPrice());
			prepareStatement.setString(3, goods.getStatus());
			prepareStatement.setDate(4, goods.getUpdateDate());
			prepareStatement.setLong(5, goods.getCategory().getId());
			
			prepareStatement.execute();
			
			connection.commit();
			prepareStatement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 查询所有的商品
	 * 
	 * @return
	 */
	public List<Goods> selectAllGoods(){
		
		List<Goods> list = new ArrayList<>();
		try {
			Connection connection = ConnectionUtil.getConnection();
			
			//查询商品以及商品所对应分类名字
			String sql = "select g.id gid,g.name gname,g.price gprice,g.state gstate,c.name cname"
					+ "from goods g,category c "
					+ "where g.category_id = c.id";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getLong("gid"));
				goods.setName(resultSet.getString("gname"));
				goods.setPrice(resultSet.getDouble("gprice"));
				goods.setStatus(resultSet.getString("gstate"));
				
				Category category = new Category();
				category.setName(resultSet.getString("cname"));
				goods.setCategory(category);
				
				list.add(goods);
			}
			connection.commit();
			prepareStatement.close();
			connection.close();
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
}
