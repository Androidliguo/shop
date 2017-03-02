package com.lg.shop.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import com.lg.shop.dao.IFactoryDao;
import com.lg.shop.dao.ShopDi;


public class DaoUtil {
	public static void main(String[] args){
		System.out.println(createDaoFactory());
	}
	public static void diObject(Object obj){
		Method[] methods=obj.getClass().getDeclaredMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(ShopDi.class)){
				String name=method.getName();
				ShopDi sd=method.getAnnotation(ShopDi.class);
				 String mm=sd.value();
				if(mm==null||"".equals(mm.trim())){
					name=name.substring(3);
					mm=name.substring(0,1).toLowerCase()+name.substring(1);
				}
				Object object=DaoUtil.createDaoFactory().getDao(mm);
				try {
					method.invoke(obj, object);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static IFactoryDao createDaoFactory(){
		IFactoryDao f=null;
		try {
		Properties prop=PropertiesUtil.getDaoProp();
		String fs=prop.getProperty("factory");
		Class clz=Class.forName(fs);
		String mm="getInstance";
		Method m=clz.getMethod(mm);
		f=(IFactoryDao) m.invoke(clz);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
		
	}

}
