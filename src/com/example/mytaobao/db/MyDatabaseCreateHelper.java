package com.example.mytaobao.db;

import com.example.mytaobao.util.MyApplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.NfcAdapter.CreateBeamUrisCallback;

public class MyDatabaseCreateHelper extends SQLiteOpenHelper {
	private String createCategory="create table category("
			+"categoryId integer primary key,"
			+"categoryName text)";
	private String createUser="create table user("
			+"userId text primary key,"
			+"passWord text not null)";
	private String createProduct="create table product("
			+"id integer primary key,"
			+"name text not null,"
			+"categoryId integer,"
			+"pictrue blob,"
			+"price real,"
			+"note text,"
			+"constraint waijian foreign key(categoryId) references category(categoryId))";
	private static Context context;
	private static MyDatabaseCreateHelper helper=new MyDatabaseCreateHelper(MyApplication.getContext(), "taobaodb", null, 1);//MyApplication.getContext()注意这里得直接进行赋值
	
	public static MyDatabaseCreateHelper getInstance(){
		return helper;
	}

	private MyDatabaseCreateHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		context=MyApplication.getContext();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(createCategory);
		db.execSQL(createUser);
		db.execSQL(createProduct);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
