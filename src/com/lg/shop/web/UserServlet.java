package com.lg.shop.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lg.shop.dao.IUserDao;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.model.Pager;
import com.lg.shop.model.ShopException;
import com.lg.shop.model.User;
import com.lg.shop.util.DBUtil;
import com.lg.shop.util.RequestUtil;
import com.sun.net.httpserver.HttpsConfigurator;

import jdk.nashorn.internal.ir.RuntimeNode.Request;



public class UserServlet extends BaseServlet {
	 /**
	 * 
	 */
	public static final String redirPath="redirect:";
	private static final long serialVersionUID = 3752059040876909348L;
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	 
	 public String list(HttpServletRequest request,HttpServletResponse response){
		 Pager<User> users=userDao.find("");
		 request.setAttribute("users", users);
		 return "/user/list.jsp";
	 }
	 
	 
	 @Auth("any")
	 public String addInput(HttpServletRequest request,HttpServletResponse response){
		 return "/user/addInput.jsp";
	 }
	 
	 public String delete(HttpServletRequest request,HttpServletResponse response){
		 int id=Integer.parseInt(request.getParameter("id"));
		 userDao.delete(id);
		 return redirPath+"user.do?method=list";
	 }
	 
	 public String updateInput(HttpServletRequest request,HttpServletResponse response){
		 int id=Integer.parseInt(request.getParameter("id"));
		 User user=userDao.load(id);
		 request.setAttribute("user",user);
		 return "/user/updateInput.jsp";
	 }
	 
	 public String update(HttpServletRequest request,HttpServletResponse response){
		 int id=Integer.parseInt(request.getParameter("id"));
		 User u=userDao.load(id);
		 boolean b=RequestUtil.validate(User.class, request);
		 if(!b){
			 request.setAttribute("user",u);
			 return "/user/updateInput.jsp";
		 }
		 User user=(User)RequestUtil.setParam(User.class, request);
		 u.setPassword(user.getPassword());
		 u.setNickname(user.getNickname());
		 userDao.update(u);
		 return redirPath+"user.do?method=list";
	 }
	 
	 public String changeType(HttpServletRequest request,HttpServletResponse response){
		 int id=Integer.parseInt(request.getParameter("id"));
		 User u=userDao.load(id);
		 if(u.getType()==1){
			 u.setType(0);
		 }else{
			 u.setType(1);
		 }
		 userDao.update(u);
		 return redirPath+"user.do?method=list";
	 }
	 
	 @Auth("any")
	 public String add(HttpServletRequest request,HttpServletResponse response){
		 boolean b=RequestUtil.validate(User.class, request);
		 if(!b){
			 return "/user/addInput.jsp";
		 }
		 User user=(User)RequestUtil.setParam(User.class, request);
		 try{
		 userDao.add(user);
		 }catch(ShopException e){
			 request.setAttribute("errorMsg", e.getMessage());
			 return "/inc/error.jsp";
		 }
		/* Map<String, String[]> params=request.getParameterMap();
		 Set<String> keys=params.keySet();
		 User u=new User();
		 for(String key:keys){
			 Method method;
			 String mm="set"+key.substring(0, 1).toUpperCase()+key.substring(1);
			 try {
				method=u.getClass().getMethod(mm, String.class);
				 method.invoke(u, params.get(key)[0]);
			} catch (NoSuchMethodException | SecurityException e) {
				continue;
				// TODO Auto-generated catch block
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
		 userDao.add(u);*/
		/* String username=request.getParameter("username");
		 String password=request.getParameter("password");
		 String nickname=request.getParameter("nickname");
		 User user=new User();
		 user.setUsername(username);
		 user.setPassword(password);
		 user.setNickname(nickname);
		 userDao.add(user);*/
		 /**
		  * 
		  * 细节问题。注意在user前面不要加上/.否则的话，是会出错的。
		  */
		 return redirPath+"user.do?method=list";
	 }
	 
	 
	 @Auth("any")
		public String loginInput(HttpServletRequest req,HttpServletResponse resp) {
			return "/user/loginInput.jsp";
		}
	 
	 @Auth("any")
		public String login(HttpServletRequest req,HttpServletResponse resp) {
		    String username=req.getParameter("username");
		    String password=req.getParameter("password");
		    try{
		    User user=userDao.login(username, password);
		    req.getSession().setAttribute("loginUser",user);
		    }catch(ShopException e){
		    	req.setAttribute("errorMsg", "用户名或者用户密码错误");
		    	return "/inc/error.jsp";
		    }
		   
			return redirPath+req.getContextPath()+"/goods.do?method=list";
		}
	 
	 @Auth
	 public String logout(HttpServletRequest request,HttpServletResponse response){
		 request.getSession().invalidate();
		 return redirPath+"goods.do?method=list";
	 }
	 
	 @Auth
	 public String updateSelfInput(HttpServletRequest request,HttpServletResponse response){
		 User user=(User) request.getSession().getAttribute("loginUser");
		 request.setAttribute("user",user);
		 return "/user/updateSelfInput.jsp";
	 }
	 
	 @Auth
	 public String updateSelf(HttpServletRequest request,HttpServletResponse response){
		 User u=(User) request.getSession().getAttribute("loginUser");
		 boolean b=RequestUtil.validate(User.class, request);
		 if(!b){
			 request.setAttribute("user",u);
			 return "/user/updateSelfInput.jsp";
		 }
		 User user=(User)RequestUtil.setParam(User.class, request);
		 u.setPassword(user.getPassword());
		 u.setNickname(user.getNickname());
		 userDao.update(u);
		 return redirPath+"goods.do?method=list";
	 }
	 @Auth
	 public String show(HttpServletRequest request,HttpServletResponse response){
		 int id=Integer.parseInt(request.getParameter("id"));
		 User user=userDao.load(id);
		 request.setAttribute("user",user);
		 return "/user/show.jsp";
	 }
	 

}
