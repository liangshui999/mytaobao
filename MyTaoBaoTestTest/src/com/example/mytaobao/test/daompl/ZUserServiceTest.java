package com.example.mytaobao.test.daompl;

import com.example.mytaobao.daomplZhenshi.ZUserService;
import com.example.mytaobao.model.User;

import android.test.AndroidTestCase;

public class ZUserServiceTest extends AndroidTestCase {
	private ZUserService zUserService;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		zUserService=new ZUserService();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	/*
	 *测试插入用户方法 
	 * */
	public void testInsert(){
		User user1=new User("zhangsan", "000");
		User user2=new User("lisi", "111");
		User user3=new User("wangwu", "222");
		zUserService.insert(user1);
		zUserService.insert(user2);
		zUserService.insert(user3);
	}
	/*
	 *测试按用户id查找用户的方法 
	 * */
	public void testGetUserById(){
		User user=zUserService.getUserById("wangwu");
		assertEquals("111", user.getPassWord());
	}
	

}
