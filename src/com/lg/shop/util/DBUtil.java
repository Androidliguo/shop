package com.lg.shop.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.lg.shop.dao.ShopDi;


public class DBUtil {
	public static Connection getConnection() {
		Properties prop = PropertiesUtil.getJdbcProp();
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");
		String driver=prop.getProperty("driver");
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	
	/*public static void diObject(Object obj){
		Method[] methods=obj.getClass().getDeclaredMethods();
		for(Method method:methods){
			String name=method.getName();
			if(name.startsWith("set")){
				name=name.substring(3);
				name=name.substring(0, 1).toLowerCase()+name.substring(1);
				Object object=DaoUtil.createDaoFactory().getDao(name);
				try {
					method.invoke(obj,object);
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
	}*/
	
	/*public static void diObject(Object obj){
		Method[] methods=obj.getClass().getDeclaredMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(ShopDi.class)){
				String name=method.getName();
				ShopDi sd=method.getAnnotation(ShopDi.class);
				String value=sd.value();
				if(value==null||"".equals(value.trim())){
					value=name.substring(0,1).toLowerCase()+name.substring(1);
				}
				Object object=DaoUtil.createDaoFactory().getDao(value);
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
	*/
	public static void close(Connection con) {
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(java.sql.PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

