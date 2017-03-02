package com.lg.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lg.shop.model.Address;
import com.lg.shop.model.ShopException;
import com.lg.shop.model.User;

public class AddressDao extends BaseDao<Address>implements IAddressDao {
	private IUserDao userDao;
	
	
	   public IUserDao getUserDao() {
			return userDao;
		}

	   @ShopDi
		public void setUserDao(IUserDao userDao) {
			this.userDao = userDao;
		}
	/*public AddressDao() {
		// TODO Auto-generated constructor stub
		userDao=DAOFactory.getUserDao();
	}
	*/

	@Override
	public void add(Address address, int userId) {
		// TODO Auto-generated method stub
		User user=userDao.load(userId);
		if(user==null){
			throw new ShopException("添加地址的用户不存在");
		}
		address.setUser(user);
		super.add(address);
		

	}

	@Override
	public void update(Address address) {
		// TODO Auto-generated method stub
         super.update(address);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
         super.delete(Address.class, id);
	}

	@Override
	public Address load(int id) {
		// TODO Auto-generated method stub
		return super.load(Address.class, id);
	}

	@Override
	public List<Address> list(int userId) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("userId",userId);
		return super.list(Address.class, params);
	}
}
