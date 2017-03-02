package com.lg.shop.dao;

import com.lg.shop.model.Goods;
import com.lg.shop.model.Pager;


public interface IGoodsDao {
	public void add(int cid,Goods goods);
	public void delete(int id);
	public void update(Goods goods);
	
	/**
	 * 根据商品的id进行商品的加载
	 */
	public Goods load(int id);
	/**
	 * 增加具体商品的库存
	 */
	public void addStock(int id,int num);
	/**
	 * 减少具体商品的库存
	 */
	public void decrease(int id,int num);
	
	/**
	 * 根据商品的类别和名称进行搜索
	 * 这样可以进行灵活的排序
	 */
	public Pager<Goods> find(int cid,String name);

}
