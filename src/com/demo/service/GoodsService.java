package com.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.demo.bean.Category;
import com.demo.bean.Goods;
import com.demo.dao.GoodsDao;

public class GoodsService {
	
	public void addGoods(Goods goods) {
		if(goods == null) {
			throw new RuntimeException("参数为空");
		}
		GoodsDao dao = new GoodsDao();
		dao.insertData(goods);
	}
	
	public List<Goods> analysisXML(){
		try {
			List<Goods> list = new ArrayList<>();
			//1创建解析器
			SAXReader reader = new SAXReader();
			
			Document document = reader.read("src/data.xml");
			Element rootElement = document.getRootElement();
			//的到根元素下所有子元素
			List<Element> elements = rootElement.elements();
			elements.forEach(t -> {
				String name = t.element("name").getTextTrim();
				String price = t.element("price").getTextTrim();
				String categoryId = t.element("categoryId").getTextTrim();
				
				Goods goods = new Goods();
				goods.setName(name);
				goods.setPrice(Double.parseDouble(price));
				goods.setStatus("0");
				goods.setUpdateDate(new Date(System.currentTimeMillis()));
				
				Category category = new Category();
				category.setId(Long.parseLong(categoryId));
				
				goods.setCategory(category);
				list.add(goods);
			});
			return list;
		} catch (DocumentException e) {
			return null;
		}
	}
	
	public List<Goods> findAll(){
		GoodsDao dao = new GoodsDao();
		List<Goods> list = dao.selectAllGoods();
		return list;
	}
}
