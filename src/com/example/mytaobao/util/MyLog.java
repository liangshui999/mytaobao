package com.example.mytaobao.util;

import android.util.Log;

public class MyLog {
	private static final int VERBOSE=1;
	private static final int DEBUG=2;
	private static final int INFO=3;
	private static final int WARN=4;
	private static final int ERROR=5;
	private static final int NOTHING=6;
	private static final int LEVEL=VERBOSE;
	public static void v(String tag,String msg){
		if(VERBOSE>=LEVEL)
			Log.v(tag, msg);
	}
	public static void d(String tag,String msg){
		if(DEBUG>=LEVEL)
			Log.d(tag, msg);
	}
	public static void i(String tag,String msg){
		if(INFO>=LEVEL)
			Log.i(tag, msg);
	}
	public static void w(String tag,String msg){
		if(WARN>=LEVEL)
			Log.w(tag, msg);
	}
	public static void e(String tag,String msg){
		if(ERROR>=LEVEL)
			Log.e(tag, msg);
	}

}
