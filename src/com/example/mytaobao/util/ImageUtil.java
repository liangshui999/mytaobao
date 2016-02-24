package com.example.mytaobao.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ImageUtil {
	/*
	 * 根据文件路径从本地加载图片，转换成bitmap类型
	 * path是路径
	 * name是文件名称
	 * */
	public static Bitmap getBitmapFromFile(String path,String name){
		return BitmapFactory.decodeFile(path+"/"+name);
	}
	/*
	 * 把bitmap转换成字节数组byte[]
	 * */
	public static byte[] convertBitmapToByteArray(Bitmap bitmap){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//把bitmap写入字节数组输出流
		byte[] b=bos.toByteArray();//把字节数组输出流转换成字节数组
		return b;
	}
	/*
	 * 把字节数组byte[]转换成bitmap
	 * */
	public static Bitmap convertByteArrayToBitmap(byte[] array){
		Bitmap bitmap=BitmapFactory.decodeByteArray(array, 0, array.length);
		return bitmap;
	}
	/*
	 * 将bitmap保存为本地文件,fileName是保存的文件名称，path是保存的文件路径
	 * */
	public static void saveBitmapToFile(Bitmap bitmap,String fileName,String path){
		File file=new File(path,fileName);
		try {
			OutputStream outputStream=new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG,100, outputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
