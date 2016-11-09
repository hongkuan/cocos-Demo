package com.kuange.imageloader.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.widget.ImageView;

import com.kuange.imageloader.framework.util.ImageSizeUtil.ImageSize;

public class ImageDownloadUtil {
	private static final String TAG = "ImageDownloadUtil";

	/**
	 * 根据指定url下载图片 直接返回一个Bitmap
	 * 
	 * @param urlStr
	 * @param imageView
	 * @return
	 */
	public static Bitmap downloadImgByUrl(String urlStr, ImageView imageView) {
		InputStream inputStream = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			inputStream = conn.getInputStream();
			inputStream.mark(inputStream.available());

			Options options = new Options();
			options.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);

			// 获取ImageView想要显示的宽高
			ImageSize imageSize = ImageSizeUtil.getImageViewSize(imageView);
			options.inSampleSize = ImageSizeUtil.caculateInSampleSize(options, imageSize.mImageWidth, imageSize.mImageHeight);

			options.inJustDecodeBounds = false;
			inputStream.read();
			bitmap = BitmapFactory.decodeStream(inputStream, null, options);

			conn.disconnect();
			return bitmap;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e2) {
				Log.e(TAG, e2.getMessage());
			}
		}
		return null;
	}

	/**
	 * 根据url下载图片 保存到指定路径
	 * @param urlStr
	 * @param file
	 * @return 下载成功返回true 失败返回false
	 */
	public static boolean downloadImgByUrl(String urlStr, File file) {
		FileOutputStream fos = null;
		InputStream is = null;
		boolean isFinish = false;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			is = conn.getInputStream();
			fos = new FileOutputStream(file);
			byte[] buf = new byte[512];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
			isFinish = true;
			conn.disconnect();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				Log.e(TAG, e.getMessage());
			}

			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				Log.e(TAG, e.getMessage());
			}
		}

		return isFinish;
	}

}
