package com.lg.shop.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import com.lg.shop.dao.DAOFactory;
import com.lg.shop.dao.IUserDao;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.model.Address;
import com.lg.shop.model.Pager;
import com.lg.shop.model.SystemContext;
import com.lg.shop.model.User;

public class TestBatis extends BaseTest {
	private IUserDao userDao;
	

	public IUserDao getUserDao() {
		return userDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/*@Before
	public void init(){
		userDao=DAOFactory.getUserDao();
	}*/
	@Test
	public void testFind(){
		SystemContext.setSort("username");
		SystemContext.setOrder("desc");
		SystemContext.setPageOffset(0);
		SystemContext.setPageSize(15);
		Pager<User> pages=userDao.find("");
		for(User user:pages.getDatas()){
			System.out.println(user);
		}
	}
	/*@Test
	public void TestAdd(){
		User user=new User();
		user.setUsername("oox");
		user.setNickname("ooo0");
		user.setPassword("1234");
		user.setType(0);
		userDao.add(user);
	}
	*/
	/*@Test
	public void testUpdate(){
		User user=userDao.load(114);
		user.setNickname("大傻子");
		user.setPassword("1234");
		user.setUsername("haha");
		userDao.update(user);
		System.out.println(user.getNickname());
	}*/
	
/*	@Test
	public void testLogin(){
		userDao.login("admin", "253473");
	}
		*/
	
	/*@Test
	public void testDelete(){
		userDao=DAOFactory.getUserDao();
		userDao.delete(113);
	}*/
/*	@Test
	public void testUpdate(){
		User user=userDao.load(9);
		user.setNickname("傻子");
		userDao.update(user);
		System.out.println(user.getNickname());
	}
	*/
/*	
	@Test
	public void testLoad(){
	User user=userDao.load(1);
	for(Address address:user.getAddresses()){
		System.out.println(address);
	}
	 
	// System.out.println(user.getNickname());
	 
	}*/
	/*@Test
	public void testLoadByUsername(){
		User user=userDao.loadByUsername("oop");
		System.out.println(user.getNickname());
	}*/
	

}
