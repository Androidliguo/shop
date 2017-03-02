package com.lg.shop.web;



import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.converters.ConverterFacade;

import com.lg.shop.dao.CategoryDao;
import com.lg.shop.dao.IAddressDao;
import com.lg.shop.dao.IUserDao;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.model.Address;
import com.lg.shop.model.Category;
import com.lg.shop.model.Pager;
import com.lg.shop.model.ShopException;
import com.lg.shop.model.User;
import com.lg.shop.util.DBUtil;
import com.lg.shop.util.RequestUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;








public class CategoryServlet extends BaseServlet {
	 /**
	 * 
	 */
	public static final String redirPath="redirect:";
	private static final long serialVersionUID = 3752059040876909348L;
	
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	

	public String list(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");
		List<Category> list=categoryDao.list(name);
		request.setAttribute("list",list);
		return "/category/list.jsp";
	}
	
	
	public String addInput(HttpServletRequest request,HttpServletResponse response){
		return "/category/addInput.jsp";
	}
	
	public String add(HttpServletRequest request,HttpServletResponse response){
		if(!RequestUtil.validate(Category.class, request)){
			return "/category/addInput.jsp";
		}
		Category category=(Category) RequestUtil.setParam(Category.class, request);
		categoryDao.add(category);
		return redirPath+"category.do?method=list";
	}
	
	public String show(HttpServletRequest request,HttpServletResponse response){
		int category_id=Integer.parseInt(request.getParameter("id"));
		Category category=categoryDao.load(category_id);
		request.setAttribute("category",category);
		return "/category/show.jsp";
		
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response){
		int category_id=Integer.parseInt(request.getParameter("id"));
		categoryDao.delete(category_id);
		return redirPath+"category.do?method=list";
	
	}
	
	public String updateInput(HttpServletRequest request,HttpServletResponse response){
		int category_id=Integer.parseInt(request.getParameter("id"));
		Category category=categoryDao.load(category_id);
		request.setAttribute("category",category);
		return "/category/updateInput.jsp";
		
	}
	
	public String update(HttpServletRequest request,HttpServletResponse response){
		if(!RequestUtil.validate(Category.class, request)){
			return "/category/updateInput.jsp";
		}
		Category category=(Category) RequestUtil.setParam(Category.class, request);
		int category_id=Integer.parseInt(request.getParameter("id"));
		Category c=categoryDao.load(category_id);
		c.setName(category.getName());
		categoryDao.update(c);
		return redirPath+"category.do?method=list";
		
	}
	
	 

}
