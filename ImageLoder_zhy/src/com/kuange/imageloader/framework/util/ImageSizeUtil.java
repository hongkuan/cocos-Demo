package com.kuange.imageloader.framework.util;

import java.lang.reflect.Field;

import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class ImageSizeUtil {
	
	private static final String TAG = "ImageSizeUtil";
	/**
	 * 定义图片的宽和高
	 * 
	 * @author FBQQF52
	 *
	 */
	public static class ImageSize {
		public int mImageWidth;
		public int mImageHeight;
	}

	/**
	 * 根据imageview获取适当的压缩宽和高
	 * 
	 * @param imageView
	 * @return
	 */
	public static ImageSize getImageViewSize(ImageView imageView) {
		ImageSize imageSize = new ImageSize();

		DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
		LayoutParams layoutParams = imageView.getLayoutParams();

		int width = imageView.getWidth();// 获取ImageView实际宽度
		if (width <= 0) {
			width = layoutParams.width; // 获取ImageView在布局中声明的宽度
		}
		if (width <= 0) {
			width = getImageViewFieldValue(imageView, "mMaxWidth");// 获取ImageView最大值 使用屏幕宽度
		}
		if (width <= 0){
			width = displayMetrics.widthPixels;//使用屏幕宽度
		}
		
		int height = imageView.getHeight();// 获取ImageView实际高度
		if (height <= 0) {
			height = layoutParams.height; // 获取ImageView在布局中声明的高度
		}
		if (height <= 0) {
			height = getImageViewFieldValue(imageView, "mMaxHeight");// 获取ImageView最大值 使用屏幕高度
		}
		if (height <= 0){
			height = displayMetrics.heightPixels;//使用屏幕高度
		}
		
		imageSize.mImageWidth = width;
		imageSize.mImageHeight = height;

		return imageSize;
	}

	/**
	 * 通过反射拿到ImageView是某个属性值
	 * 
	 * @param objiect
	 * @param fieldName
	 * @return
	 */
	private static int getImageViewFieldValue(Object object, String fieldName) {
		try {
			Field field = ImageView.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			int fieldValue = field.getInt(object);
			if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
				return fieldValue;
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 根据需要的以及实际的高度和宽度计算SampleSize
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int caculateInSampleSize(Options options, int reqWidth, int reqHeight){
		int width = options.outWidth;
		int height = options.outHeight;
		
		if(width > reqWidth || height > reqHeight){
			int widthRodio = Math.round(width * 1.0f / reqWidth);
			int heightRodio = Math.round(height * 1.0f / reqHeight);
			return Math.max(widthRodio, heightRodio);
		}
		return 0;
	}

}
