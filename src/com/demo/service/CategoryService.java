package com.demo.service;

import com.demo.bean.Category;
import com.demo.dao.CategoryDao;

public class CategoryService {
	public void addCategory(Category category) {
		if(category == null) {
			throw new RuntimeException("参数为空");
		}
		CategoryDao dao = new CategoryDao();
		dao.insertCategoryDate(category);
	}
}
