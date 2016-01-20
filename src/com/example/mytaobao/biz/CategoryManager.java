package com.example.mytaobao.biz;

import java.util.List;

import com.example.mytaobao.dao.ICategoryService;
import com.example.mytaobao.daompl.CategoryService;
import com.example.mytaobao.model.Category;

public class CategoryManager {
	private ICategoryService ic=new CategoryService();;
	public List<Category> getAllCategories(){		
		return ic.getAllCategories();
	}
	public Category getCategoryById(int categoryId){
		return ic.getCategoryById(categoryId);
	}

}
