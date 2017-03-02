package com.lg.shop.dao;

import java.util.List;

import com.lg.shop.model.Category;


public interface ICategoryDao {
  public void add(Category category);
  public void delete(int id);
  public void update(Category category);
  public Category load(int id);
  public List<Category> list(String name);
  public List<Category> list ();
}
