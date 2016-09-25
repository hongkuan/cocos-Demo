package com.kuange.musiccilent.util;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory.Options;
import android.net.rtp.RtpStream;

public class BitmapUtils {
	
	/**
	 * 通过一个文件路径加载一张bitmap对象
	 * @param path
	 * @return
	 */
	public static Bitmap loadBitmap(String path){
		File file=new File(path);
		if(!file.exists()){
			return null;
		}
		//解析bitmap
		return BitmapFactory.decodeFile(path);
	}
	/**
	 * 保存bitmap 以jpeg格式保存
	 * @param bitmap
	 * @param targeFile
	 * @throws Exception
	 */
	public static void save(Bitmap bitmap,File targeFile)throws Exception{
		if(!targeFile.getParentFile().exists()){
			targeFile.getParentFile().mkdirs();
		}
		if(!targeFile.exists()){
			targeFile.createNewFile();
		}
		//把bitmap一jpeg格式存入硬盘
		bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(targeFile));
	}
	/**
	 * 把字节数组转换成Bitmap  要求目标图片的宽高符合条件
	 * @param bytesm
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap loadbitmap(byte[] bytes,int width,int height){
		Options opt=new Options();
		//仅仅加载图片数据的边界属性
		opt.inJustDecodeBounds=true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opt);
		//图片原始的宽度和高度
		int w=opt.outWidth/width;
		int h=opt.outHeight/height;
		int scale=w>h?w:h;
		//不仅仅加载边界属性
		opt.inJustDecodeBounds=false;
		//设置伸缩比例
		opt.inSampleSize=scale;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opt);
	}

}
