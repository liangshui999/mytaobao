package com.example.mytaobao.test.daompl;

import com.example.mytaobao.externaltorage.ExternalStorageHelper;

import android.test.AndroidTestCase;

public class ExternalStorageHelperTest extends AndroidTestCase {
	private ExternalStorageHelper helper;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		helper=new ExternalStorageHelper();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testgetImageFileDir(){
		helper.getImageFileDir("mytaobaoimage");
	}
	

}
