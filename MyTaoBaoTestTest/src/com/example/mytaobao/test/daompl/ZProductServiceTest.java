package com.example.mytaobao.test.daompl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.mytaobao.R;
import com.example.mytaobao.daomplZhenshi.ZProductService;
import com.example.mytaobao.model.Product;
import com.example.mytaobao.util.ImageUtil;
import com.example.mytaobao.util.MyLog;

import android.graphics.Bitmap;
import android.os.Environment;
import android.test.AndroidTestCase;

public class ZProductServiceTest extends AndroidTestCase {
	private ZProductService zProductService;
	private String path;
	private String name;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		zProductService=new ZProductService();
		path=Environment.getExternalStorageDirectory().getCanonicalPath();
		name="p1.jpg";
		
		Product product1=new Product(1,"三星 GT-S5830", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p1.jpg")),
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		
		Product product2=new Product(2,"HTC A510e", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p2.jpg")),
				1514.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product3=new Product(3,"三星 I9100", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p3.jpg")),
				3266.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product4=new Product(4,"中兴 U880（TD版）", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p4.jpg")), 989.00,
				"网络类型:TD-SCDMA(3G) 操作系统:ANDROID  ");
		Product product5=new Product(5,"Sony Ericsson索尼爱立信 ", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p5.jpg")), 2584.00,
				"网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product6=new Product(6,"摩托罗拉 Defy", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p6.jpg")),
				1851.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product7=new Product(7,"中兴 V880", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p7.jpg")),
				957.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		List<Product>products=new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		products.add(product5);
		products.add(product6);
		products.add(product7);
		zProductService.addAll(products);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testInsert(){
		byte[] b=null;
		b = ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, name));
		Product product=new Product(1,"三星 GT-S5830", 1,b,
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		zProductService.insert(product);
		assertEquals(15880,b.length);
	}
	
	public void testGetById(){
		byte[] b=zProductService.getById(1).getImage();
		Bitmap bitmap=ImageUtil.convertByteArrayToBitmap(b);
		ImageUtil.saveBitmapToFile(bitmap, "13.jpg", path);
		assertEquals(16880,b.length);
		//assertNull(b);
		//MyLog.d("ZProductServiceTest", b.length+"haha");
		//assertNull(bitmap);
		
	}
	public void testAddAll(){
		Product product1=new Product(1,"三星 GT-S5830", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p1.jpg")),
				1630.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		
		Product product2=new Product(2,"HTC A510e", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p2.jpg")),
				1514.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product3=new Product(3,"三星 I9100", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p3.jpg")),
				3266.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product4=new Product(4,"中兴 U880（TD版）", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p4.jpg")), 989.00,
				"网络类型:TD-SCDMA(3G) 操作系统:ANDROID  ");
		Product product5=new Product(5,"Sony Ericsson索尼爱立信 ", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p5.jpg")), 2584.00,
				"网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product6=new Product(6,"摩托罗拉 Defy", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p6.jpg")),
				1851.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		Product product7=new Product(7,"中兴 V880", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p7.jpg")),
				957.00, "网络类型:WCDMA(3G) 操作系统:ANDROID ");
		List<Product>products=new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product4);
		products.add(product5);
		products.add(product6);
		products.add(product7);
		zProductService.addAll(products);
		byte[]b=zProductService.getById(4).getImage();
		Bitmap bitmap=ImageUtil.convertByteArrayToBitmap(b);
		ImageUtil.saveBitmapToFile(bitmap, "4.jpg", path);
	}
	public void testGetByPage(){
		List<Product>products=zProductService.getByPage(2, 3);
		assertEquals(1, products.size());
		//assertEquals(3266.00, products.get(0).getPrice());
	}
	public void testGetAllProduct(){
		List<Product>products=zProductService.getAllProducts();
		assertEquals(7, products.size());
	}
	public void testGetByName(){
		List<Product>products=zProductService.getByName("中兴");
		assertEquals(2, products.size());
	}
	public void testDelById(){
		zProductService.del(3);
	}
	public void testModifyById(){
		zProductService.modify(7, 2, "张三丰", "武当掌门", 2000.00);
		
	}

}
