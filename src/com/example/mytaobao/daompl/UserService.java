package com.example.mytaobao.daompl;

import java.util.HashMap;
import java.util.Map;

import com.example.mytaobao.dao.IUserService;
import com.example.mytaobao.model.User;

public class UserService implements IUserService {
	private Map<String,User>users;
	/*
	 * �ڹ��췽�������ṩһ����ʼ�����û�
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
	 * �����û�
	 * */
	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		users.put(user.getUserId(), user);//put����Ҳ�з���ֵ�������ǰ��ͬ����key�ˣ������ȥ֮��������ԭ���ģ������صľ���ԭ����valueֵ
		return user;
	}
	/*
	 * �����˻��������û�
	 * */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return users.get(userId);
	}

}
