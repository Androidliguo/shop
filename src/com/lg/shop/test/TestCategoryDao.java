package com.lg.shop.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import com.lg.shop.dao.CategoryDao;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.model.Category;


public class TestCategoryDao extends BaseTest {
  
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Test
	public void testAdd(){
		Category category=new Category();
		category.setName("汽车");
		categoryDao.add(category);
		
		category=new Category();
		category.setName("冬季服装");
		categoryDao.add(category);
	
		category=new Category();
		category.setName("秋季服装");
		categoryDao.add(category);
		
		category=new Category();
		category.setName("夏季服装");
		categoryDao.add(category);
		
		category=new Category();
		category.setName("家电");
		categoryDao.add(category);
	}
	
	@Test
	public void testDelete(){
		categoryDao.delete(6);
	}
	
	@Test
	public void testUpdate(){
		Category category=categoryDao.load(7);
		category.setName("服装");
		categoryDao.update(category);
		
	}
	
	
	@Test
	public void testListCon(){
	 List<Category>	list=categoryDao.list("服");
	 for(Category c:list){
		 System.out.println(c.getName());
	 }
	}
	
	@Test
	public void testList(){
		 List<Category>	list=categoryDao.list();
		 for(Category c:list){
			 System.out.println(c.getName());
		 }
		
	}
	
	
	
	@Test
	public void testLoad(){
		Category category=categoryDao.load(7);
		System.out.println(category.getName());
	}
	
}
