package com.example.mytaobao.db;

import android.database.Cursor;

/*
 * 不同的表查出来的cursor对象的处理方法是不同的，因此需要表自己来实现该接口
 * */
public interface HandleCursor {
	public Object handleCursor(Cursor cursor);

}
