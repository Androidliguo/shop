package com.lg.shop.web;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lg.shop.model.User;
import com.lg.shop.util.DaoUtil;

public class BaseServlet extends HttpServlet {
	/**
	 * 
	 */
	//存放的是错误的消息
		private Map<String,String> errors = new HashMap<String,String>();
	//private Map<String,String> errors =null;


		//得到错误的消息
		protected Map<String,String> getErrors() {
			return errors;
		}
		
		//这里是判断是否有错误的消息
		protected boolean hasErrors() {
			if(errors!=null&&errors.size()>0) return true;
			return false;
		}
		
	private static final long serialVersionUID = -7997106979510625723L;
	public static final String redirPath="redirect:";

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		errors.clear();
		//errors=new HashMap<String,String>();
		request.setAttribute("errors", errors);
		if(ServletFileUpload.isMultipartContent(request)) {
			request = new MultipartWrapper(request);
		}
		
		//对于文件的上传的话，那么要注意的是，我们可以理解为俩个请求
		//一个是跳转的请求，即method=list.还有一个就是文件上传的请求。
		DaoUtil.diObject(this);
		try {
			String method=request.getParameter("method");
			Method m=this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			int flag = checkAuth(request,m);
			if(flag==1) {
				response.sendRedirect("user.do?method=loginInput");
				return;
			} else if(flag==2) {
				request.setAttribute("errorMsg", "你没有权限访问该功能");
				request.getRequestDispatcher("/WEB-INF/inc/error.jsp").forward(request, response);
				return;
			}
		/*	if(flag==1){
				response.sendRedirect("user.do?method=loginInput");
				return;
			}
			if(flag==2){
				request.setAttribute("errorMsg","你没有权限操作该方法");
				request.getRequestDispatcher("/WEB-INF/inc/error.jsp").forward(request,response);
				return;
				
			}*/
			String path=(String) m.invoke(this, request,response);
			if(path.startsWith(redirPath)){
				response.sendRedirect(path.substring(redirPath.length()));
			}else{
				request.getRequestDispatcher("WEB-INF"+path).forward(request, response);
			}
				
			
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
			
		
		
	}


	/**
	 * 0表示的是可正常访问，1表示的是到登录页面。2表示的是抛出异常信息，不可以正常访问
	 * @param request
	 * @param m
	 * @return
	 */
	private int checkAuth(HttpServletRequest request, Method m) {
	/*	*//**
		 * 获取用户
		 *//*
		User user=(User) request.getSession().getAttribute("loginUser");
		*//**
		 * 如果用户是登录人员
		 *//*
		if(user!=null&&user.getType()==1){
			return 0;
		}
		*//**
		 * 如果这个方法是没有注解的话，表示的是只有管理员才能使用该方法。需要进行进一步的判断。
		 *//*
		
		if(!m.isAnnotationPresent(Auth.class)){
			if(user==null){
				return 1;
			}
			if(user.getType()!=1){
				return 2;
			}
		}else{//有注解的话，又进行进一步的判断。
			Auth auth=m.getAnnotation(Auth.class);
			String value=auth.value();
			if(value.equals("any")){
				return 0;
			}else if(value.equals("user")){
				if(user!=null){
					return 0;
				}else{
					return 1;
				}
			}
		}
		
		return 0;*/
		User lu = (User)request.getSession().getAttribute("loginUser");
		if(lu!=null&&lu.getType()==1) {
			//如果是管理员说明所有的功能都可以访问
			return 0;
		}
		if(!m.isAnnotationPresent(Auth.class)) {
			//没有Annotation说明该方法必须由超级管理员
			if(lu==null) {
				return 1;
			}else if(lu.getType()!=1) {
				return 2;
			}
		} else {
			Auth a = m.getAnnotation(Auth.class);
			String v = a.value();
			if(v.equals("any")) {
				return 0;
			} else if(v.equals("user")){
				if(lu==null)
					return 1;
				else return 0;
			}
		}
		return 0;
	}
}
