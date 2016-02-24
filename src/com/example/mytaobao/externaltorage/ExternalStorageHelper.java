package com.example.mytaobao.externaltorage;

import java.io.File;

import com.example.mytaobao.util.MyApplication;

import android.os.Environment;

public class ExternalStorageHelper {
	/*
	 * 获取一个公共文件夹
	 * 需要在配置文件里面声明权限read_externalstorage 和write_externalstorage
	 * */
	public File getImageFileDir(String imageDirNameName){
		File file=null;
		if(this.isExternalStorageWritable()){			
			//file=new File(Environment.getExternalStorageDirectory(),imageDirNameName);//直接在sd卡的根目录下，新建了一个名字叫imageDirNameName的文件夹			
			//公开分享的文件夹的建法，会在sd卡根目录下的pictrue目录下面新建一个名字叫imageDirNameName的文件夹
			file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),imageDirNameName);
			
			//私有的文件夹的建法，storage/Android/包名/files/Pitrues/imageDirNameName，这个建的文件夹隐藏的很深,卸载应用的时候会被卸载掉
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
