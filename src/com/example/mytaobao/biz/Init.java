package com.example.mytaobao.biz;
//专门负责实例化，整个程序只能有一个userManager，不能到处new，否则数据不统一
//其他组件里面的UserManager只能用这儿的
//整个程序也只能有一个productmanager，否则会乱套的,说白了，数据源只能有一个
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
