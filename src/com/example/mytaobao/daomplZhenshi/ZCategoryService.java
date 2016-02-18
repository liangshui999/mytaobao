package com.example.mytaobao.daomplZhenshi;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mytaobao.dao.ICategoryService;
import com.example.mytaobao.db.HandleCursor;
import com.example.mytaobao.db.MyDatabaseCreateHelper;
import com.example.mytaobao.db.MyDatabaseOperateHelper;
import com.example.mytaobao.model.Category;
import com.example.mytaobao.util.MyApplication;

public class ZCategoryService implements ICategoryService {
	private MyDatabaseOperateHelper helper;
	public ZCategoryService() {
		helper=MyDatabaseOperateHelper.getDatabaseOperateHelper();
		helper.zsg("insert into category(categoryId,categoryName) values(?,?)",new String[]{ "0","服装" });
		helper.zsg("insert into category(categoryId,categoryName) values(?,?)",new String[]{ "1","手机" });
		helper.zsg("insert into category(categoryId,categoryName) values(?,?)",new String[]{ "2","电器" });
		helper.zsg("insert into category(categoryId,categoryName) values(?,?)",new String[]{ "3","图书" });
		helper.zsg("insert into category(categoryId,categoryName) values(?,?)",new String[]{ "4","鞋子" });

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> list=(List<Category>) helper.query("select * from category", null, new HandleCursor() {
			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				List<Category> categories=new ArrayList<Category>();
				while(cursor.moveToNext()){
					int categoryId=cursor.getInt(cursor.getColumnIndex("categoryId"));
					String categoryName=cursor.getString(cursor.getColumnIndex("categoryName"));
					Category category=new Category(categoryId,categoryName);
					categories.add(category);
				}
				return categories;
			}
		});
		return list;
		/*List<Category>categories=new ArrayList<Category>();
		String sql="select * from category";
		Cursor cursor=taobaodb.rawQuery(sql, null);
		while(cursor.moveToNext()){			
			int categoryId=cursor.getInt(cursor.getColumnIndex("categoryId"));
			String categoryName=cursor.getString(cursor.getColumnIndex("categoryName"));
			Category category=new Category(categoryId,categoryName);
			categories.add(category);
		}
		cursor.close();
		return categories;*/
	}

	@Override
	public Category getCategoryById(int categoryId) {
		Category c=(Category) helper.query("select * from category where categoryId=?",new String[]{ categoryId+""}, new HandleCursor() {
			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				Category category=null;
				if(cursor.moveToNext()){
					int Id=cursor.getInt(cursor.getColumnIndex("categoryId"));
					String categoryName=cursor.getString(cursor.getColumnIndex("categoryName"));
					category=new Category(Id,categoryName);
				}
				return category;
			}
		});
		return c;
		// TODO Auto-generated method stub
		/*Category category=null;
		String sql="select * from category where categoryId=?";
		Cursor cursor=taobaodb.rawQuery(sql, new String[]{ categoryId+""});
		if(cursor.moveToNext()){
			int Id=cursor.getInt(cursor.getColumnIndex("categoryId"));
			String categoryName=cursor.getString(cursor.getColumnIndex("categoryName"));
			category=new Category(Id,categoryName);
		}
		cursor.close();
		return category;*/
	}



}
