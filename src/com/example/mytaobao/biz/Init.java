package com.example.mytaobao.biz;
//ר�Ÿ���ʵ��������������ֻ����һ��userManager�����ܵ���new���������ݲ�ͳһ
//������������UserManagerֻ���������
//��������Ҳֻ����һ��productmanager����������׵�,˵���ˣ�����Դֻ����һ��
public class Init {
	private static UserManager userManager=new UserManager();
	private static ProductManager productManager=new ProductManager();
	public static UserManager getUserManager() {
		return userManager;
	}
	public static ProductManager getProductManager() {
		return productManager;
	}
	
	
}
