package com.example.mytaobao.test.daompl;

import java.io.IOException;

import com.example.mytaobao.util.ImageUtil;

import android.os.Environment;
import android.test.AndroidTestCase;

public class ImageUtilTest extends AndroidTestCase {
	private String path;
	private String name;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		path=Environment.getExternalStorageDirectory().getCanonicalPath();
		name="p1.jpg";
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	public void testgetBitmapFromFile(){
		assertNotNull(ImageUtil.getBitmapFromFile(path, name));
	}
	public void testconvertBitmapToByteArray(){
		assertNotNull(ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, name)));
	}
	public void testconvertByteArrayToBitmap(){
		assertNotNull(ImageUtil.convertByteArrayToBitmap(ImageUtil.convertBitmapToByteArray(ImageUtil.getBitmapFromFile(path, name))));
	}
	public void testsaveBitmapToFile(){
		ImageUtil.saveBitmapToFile(ImageUtil.getBitmapFromFile(path, name), "8.jpg", path);
	}
	

}
