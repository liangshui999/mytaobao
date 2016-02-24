package com.example.mytaobao.externaltorage;

import java.io.File;

import com.example.mytaobao.util.MyApplication;

import android.os.Environment;

public class ExternalStorageHelper {
	/*
	 * ��ȡһ�������ļ���
	 * ��Ҫ�������ļ���������Ȩ��read_externalstorage ��write_externalstorage
	 * */
	public File getImageFileDir(String imageDirNameName){
		File file=null;
		if(this.isExternalStorageWritable()){			
			//file=new File(Environment.getExternalStorageDirectory(),imageDirNameName);//ֱ����sd���ĸ�Ŀ¼�£��½���һ�����ֽ�imageDirNameName���ļ���			
			//����������ļ��еĽ���������sd����Ŀ¼�µ�pictrueĿ¼�����½�һ�����ֽ�imageDirNameName���ļ���
			file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),imageDirNameName);
			
			//˽�е��ļ��еĽ�����storage/Android/����/files/Pitrues/imageDirNameName����������ļ������صĺ���,ж��Ӧ�õ�ʱ��ᱻж�ص�
			//file=new File(MyApplication.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),imageDirNameName);
			if(!file.mkdirs()){
				return null;
			}else{
				return file;
			}
		}else{
			return null;
		}
		
		
	}
	// Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	




}
