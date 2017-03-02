package com.lg.shop.dao;

import java.util.HashMap;
import java.util.Map;

import com.lg.shop.model.Category;
import com.lg.shop.model.Goods;
import com.lg.shop.model.Pager;
import com.lg.shop.model.ShopException;
import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

public class GoodsDao extends BaseDao<Goods> implements IGoodsDao {
	private ICategoryDao categoryDao;
	
	

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@ShopDi
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void add(int cid,Goods goods) {
		// TODO Auto-generated method stub
		Category category=categoryDao.load(cid);
		if(category==null){
			throw new ShopException("要添加的商品类别不存在");
		}
		goods.setCategory(category);
		super.add(goods);

	}

	@Override
	public void delete(int id) {
		// TODO 如果用户购买了商品的话，那么就不能删除。如果该商品存在订单的话，也不能被删除。
		//如果要删除商品的话，需要删除商品的图片
		super.delete(Goods.class, id);

	}

	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		super.update(goods);

	}

	@Override
	public Goods load(int id) {
		// TODO Auto-generated method stub
		return super.load(Goods.class, id);
	}

	@Override
	public void addStock(int id, int num) {
		// TODO Auto-generated method stub
		Goods goods=this.load(id);
		goods.setStock(goods.getStock()+num);
		this.update(goods);

	}

	@Override
	public void decrease(int id, int num) {
		// TODO Auto-generated method stub
		Goods goods=this.load(id);
		goods.setStock(goods.getStock()-num);
		this.update(goods);
	}

	@Override
	public Pager<Goods> find(int cid, String name) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new  HashMap<>();
		if(cid>0){
			params.put("cid", cid);
		}
		if(name!=null&&!"".equals(name.trim())){
			params.put(name,"%"+name+"%");
		}
		return super.find(Goods.class, params);
	}

}
