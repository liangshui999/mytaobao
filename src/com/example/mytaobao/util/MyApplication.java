package com.example.mytaobao.util;

import android.app.Application;
import android.content.Context;
/*
 * 获取全局的context，有些地方不太容易取得context，就用这种方法
 * */
public class MyApplication extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context=getApplicationContext();		
	}
	public static Context getContext(){		
		return context;
	}
	
	

}
