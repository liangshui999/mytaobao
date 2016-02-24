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
		
		Product product1=new Product(1,"���� GT-S5830", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p1.jpg")),
				1630.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		
		Product product2=new Product(2,"HTC A510e", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p2.jpg")),
				1514.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product3=new Product(3,"���� I9100", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p3.jpg")),
				3266.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product4=new Product(4,"���� U880��TD�棩", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p4.jpg")), 989.00,
				"��������:TD-SCDMA(3G) ����ϵͳ:ANDROID  ");
		Product product5=new Product(5,"Sony Ericsson���ᰮ���� ", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p5.jpg")), 2584.00,
				"��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product6=new Product(6,"Ħ������ Defy", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p6.jpg")),
				1851.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product7=new Product(7,"���� V880", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p7.jpg")),
				957.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
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
		Product product=new Product(1,"���� GT-S5830", 1,b,
				1630.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
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
		Product product1=new Product(1,"���� GT-S5830", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p1.jpg")),
				1630.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		
		Product product2=new Product(2,"HTC A510e", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p2.jpg")),
				1514.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product3=new Product(3,"���� I9100", 1,ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p3.jpg")),
				3266.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product4=new Product(4,"���� U880��TD�棩", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p4.jpg")), 989.00,
				"��������:TD-SCDMA(3G) ����ϵͳ:ANDROID  ");
		Product product5=new Product(5,"Sony Ericsson���ᰮ���� ", 1,
				ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p5.jpg")), 2584.00,
				"��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product6=new Product(6,"Ħ������ Defy", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p6.jpg")),
				1851.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
		Product product7=new Product(7,"���� V880", 1, ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, "p7.jpg")),
				957.00, "��������:WCDMA(3G) ����ϵͳ:ANDROID ");
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
		List<Product>products=zProductService.getByName("����");
		assertEquals(2, products.size());
	}
	public void testDelById(){
		zProductService.del(3);
	}
	public void testModifyById(){
		zProductService.modify(7, 2, "������", "�䵱����", 2000.00);
		
	}

}
