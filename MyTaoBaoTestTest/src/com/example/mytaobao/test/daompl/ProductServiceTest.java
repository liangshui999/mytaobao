package com.example.mytaobao.test.daompl;

import java.util.List;

import android.test.AndroidTestCase;

import com.example.mytaobao.R;
import com.example.mytaobao.daompl.ProductService;
import com.example.mytaobao.model.Product;

public class ProductServiceTest extends AndroidTestCase{
	//private List<Product> products;
	@Override
	//protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		//super.setUp();
		/*
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
				*/
	//}

	//@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	//测试分页的方法，分页方法比较容易出错
	public void testgetByPage(){
		int pageIndex=4;
		int pageSize=3;
		//assertEquals(products.size(), 5);
		int size=new ProductService().getByPage(pageIndex, pageSize).size();
		assertEquals(size, 2);
				
		
		
	}

}
