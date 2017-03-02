package com.lg.shop.web;



import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lg.shop.dao.IAddressDao;
import com.lg.shop.dao.IUserDao;
import com.lg.shop.dao.ShopDi;
import com.lg.shop.model.Address;
import com.lg.shop.model.Pager;
import com.lg.shop.model.ShopException;
import com.lg.shop.model.User;
import com.lg.shop.util.DBUtil;
import com.lg.shop.util.RequestUtil;







public class AddressServlet extends BaseServlet {
	 /**
	 * 
	 */
	public static final String redirPath="redirect:";
	private static final long serialVersionUID = 3752059040876909348L;
	private IUserDao userDao;
	private IAddressDao addressDao;
	

	public IAddressDao getAddressDao() {
		return addressDao;
	}

	@ShopDi
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	 

	 
	 
	 @Auth
	 public String addInput(HttpServletRequest request,HttpServletResponse response){
		 int userId=Integer.parseInt(request.getParameter("userId"));
		 User user=userDao.load(userId);
		 request.setAttribute("user",user);
		 return "/address/addInput.jsp";
	 }
	 
	 
	 @Auth
	 public String add(HttpServletRequest req,HttpServletResponse resp){
		 int userId=Integer.parseInt(req.getParameter("userId"));
		 System.out.println(userId);
		 System.out.println(userDao.load(userId));
		 if(!RequestUtil.validate(Address.class, req)){
			 User user=userDao.load(userId);
			 req.setAttribute("user",user);
			 return "/address/addInput.jsp";
		 }
		 Address address=(Address) RequestUtil.setParam(Address.class, req);
		 addressDao.add(address, userId);
		 return redirPath+req.getContextPath()+"/user.do?method=show&id="+userId;
		 
	 }
	@Auth
	public String delete(HttpServletRequest request,HttpServletResponse response){
		 int id=Integer.parseInt(request.getParameter("id"));
		 Address address=addressDao.load(id);
		 int userId=/*Integer.parseInt(request.getParameter("userId"));*/address.getUser().getId();
		 addressDao.delete(id);
		 return redirPath+request.getContextPath()+"/user.do?method=show&id="+userId;
		 
	}
	@Auth
	public String updateInput(HttpServletRequest request,HttpServletResponse response){
		int address_id=Integer.parseInt(request.getParameter("id"));
		int user_Id=Integer.parseInt(request.getParameter("userId"));
		 Address address=addressDao.load(address_id);
		 request.setAttribute("address",address);
		 return "/address/updateInput.jsp";
	}
	
	@Auth
	public String update(HttpServletRequest request,HttpServletResponse response){
		int address_id=Integer.parseInt(request.getParameter("address_id"));
		Address address=addressDao.load(address_id);
		if(!RequestUtil.validate(Address.class, request)){
			request.setAttribute("address",address);
			 return "/address/updateInput.jsp";
		}
		 Address newAddress=(Address) RequestUtil.setParam(Address.class, request);
		 address.setName(newAddress.getName());
		 address.setPhone(newAddress.getPhone());
		 address.setPostcode(newAddress.getPostcode());
		 addressDao.update(address);
		 return redirPath+request.getContextPath()+"/user.do?method=show&id="+address.getUser().getId();
	}
	 

}
