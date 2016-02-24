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
	 * �����ļ�·���ӱ��ؼ���ͼƬ��ת����bitmap����
	 * path��·��
	 * name���ļ�����
	 * */
	public static Bitmap getBitmapFromFile(String path,String name){
		return BitmapFactory.decodeFile(path+"/"+name);
	}
	/*
	 * ��bitmapת�����ֽ�����byte[]
	 * */
	public static byte[] convertBitmapToByteArray(Bitmap bitmap){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//��bitmapд���ֽ����������
		byte[] b=bos.toByteArray();//���ֽ����������ת�����ֽ�����
		return b;
	}
	/*
	 * ���ֽ�����byte[]ת����bitmap
	 * */
	public static Bitmap convertByteArrayToBitmap(byte[] array){
		Bitmap bitmap=BitmapFactory.decodeByteArray(array, 0, array.length);
		return bitmap;
	}
	/*
	 * ��bitmap����Ϊ�����ļ�,fileName�Ǳ�����ļ����ƣ�path�Ǳ�����ļ�·��
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
