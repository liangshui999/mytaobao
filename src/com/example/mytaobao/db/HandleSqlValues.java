package com.example.mytaobao.db;

public interface HandleSqlValues {
	/*
	 * ��ͬ�Ķ����в�ͬ�ı�������������˵user��2����user.userId;user.passWord
	 * �ͷ���new String[]{user.userId+"";user.passWord+""};
	 * �õ�ʱ���object����ǿ������ת��
	 * */
	public String[] handSqlValues(Object object);
	

}
