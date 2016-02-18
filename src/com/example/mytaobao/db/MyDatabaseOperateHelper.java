package com.example.mytaobao.db;

import com.example.mytaobao.util.MyLog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * �ṩ���ݿ���ɾ�Ĳ��ͨ�÷���
 * ʹ���˵������ģʽ�е�����ʽ
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
	 * ��ɾ�ĵ�ͨ�÷���
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
	 *���������һ�ַ��� 
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
	 * ��ѯ�ķ���
	 * */
	public Object query(String sql,String[]args,HandleCursor handleCursor){
		SQLiteDatabase taobaodb=null;
		Object object=null;
		try {
			taobaodb=createHelper.getWritableDatabase();
			Cursor cursor=taobaodb.rawQuery(sql, args);
			object=handleCursor.handleCursor(cursor);//��ͬ�ı��cursor�в�ͬ�Ĵ�����
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{			
			taobaodb.close();
		}
		return object;
	}
}
