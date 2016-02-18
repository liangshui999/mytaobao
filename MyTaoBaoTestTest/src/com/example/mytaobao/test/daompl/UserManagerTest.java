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
	//����ע�᷽��
	public void testRegister(){
		userManager.register(new User("hhaha","000"));
		userManager.register(user);
		assertEquals(4, userService.getUsers().size());
		
		
	}
	//���Ե�¼����
	public void testLogin(){
		userManager.register(user);
		assertEquals("xg", userManager.login("xg", "000").getUserId());
		assertEquals("admn", userManager.login("admin", "admin").getUserId());
	}
	//��������ע����ķ���
	public void testGetCheckCode(){
		assertEquals(4, userManager.getCheckCode().length());
	}
	//�����ж��û��Ƿ���ڵķ���
	public void testIsExist(){
		assertEquals(true, userManager.isExist("admin"));
	}
	

}
