package com.lg.shop.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.lg.shop.dao.GoodsDao;
import com.lg.shop.dao.ICategoryDao;
import com.lg.shop.dao.IGoodsDao;
import com.lg.shop.dao.IUserDao;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.model.Goods;
import com.lg.shop.model.Pager;
import com.lg.shop.model.ShopException;
import com.lg.shop.model.User;
import com.lg.shop.util.DBUtil;
import com.lg.shop.util.RequestUtil;
import com.sun.net.httpserver.HttpsConfigurator;

import jdk.nashorn.internal.ir.RuntimeNode.Request;



public class GoodsServlet extends BaseServlet {
	 /**
	 * 
	 */
	public static final String redirPath="redirect:";
	private static final long serialVersionUID = 3752059040876909348L;
	
	private IGoodsDao goodsDao;
	private ICategoryDao categoryDao;
	
	
	
	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}
	@ShopDi
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Auth("any")
	public String list(HttpServletRequest req,HttpServletResponse resp){
		return "/goods/list.jsp";
	}
	
	public String addInput(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("cs",categoryDao.list());
		return "/goods/addInput.jsp";
	}
	
	public String add(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException{
		Goods goods=(Goods) RequestUtil.setParam(Goods.class, request);
		goods.setStatus(1);
		boolean b=true;
		int cid=0;
		RequestUtil.validate(Goods.class, request);
		try{
		    cid=Integer.parseInt(request.getParameter("cid"));
		}catch(NumberFormatException e){}
		/*if(cid==0){
		Map<String, String> errors=(Map<String, String>) request.getAttribute("errors");
		errors.put("cid","商品类别必须选择");
		b=false;
		}
		if(!b){
			request.setAttribute("cs",categoryDao.list());
			return "/goods/addInput.jsp";
		}*/
		if(cid==0) {
			this.getErrors().put("cid", "商品类别必须选择");
		}
		if(!this.hasErrors()) {
			//文件上传
			byte[] fs = (byte[])request.getAttribute("fs");
			System.out.println(fs.length+"----------------------------.");
			String fname = request.getParameter("img");
			fname = FilenameUtils.getName(fname);
			System.out.println(fname);
			RequestUtil.uploadFile(fname, "img", fs, request);

		}
		if(this.hasErrors()) {
			System.out.println("#######################################");
			System.out.println("#######################################");

			request.setAttribute("cs",categoryDao.list());
			
			Map<String,String> errors = (Map<String,String>)request.getAttribute("errors");
			System.out.println("错误的个数"+errors.size());
			System.out.println(errors.get("img"));
			return "/goods/addInput.jsp";
		}
		//GoodsDao.add(cid, goods);
		
		return redirPath+"goods.do?method=list";
	}
	 

}
