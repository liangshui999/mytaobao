package com.example.mytaobao.test.daompl;

import com.example.mytaobao.biz.UserManager;
import com.example.mytaobao.daomplMoni.UserService;
import com.example.mytaobao.model.User;

import android.test.AndroidTestCase;

public class UserManagerTest extends AndroidTestCase {
	private User user;
	private UserManager userManager;
	private UserService userService;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		user=new User("xg","000");
		userManager=new UserManager();
		userManager.register(new User("a","123456"));
		userService=(UserService) userManager.getIus();
		super.setUp();
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	//测试注册方法
	public void testRegister(){
		userManager.register(new User("hhaha","000"));
		userManager.register(user);
		assertEquals(4, userService.getUsers().size());
		
		
	}
	//测试登录方法
	public void testLogin(){
		userManager.register(user);
		assertEquals("xg", userManager.login("xg", "000").getUserId());
		assertEquals("admn", userManager.login("admin", "admin").getUserId());
	}
	//测试生成注册码的方法
	public void testGetCheckCode(){
		assertEquals(4, userManager.getCheckCode().length());
	}
	//测试判断用户是否存在的方法
	public void testIsExist(){
		assertEquals(true, userManager.isExist("admin"));
	}
	

}
