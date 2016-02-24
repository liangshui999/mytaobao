package com.example.mytaobao.db;

public interface HandleSqlValues {
	/*
	 * 不同的对象有不同的变量个数，比如说user有2个，user.userId;user.passWord
	 * 就返回new String[]{user.userId+"";user.passWord+""};
	 * 用的时候对object进行强制类型转换
	 * */
	public String[] handSqlValues(Object object);
	

}
