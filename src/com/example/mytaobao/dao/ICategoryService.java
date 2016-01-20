package com.example.mytaobao.dao;

import java.util.List;

import com.example.mytaobao.model.Category;

public interface ICategoryService {
	public List<Category> getAllCategories();//获取所有的类别
	public Category getCategoryById(int categoryId);//通过类别的id获取对应的类别

}
