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

	//用户注册,用户没注册成功会return null。
	public User register(User user){		
		ius.insert(user);
		return user;
	}
	//用户登录,先判断用户是否存在，再判断密码是否正确
	//用户不存在或者密码不正确都是return null。
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
	//随机生成4位数的验证码，用户注册时须填写的
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
	//判断某个用户是否已经存在
	public boolean isExist(String userId){
		User user=ius.getUserById(userId);
		if(user!=null){
			return true;
		}else{
			return false;
		}
	}

}
