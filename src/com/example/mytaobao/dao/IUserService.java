package com.example.mytaobao.dao;

import com.example.mytaobao.model.User;

public interface IUserService {
	public User insert(User user);//插入user对象
	public User getUserById(String userId);//根据userId查找对应的user对象

}
