package com.lg.shop.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;

import com.lg.shop.model.Pager;
import com.lg.shop.model.ShopException;
import com.lg.shop.model.SystemContext;
import com.lg.shop.model.User;
import com.lg.shop.util.MyBatisUtil;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager.NameMap;

public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		//SqlSession session=null;
		User user2=this.loadByUsername(user.getUsername());
		if(user2!=null){
			throw new ShopException("用户名已经存在了");
		}
		
		/*try{
			session=MyBatisUtil.createSession();
			session.insert(User.class.getName()+".add",user);
			session.commit();
			
		}catch(Exception e){
			session.rollback();
		}finally{
			MyBatisUtil.closeSession(session);
		}*/
		super.add(user);
		
		

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		super.delete(User.class, id);
		/*SqlSession session=null;
		try{
		session=MyBatisUtil.createSession();
		session.delete(User.class.getName()+".delete",id);
		session.commit();
		}catch(Exception e){
		 session.rollback();
		}finally{
			MyBatisUtil.closeSession(session);
		}*/

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		super.update(user);
		/*SqlSession session=null;
		try{
		session=MyBatisUtil.createSession();
		session.update(User.class.getName()+".update",user);
		session.commit();
	  	
		}catch(Exception e){
			session.rollback();
		}finally {
		MyBatisUtil.closeSession(session);
		}*/
	

	}

	@Override
	public User loadByUsername(String username) {
		// TODO Auto-generated method stub
		/*User user=null;
		SqlSession session=null;
		try{
		session=MyBatisUtil.createSession();
	    user=session.selectOne(User.class.getName()+".load_by_username",username);		
		}finally {
		MyBatisUtil.closeSession(session);
		}
		return user;*/
		return super.loadBySqlId(User.class.getName()+".load_by_username", username);
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return super.load(User.class, id);
		/*User user=null;
		SqlSession session=null;
		try{
		session=MyBatisUtil.createSession();
	    user=session.selectOne(User.class.getName()+".load",id);		
		}finally {
		MyBatisUtil.closeSession(session);
		}
		return user;
		*/
		
		
	}

	@Override
	public Pager<User> find(String name) {
	   Map<String,Object> params=new HashMap<String,Object>();
		if(name!=null&&!name.equals("")){
		params.put("name", "%"+name+"%");
		}
		return super.find(User.class, params);
	/*	// TODO Auto-generated method stub
		SqlSession session=null;
		int pageOffset=SystemContext.getPageOffset();
		int pageSize=SystemContext.getPageSize();
		String sort=SystemContext.getSort();
		String order=SystemContext.getOrder();
		List<User> datas=new ArrayList<>();
		Pager<User> pages=new Pager<>();
		
		try{
			session=MyBatisUtil.createSession();
			Map<String,Object> map=new HashMap<String,Object>();
			if(name!=null&&!name.equals("")){
			map.put("name", "%"+name+"%");
			}
			map.put("pageOffset", pageOffset);
			map.put("pageSize", pageSize);
			map.put("sort",sort);
			map.put("order",order);
			datas=session.selectList(User.class.getName()+".find",map);
			pages.setDatas(datas);
			pages.setPageOffset(pageOffset);
			pages.setPageSize(pageSize);
			int totalRecord=session.selectOne(User.class.getName()+".find_count",map);
			pages.setTotalRecord(totalRecord);
			
			
			
			
		}finally {
			MyBatisUtil.closeSession(session);
		}
		
		
		return pages;*/
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user=this.loadByUsername(username);
		if(user==null){
			throw new ShopException("用户不存在");
		}
		if(!(user.getPassword().equals(password))){
			throw new ShopException("用户密码错误");
		}
		
		return user;
	}

}
