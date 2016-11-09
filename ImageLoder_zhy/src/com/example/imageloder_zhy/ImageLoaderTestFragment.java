package com.example.imageloder_zhy;

import com.kuange.imageloader.framework.ImageLoader;
import com.kuange.imageloader.framework.ImageLoader.Type;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageLoaderTestFragment extends Fragment {

	/**
	 * 定义一行显示图片3张
	 */
	private static final int IMAGE_GROUP_ROW_COUNT = 3;
	/**
	 * 是否在网络中加载图片
	 */
	private static final boolean IMAGE_LODER_IS_NET = true;
	private static final String TAG = "ImageLoaderTestFragment";
	private RecyclerView mRecyclerView;

	private ImageLoader mImageLoder = null;
	
	private String[] mImages = null;

	public static ImageLoaderTestFragment newInstence() {
		return new ImageLoaderTestFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView is call......");
		// 获取到ImageLoder实例  设置线程池线程数量和加载策略
		mImageLoder = ImageLoader.getInstance(4, Type.LIFO);
		
		// 设置数据源
		mImages = Images.imageThumbUrls;
		
		View view = inflater.inflate(R.layout.fragment_image_loader_group, container, false);

		mRecyclerView = (RecyclerView) view.findViewById(R.id.imager_loder_recycler_view);
		mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), IMAGE_GROUP_ROW_COUNT));

		mRecyclerView.setAdapter(new ImageLoaderAdapter(mImageLoder, mImages));
		return view;
	}

	class ImageLoaderHolder extends RecyclerView.ViewHolder {

		private ImageView mImageView;

		public ImageLoaderHolder(View itemView) {
			super(itemView);
			mImageView = (ImageView) itemView;
			mImageView.setImageResource(R.drawable.ic_launcher);
		}

		public void bindImageView(String path, ImageLoader imageLoder) {
			imageLoder.loadImage(path, mImageView, IMAGE_LODER_IS_NET);
		}
	}

	class ImageLoaderAdapter extends RecyclerView.Adapter<ImageLoaderHolder> {

		private ImageLoader mImageLoder;
		private String[] mImages;
		
		public ImageLoaderAdapter(ImageLoader imageLoder, String[] mImages) {
			this.mImageLoder = imageLoder;
			this.mImages = mImages;
		}
		
		@Override
		public int getItemCount() {
			return mImages.length;
		}

		@Override
		public void onBindViewHolder(ImageLoaderHolder holder, int postion) {
			holder.bindImageView(mImages[postion], mImageLoder);
		}

		@Override
		public ImageLoaderHolder onCreateViewHolder(ViewGroup root, int postion) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.fragment_image_loader_group_itme, root, false);
			return new ImageLoaderHolder(view);
		}

	}

}
