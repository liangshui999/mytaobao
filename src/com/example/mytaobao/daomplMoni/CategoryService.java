package com.example.mytaobao.daomplMoni;

import java.util.ArrayList;
import java.util.List;

import com.example.mytaobao.dao.ICategoryService;
import com.example.mytaobao.model.Category;

public class CategoryService implements ICategoryService {
	private List<Category>categories;
	public CategoryService(){
		categories=new ArrayList<Category>();
		categories.add(new Category(0, "��װ"));//ע���Ŵ�0��ʼ�����˵�ü���һ��
		categories.add(new Category(1, "�ֻ�"));
		categories.add(new Category(2, "����"));
		categories.add(new Category(3, "ͼ��"));
		categories.add(new Category(4, "Ь��"));
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
