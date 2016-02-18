package com.example.mytaobao.test.daompl;

import com.example.mytaobao.daomplZhenshi.ZCategoryService;

import android.test.AndroidTestCase;

public class ZCategoryServiceTest extends AndroidTestCase {
	private ZCategoryService zCategoryService;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		zCategoryService=new ZCategoryService();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	public void testGetAllCategories(){
		int sum=zCategoryService.getAllCategories().size();
		assertEquals(5, sum);
	}
	public void testGetCategoryById(){
		String name=zCategoryService.getCategoryById(0).getCategoryName();
		assertEquals("·þ×°", name);
	}
	
	

}
