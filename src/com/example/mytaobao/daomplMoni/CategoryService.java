package com.example.mytaobao.daomplMoni;

import java.util.ArrayList;
import java.util.List;

import com.example.mytaobao.dao.ICategoryService;
import com.example.mytaobao.model.Category;

public class CategoryService implements ICategoryService {
	private List<Category>categories;
	public CategoryService(){
		categories=new ArrayList<Category>();
		categories.add(new Category(0, "服装"));//注意编号从0开始相对来说好计算一点
		categories.add(new Category(1, "手机"));
		categories.add(new Category(2, "电器"));
		categories.add(new Category(3, "图书"));
		categories.add(new Category(4, "鞋子"));
	}
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return categories.get(categoryId);
	}
	

}
