package com.example.mytaobao.daompl;

import java.util.HashMap;
import java.util.Map;

import com.example.mytaobao.dao.IUserService;
import com.example.mytaobao.model.User;

public class UserService implements IUserService {
	private Map<String,User>users;
	/*
	 * 在构造方法里面提供一个初始化的用户
	 * */
	public UserService() {
		User user=new User("admin", "admin");
		users=new HashMap<String, User>();
		users.put(user.getUserId(), user);
	}
	
	public Map<String, User> getUsers() {
		return users;
	}	

	/*
	 * 插入用户
	 * */
	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		users.put(user.getUserId(), user);//put本身也有返回值，如果先前有同样的key了，这个进去之后会替代掉原来的，他返回的就是原来的value值
		return user;
	}
	/*
	 * 根据账户名查找用户
	 * */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return users.get(userId);
	}

}
