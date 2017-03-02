package com.lg.shop.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.lg.shop.util.PropertiesUtil;





public class PropertiesFactory implements IFactoryDao {
	private static PropertiesFactory f=new  PropertiesFactory();
	private static Map<String, Object> daos=new  HashMap<>();
	private PropertiesFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static IFactoryDao getInstance(){
		return f;
		
	}
	@Override
	public Object getDao(String name) {
		// TODO Auto-generated method stub
		try{
		if(daos.containsKey(name)){
			return daos.get(name);
		}
		Properties properties=PropertiesUtil.getDaoProp();
		String cn=properties.getProperty(name);
	     Object object=Class.forName(cn).newInstance();
	     daos.put(name,object);
	     return object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}

	
}
