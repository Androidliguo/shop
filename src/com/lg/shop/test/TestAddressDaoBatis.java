package com.lg.shop.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lg.shop.dao.AddressDao;
import com.lg.shop.dao.DAOFactory;
import com.lg.shop.dao.IAddressDao;
import com.lg.shop.dao.IUserDao;
import com.lg.shop.dao.PropertiesFactory;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.dao.UserDao;
import com.lg.shop.model.Address;
import com.lg.shop.model.User;
import com.lg.shop.util.DaoUtil;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import sun.security.timestamp.TimestampToken;

public class TestAddressDaoBatis extends BaseTest{
	private IAddressDao addressDao;
	private IUserDao userDao;
	
	

/*@Before
   public void Init(){
	  dao=(IAddressDao)  PropertiesFactory.getInstance().getDao("addressDao");
	//dao=new AddressDao();
   }*/
  
  



/*@Test
public void testAdd(){
	Address address=new Address();
	address.setName("低山");
	address.setPhone("1234");
	address.setPostcode("525100");
	addressDao.add(address, 117);
	
}*/

/*@Test
public void TestUser(){
	System.out.println(userDao);
}*/
//	@Test
//	public void testAdd(){
//		Address address=n ew Address();
//		address.setName("广州");
//		address.setPhone("1234");
//		address.setPostcode("525100");
//		dao.add(address, 117);
//		
//	}
	/*@Test
	public void testDelete(){
	dao.delete(1);
		
}*/
   public TestAddressDaoBatis() {
	// TODO Auto-generated constructor stub
}
   
   @Test
   public void setOk(){
	   
   }
 
  


@Test
   public void testLoad(){
	   Address address=addressDao.load(5);
	   System.out.println(address.getPostcode());
   }


public IAddressDao getAddressDao() {
	return addressDao;
}

@ShopDi
public void setAddressDao(IAddressDao addressDao) {
	this.addressDao = addressDao;
}

public IUserDao getUserDao() {
	return userDao;
}

@ShopDi
public void setUserDao(IUserDao userDao) {
	this.userDao = userDao;
}
   
   /*@Test
   public void TestUpdate(){
	   Address address=dao.load(5);
		address.setName("广东省茂名化州市那务镇");
		address.setPhone("1234");
		address.setPostcode("525100");
		dao.update(address);
   }*/
   
  /*@Test
   public void testList(){
	   List<Address> list=dao.list(1);
	   //User user=userDao.load(1);
	  // System.out.println(user);
	   for(Address address:list){
		   System.out.println(address.getUser());
	   }
	   
   }
*/
}
