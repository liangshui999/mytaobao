package com.example.mytaobao.biz;

import com.example.mytaobao.dao.IUserService;
import com.example.mytaobao.daompl.UserService;
import com.example.mytaobao.model.User;

public class UserManager {
	private IUserService ius;

	public UserManager() {
		ius=new UserService();
	}
	
	public IUserService getIus() {
		return ius;
	}

	//�û�ע��,�û�ûע��ɹ���return null��
	public User register(User user){		
		ius.insert(user);
		return user;
	}
	//�û���¼,���ж��û��Ƿ���ڣ����ж������Ƿ���ȷ
	//�û������ڻ������벻��ȷ����return null��
	public User login(String userId,String password){
		User user=null;
		user=ius.getUserById(userId);
		if(user==null){
			return null;
		}else{
			if(password.equals(user.getPassWord())){
				return user;
			}else{
				return null;
			}
		}
	}
	//�������4λ������֤�룬�û�ע��ʱ����д��
	public String getCheckCode(){
		String checkCode=null;
		Integer integer=(int) (Math.random()*100000);
		checkCode=String.valueOf(integer);
		if(checkCode.length()!=4){
			return getCheckCode();
		}else{
			return checkCode;
		}
	}
	//�ж�ĳ���û��Ƿ��Ѿ�����
	public boolean isExist(String userId){
		User user=ius.getUserById(userId);
		if(user!=null){
			return true;
		}else{
			return false;
		}
	}

}
