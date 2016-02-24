package com.example.mytaobao.daomplZhenshi;

import android.database.Cursor;

import com.example.mytaobao.dao.IUserService;
import com.example.mytaobao.db.HandleCursor;
import com.example.mytaobao.db.MyDatabaseOperateHelper;
import com.example.mytaobao.model.User;

public class ZUserService implements IUserService {
	private MyDatabaseOperateHelper helper;	
	public ZUserService() {
		helper=MyDatabaseOperateHelper.getDatabaseOperateHelper();
	}
	/*
	 * �����û�
	 * */
	@Override
	public User insert(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user values(?,?)";
		String[] values=new String[]{user.getUserId(),user.getPassWord()};
		helper.zsg(sql, values);
		return user;
	}
	/*
	 * �����û�id�����û�
	 * */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		String sql="select * from user where userId=?";
		String[] values=new String[]{userId};
		User user=(User) helper.query(sql, values, new HandleCursor() {
			
			@Override
			public Object handleCursor(Cursor cursor) {
				// TODO Auto-generated method stub
				User user1=null;
				if(cursor.moveToNext()){
					String id=cursor.getString(cursor.getColumnIndex("userId"));
					String name=cursor.getString(cursor.getColumnIndex("passWord"));
					user1=new User(id, name);
				}
				return user1;
			}
		});
		return user;
	}

}
