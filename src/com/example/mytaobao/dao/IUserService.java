package com.example.mytaobao.dao;

import com.example.mytaobao.model.User;

public interface IUserService {
	public User insert(User user);//����user����
	public User getUserById(String userId);//����userId���Ҷ�Ӧ��user����

}
