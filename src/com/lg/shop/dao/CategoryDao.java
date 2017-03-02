package com.lg.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lg.shop.model.Category;

public class CategoryDao extends BaseDao<Category> implements ICategoryDao {

	@Override
	public void add(Category category) {
		// TODO Auto-generated method stub
		super.add(category);

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		super.delete(Category.class, id);

	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		super.update(category);

	}

	@Override
	public Category load(int id) {
		// TODO Auto-generated method stub
		return super.load(Category.class, id);
	}

	@Override
	public List<Category> list(String name) {
		Map<String,Object> params=new HashMap<>();
		if(name!=null&&!"".equals(name)){
			params.put("name", "%"+name+"%");
		}
		// TODO Auto-generated method stub
		return super.list(Category.class, params);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return super.list(Category.class, null);
	}

}
