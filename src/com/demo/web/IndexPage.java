package com.demo.web;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.demo.bean.Category;
import com.demo.bean.Goods;
import com.demo.service.AdminService;
import com.demo.service.CategoryService;
import com.demo.service.GoodsService;

/**
 * 首页
 * @author ASUS
 *
 */
public class IndexPage {
	/**
	 * 展示页面
	 */
	public static void showPage() {
		System.out.println("欢迎使用后台管理系统");
		System.out.println("1.管理员登录");
		System.out.println("2.管理员登出");
		System.out.println("3.商品添加");
		System.out.println("4.商品修改");
		System.out.println("5.商品删除");
		System.out.println("6.批量添加商品");
		System.out.println("7.批量删除商品");
		System.out.println("8.商品的上架");
		System.out.println("9.一键上架");
		System.out.println("V.查询所有商品");
		System.out.println("X.分类的添加");
		System.out.println("I.商品下架");
		System.out.println("II.商品一键下架");
		System.out.println("III系统退出");
		System.out.println("请输入编号操作");
	}
	
	public static void start() {
		while(true) {
			showPage();
			Scanner scanner = new Scanner(System.in);
			String number = scanner.next();
			switch(number) {
			case "1":
				try {
					//调用server代码去进行登录
					/*
					 * 1.让别人输入用户名和密码
					 * 2.把用户名和密码交给service去进行逻辑操作
					 * 
					 */
					System.out.println("请严格按照下面的格式输入用户名和密码");
					System.out.println("用户名:密码");
					String userinfo = scanner.next();
					//把用户名和密码传入给service
					AdminService service = new AdminService();
					service.login(userinfo.split(":")[0], userinfo.split(":")[1]);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "2":
				//所谓登出就是把登录成功的标志位清空
				AdminService service = new AdminService();
				service.logout();
				System.out.println("登出成功，请重新登录");
				break;
			case "X":
				AdminService service2 = new AdminService();
				if(!service2.isLogin()) {
					System.out.println("没有登录，请登录后进行操作");
					break;
				}
				System.out.println("请严格按照下面的格式进行输入");
				System.out.println("分类名字:分类所属编号");
				String categoryInfo = scanner.next();
				
				Category category = new Category();
				category.setName(categoryInfo.split(":")[0]);
				category.setParentId(Long.parseLong(categoryInfo.split(":")[1]));
				
				//调用service添加分类
				CategoryService categoryService = new CategoryService();
				categoryService.addCategory(category);
				System.out.println("添加成功");
				break;
			case "3":
				AdminService adminService = new AdminService();
				if(adminService.isLogin()) {
					System.out.println("没有登录，请登录以后进行操作");
					break;
				}
				System.out.println("请严格按照下面的格式进行输入");
				System.out.println("商品名:商品价格:商品分类编号");
				String goodsInfo = scanner.next();
				Goods goods = new Goods();
				goods.setName(goodsInfo.split(":")[0]);
				goods.setPrice(Double.parseDouble(goodsInfo.split(":")[1]));
				goods.setStatus("0");
				goods.setUpdateDate(new Date(System.currentTimeMillis()));
				
				Category category2 = new Category();
				category2.setId(Long.parseLong(goodsInfo.split(":")[2]));
				goods.setCategory(category2);
				//调用service
				GoodsService goodsService = new GoodsService();
				goodsService.addGoods(goods);
				break;
			case "6":
				GoodsService goodsService2 = new GoodsService();
				
				List<Goods> list = goodsService2.analysisXML();
				
				list.forEach(t -> goodsService2.addGoods(t));
				System.out.println("添加成功");
				break;
			case "V":
				GoodsService goodService3 = new GoodsService();
				List<Goods> all = goodService3.findAll();
				System.out.println("----------------------------------------------");
				System.out.println("商品编号\t\t商品名字\t\t商品价格\t\t商品状态\t\t分类名字");
				for (Goods goods2 : all) {
					System.out.println(goods2.getId() 
										+ "\t\t" + goods2.getName() 
										+ "\t\t" + goods2.getPrice() 
										+ "\t\t" + goods2.getStatus() 
										+ "\t\t" + goods2.getCategory().getName());
				}
				System.out.println("----------------------------------------------");
				break;
				
			case "III":
				System.out.println("欢迎下次使用");
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	public static void main(String[] args) {
		start();
	}
}
