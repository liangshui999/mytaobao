package com.example.mytaobao.dao;

import java.util.List;

import com.example.mytaobao.model.Category;

public interface ICategoryService {
	public List<Category> getAllCategories();//��ȡ���е����
	public Category getCategoryById(int categoryId);//ͨ������id��ȡ��Ӧ�����

}
