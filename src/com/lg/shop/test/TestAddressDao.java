package com.lg.shop.test;

import java.util.List;

import com.lg.shop.dao.AddressJDBCDao;
import com.lg.shop.dao.DAOFactory;
import com.lg.shop.model.Address;

public class TestAddressDao {
	private static  AddressJDBCDao dao=(AddressJDBCDao) DAOFactory.getAddressDao();
	public static void main(String[] args){
		//testAdd();
		testList();
	}
	public static void testAdd(){
		Address address=new Address();
		address.setName("化州市");
		address.setPhone("120");
		address.setPostcode("525139");
        dao.add(address, 1);
	}
	
	public static void testList(){
		List<Address> as=dao.list(1);
		for(Address address:as){
			System.out.println(address.getUser().getNickname());
			System.out.println(address.getName());
		}
	}

}
