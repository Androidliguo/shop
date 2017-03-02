package com.lg.shop.dao;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.lg.shop.model.Pager;
import com.lg.shop.model.SystemContext;
import com.lg.shop.util.DBUtil;
import com.lg.shop.util.DaoUtil;
import com.lg.shop.util.MyBatisUtil;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager.NameMap;


public class BaseDao<T> {
	
	public BaseDao(){
		DaoUtil.diObject(this);
		/*Method[] methods=this.getClass().getDeclaredMethods();
		for(Method method:methods){
			String name=method.getName();
			if(name.startsWith("set")){
				name=name.substring(3);
				name=name.substring(0, 1).toLowerCase()+name.substring(1);
				Object object=DaoUtil.createDaoFactory().getDao(name);
				try {
					method.invoke(this,object);
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
		}*/
	}
	
	public void add(T obj){
		SqlSession session=null;
		try{
			session=MyBatisUtil.createSession();
			session.insert(obj.getClass().getName()+".add", obj);
			session.commit();
		}catch(Exception e){
			session.rollback();
			
		}finally{
			MyBatisUtil.closeSession(session);
		}
		
		
	}
	
	/**
	 * 加载的操作
	 * @param clz
	 * @param id
	 * @return
	 */
	
    public T load(Class clz,int id){
    	T obj=null;
		SqlSession session=null;
		try{
		session=MyBatisUtil.createSession();
	    obj=session.selectOne(clz.getName()+".load",id);		
		}finally {
		MyBatisUtil.closeSession(session);
		}
		return obj;
    }
    
    /**
     * 
     * 更加一般的方式
     */
    
    public T loadBySqlId(String sqlId,Map<String, Object> params){
    	SqlSession session=null;
    	T t=null;
    	try{
    		session=MyBatisUtil.createSession();
    	    t=(T)session.selectOne(sqlId, params);
    		
    	}finally{
    		MyBatisUtil.closeSession(session);
    	}
    	return t;
    	
    }
    
    /**
     * 重载的load方法
     */
    public T loadBySqlId(String sqlId,Object obj){
    	SqlSession session=null;
    	T t=null;
    	try{
    		session=MyBatisUtil.createSession();
    		t=(T) session.selectOne(sqlId, obj);
    	}finally{
    		
    	}
    	return t;
    }
    
    public List<T> list(Class clz,Map<String, Object> params){
    	return this.list(clz.getName()+".list",params);
    }
    
    /**
     * list操作方法
     */
    public List<T> list (String sqlId,Map<String,Object> params) {
    	List<T> list=null;
    	SqlSession session=null;
    	try{
    		session=MyBatisUtil.createSession();
    		list=session.selectList(sqlId,params);
    	}finally{
    		MyBatisUtil.closeSession(session);
    	}
    	return list;
    }
    
    /**
     * 删除的操作
     */
    public void delete(Class clz,int id){SqlSession session=null;
	try{
	session=MyBatisUtil.createSession();
	session.delete(clz.getName()+".delete",id);
	session.commit();
	}catch(Exception e){
	 session.rollback();
	}finally{
		MyBatisUtil.closeSession(session);
	}
	
    }
    

	/**
	 * 更新的操作
	 */
    public void update(T obj){
    	SqlSession session=null;
		try{
		session=MyBatisUtil.createSession();
		session.update(obj.getClass().getName()+".update",obj);
		session.commit();
	  	
		}catch(Exception e){
			session.rollback();
		}finally {
		MyBatisUtil.closeSession(session);
		}
    }
    public Pager<T> find(Class<T> clz,Map<String,Object> params){
    	return this.find(clz.getName()+".find", params);
    }
    
    /**
     * find的操作
     */
    
    public Pager<T> find(String sqlId,Map<String, Object> params){
    	int pageSize=SystemContext.getPageSize();
    	int pageOffset=SystemContext.getPageOffset();
    	String order=SystemContext.getOrder();
    	String sort=SystemContext.getSort();
    	Pager<T> pages=new Pager<>();
    	SqlSession session=null;
    	try{
    		session=MyBatisUtil.createSession();
    		if(params==null){
    			params=new HashMap<String,Object>();
    		}
    			params.put("pageSize", pageSize);
    			params.put("pageOffset", pageOffset);
    			params.put("sort", sort);
    			params.put("order", order);
    			List<T> datas=session.selectList(sqlId,params);
    			pages.setDatas(datas);
    			pages.setPageOffset(pageOffset);
    			pages.setPageSize(pageSize);
    			int totalRecord=session.selectOne(sqlId+"_count",params);
    			pages.setTotalRecord(totalRecord);
    			
    		
    	}finally{
    		MyBatisUtil.closeSession(session);
    	}
    	return pages;
    }
    

}
