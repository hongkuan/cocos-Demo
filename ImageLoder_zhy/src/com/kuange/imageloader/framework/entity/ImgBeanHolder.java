package com.kuange.imageloader.framework.entity;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImgBeanHolder {
	private Bitmap mBitmap;
	private ImageView mImageView;
	private String mPath;

	public Bitmap getBitmap() {
		return mBitmap;
	}

	public void setBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}

	public ImageView getImageView() {
		return mImageView;
	}

	public void setImageView(ImageView mImageView) {
		this.mImageView = mImageView;
	}

	public String getPath() {
		return mPath;
	}

	public void setPath(String mPath) {
		this.mPath = mPath;
	}
}
