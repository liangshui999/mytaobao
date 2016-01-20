package com.example.mytaobao.util;

import java.util.ArrayList;
import java.util.List;

import com.example.mytaobao.R;
import com.example.mytaobao.model.Product;

public class TestConcurrentModificationException {
	
	private List<Product> products=new ArrayList<Product>();//初始给定的数据放在这个集合里面，就是从这个集合里面增删改查
	//商品编号从0开始，比较好计算一点
	public TestConcurrentModificationException() {
		products.add(new Product(0,"三星 GT-S58", 1, R.drawable.p1,
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(1,"三星 GT-S5830", 1, R.drawable.p1,
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(2,"HTC A510e", 1, R.drawable.p2,
				1514.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(3,"三星 I9100", 1, R.drawable.p3,
				3266.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(4,"中兴 U880（TD版）", 1,
				R.drawable.p4, 989.00,
				"网络类型:TD-SCDMA(3G) 操作系统:ANDROID  "));
		products.add(new Product(5,"Sony Ericsson索尼爱立信 ", 1,
				R.drawable.p5, 2584.00,
				"网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(6,"摩托罗拉 Defy", 1, R.drawable.p6,
				1851.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(7,"中兴 V880", 1, R.drawable.p7,
				957.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(8,"HTC S710e", 1, R.drawable.p8,
				2671.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(9,"摩托罗拉 ME525",1, R.drawable.p9,
				1853.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(10,"G12", 1, R.drawable.p10,
				2470.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(11,"摩托罗拉 ME525+", 1,
				R.drawable.p11, 1923.00,
				"网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(12,"Sony Ericsson", 1,
				R.drawable.p12, 2231.00,"网络类型:WCDMA(3G) 操作系统:ANDROID "));
		products.add(new Product(13,"Lenovo", 1, R.drawable.p13,997.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		//products.add(new Product(13,"Lenovo", 1, R.drawable.p13,997.00, "网络类型:WCDMA(3G) 操作系统:ANDROID "));
		//Log.d("MainActivity",""+products.size());
		
	}
	/*
	 * 获取分页数据，pageIndex表示页数，从0开始计数
	 * pageSize表示每页的数据量
	 * */
	public void main(String[]args){
		int pageIndex=0;
		int pageSize=5;
		List<Product>products1=getPage(pageIndex, pageSize);
		List<Product>products2=getPage(++pageIndex, pageSize);
		products1.addAll(products2);
		System.out.println(products1.size());
	}
	public List<Product> getPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<Product> p=new ArrayList<Product>();
		
		int maxPage=0;//maxPage表示最大页数
		int size=products.size();//size表示集合的长度
		if(size!=0){
			if(size%pageSize==0){
				maxPage=size/pageSize-1;
			}else{
				maxPage=size/pageSize;
			}
			//对给定的页数进行判定，不能超过最大页数，也不能小于0
			if(pageIndex<0){
				pageIndex=0;
			}
			if(pageIndex>maxPage){
				pageIndex=maxPage;
			}
			//判断每页的数目，数目不能小于0
			if(pageSize<0){
				pageSize=0;
			}
			//再判断是否是最后一页，最后一页和前面的页数的取法不一样
			if(pageIndex==maxPage){
				p=products.subList(pageIndex*pageSize,size);//最后一页只能取到size，不然会越界,注意取头不取尾，所以应该是size，而不是size-1
			}else{
				p=products.subList(pageIndex*pageSize,(pageIndex+1)*pageSize );
			}
		}
		
		return p;
	}
}
