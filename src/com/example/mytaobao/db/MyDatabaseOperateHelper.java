package com.example.mytaobao.db;

import com.example.mytaobao.util.MyLog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * 提供数据库增删改查的通用方法
 * 使用了单例设计模式中的懒汉式
 * */
public class MyDatabaseOperateHelper {
	private MyDatabaseCreateHelper createHelper;
	private static MyDatabaseOperateHelper operateHelper=new MyDatabaseOperateHelper();

	private MyDatabaseOperateHelper() {
		createHelper=MyDatabaseCreateHelper.getInstance();		
	}
	
	public static MyDatabaseOperateHelper getDatabaseOperateHelper(){
		return operateHelper;
	}
	/*
	 * 增删改的通用方法
	 * */
	public void zsg(String sql,Object[]args){
		SQLiteDatabase taobaodb=null;
		try {
			taobaodb=createHelper.getWritableDatabase();
			taobaodb.execSQL(sql, args);	
		} catch (Exception e) {
			// TODO: handle exception
			MyLog.d("MyDatabaseOperateHelper", createHelper.getDatabaseName());
		}finally{
			taobaodb.close();
		}
		
	}
	/*
	 *插入的另外一种方法 
	 * */
	public long insert(String table,ContentValues values){
		SQLiteDatabase taobaodb=null;
		long l=-1;
		try {
			taobaodb=createHelper.getWritableDatabase();
			l=taobaodb.insert(table, null, values);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			taobaodb.close();
		}
		return l;
	}
	
	/*
	 * 查询的方法
	 * */
	public Object query(String sql,String[]args,HandleCursor handleCursor){
		SQLiteDatabase taobaodb=null;
		Object object=null;
		try {
			taobaodb=createHelper.getWritableDatabase();
			Cursor cursor=taobaodb.rawQuery(sql, args);
			object=handleCursor.handleCursor(cursor);//不同的表的cursor有不同的处理方法
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{			
			taobaodb.close();
		}
		return object;
	}
}
