package com.kuange.imageloader.framework;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.kuange.imageloader.framework.entity.ImgBeanHolder;
import com.kuange.imageloader.framework.util.ImageDownloadUtil;
import com.kuange.imageloader.framework.util.ImageSizeUtil;
import com.kuange.imageloader.framework.util.ImageSizeUtil.ImageSize;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

/**
 * 1、单例，包含一个LruCache用于管理我们的图片；
 * 2、任务队列，每来一次加载图片的请求，会封装成Task存入到TaskQueue;
 * 3、包含一个后台线程，这个线程在第一次初始化实例的时候启动，然后会一直在后台运行；
 * 当每来一次加载图片请求的时候，同时发一个消息到后台线程，后台线程去使用线程池去TaskQueue
 * 去取一个任务执行；
 * 4、调度策略；3中说了，后台线程去TaskQueue去取一个任务，这个任务不是随便取的，有
 * 策略可以选择，一个是FIFO，一个是LIFO。
 * 
 * @author FBQQF52
 *
 */
public class ImageLoader {
	private static final String TAG = "ImageLoder";

	private static ImageLoader mInstance = null;

	/**
	 * 图片缓存的核心对象
	 */
	private LruCache<String, Bitmap> mLruCache;
	/**
	 * 线程池
	 */
	private ExecutorService mThreadPool;
	private static final int DEAFULT_THREAD_COUNT = 1;
	/**
	 * 任务的调度方式
	 */
	private Type mType = Type.LIFO;
	/**
	 * 任务队列
	 */
	private LinkedList<Runnable> mTaskQueue;
	/**
	 * 后台轮询线程
	 */
	private Thread mPoolThread;
	private Handler mPoolThreadHandler;
	/**
	 * UI线程中的Handler
	 */
	private Handler mUIHandler;

	private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
	private Semaphore mSemaphoreThreadPool;

	private boolean isDiskCacheEnable = true;

	public enum Type {
		FIFO, LIFO;
	}

	private ImageLoader(int threadCount, Type type) {
		init(threadCount, type);
	}

	/**
	 * 初始化
	 * 
	 * @param threadCount
	 * @param type
	 */
	private void init(int threadCount, Type type) {
		// 初始化后台线程
		initBackThread();

		// 设置缓存内存大小 初始化缓存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheMemory = maxMemory / 8;
		mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};

		// 创建线程池
		mThreadPool = Executors.newFixedThreadPool(threadCount);
		mTaskQueue = new LinkedList<Runnable>();
		mType = type;
		mSemaphoreThreadPool = new Semaphore(threadCount);
	}

	/**
	 * 初始化后台轮询线程
	 */
	private void initBackThread() {
		// 后台轮询线程
		mPoolThread = new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				mPoolThreadHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						// 线程池去取出一个任务进行执行
						mThreadPool.execute(getTask());
						try {
							mSemaphoreThreadPool.acquire();
						} catch (InterruptedException e) {
							Log.e(TAG, e.getMessage());
							e.printStackTrace();
						}
					}
				};

				// 释放一个信号量
				mSemaphorePoolThreadHandler.release();
				Looper.loop();
			}
		};
		mPoolThread.start();
	}

	public static ImageLoader getInstance() {
		if (mInstance == null) {
			synchronized (ImageLoader.class) {
				if (mInstance == null) {
					mInstance = new ImageLoader(DEAFULT_THREAD_COUNT, Type.LIFO);
				}
			}
		}
		return mInstance;
	}

	public static ImageLoader getInstance(int threadCount, Type type) {
		if (mInstance == null) {
			synchronized (ImageLoader.class) {
				if (mInstance == null) {
					mInstance = new ImageLoader(threadCount, type);
				}
			}
		}
		return mInstance;
	}

	/**
	 * 根据path为ImageView设置图片
	 * 
	 * @param path
	 * @param imageView
	 * @param isFromNet
	 */
	public void loadImage(final String path, final ImageView imageView, final boolean isFromNet) {
		imageView.setTag(path);
		if (mUIHandler == null) {
			mUIHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					// 获取得到的图片 为imageView设置图片
					ImgBeanHolder imgBeanHolder = (ImgBeanHolder) msg.obj;
					Bitmap bitmap = imgBeanHolder.getBitmap();
					ImageView imageView = imgBeanHolder.getImageView();
					String path = imgBeanHolder.getPath();
					// 将path于getTag存储路径进行比较
					if (imageView.getTag().toString().equals(path)) {
						imageView.setImageBitmap(bitmap);
					}
				}
			};
		}

		// 根据path获取缓存里的bitmap
		Bitmap bm = getBitmapFromLruCache(path);
		if (bm != null) {
			refreachBitmap(path, imageView, bm);
		} else {
			addTask(buildTask(path, imageView, isFromNet));
		}
	}

	private Runnable buildTask(final String path, final ImageView imageView, final boolean isFromNet) {
		return new Runnable() {

			@Override
			public void run() {
				Bitmap bm = null;
				if (isFromNet) {
					File file = getDiskCacheDir(imageView.getContext(), md5(path));
					if (file.exists()) {// 如果存在缓存文件
						Log.i(TAG, "download image :" + path + "to disk cache.");
						bm = loadImageFromLocal(file.getAbsolutePath(), imageView);
					} else {
						if (isDiskCacheEnable) {// 检测是否开启硬盘缓存
							boolean downloadState = ImageDownloadUtil.downloadImgByUrl(path, file);
							if (downloadState) { // 如果下载成功
								Log.i(TAG, "download image :" + path + "to disk cache, path is " + file.getAbsolutePath());
								bm = loadImageFromLocal(file.getAbsolutePath(), imageView);
							}
						} else { // 从网络中直接加载
							Log.e(TAG, "load image :" + path + "to memory");
							bm = ImageDownloadUtil.downloadImgByUrl(path, imageView);
						}
					}
				} else {
					bm = loadImageFromLocal(path, imageView);
				}

				// 把图片加入到缓存
				addBitmapToLruCache(path, bm);
				refreachBitmap(path, imageView, bm);
				mSemaphoreThreadPool.release();
			}

		};
	}

	private Bitmap loadImageFromLocal(final String path, final ImageView imageView) {
		Bitmap bm = null;
		// 加载图片 图片压缩
		// 获取图片显示大小
		ImageSize imageSize = ImageSizeUtil.getImageViewSize(imageView);
		// 压缩图片
		bm = decodeSampledBitmapFromPath(path, imageSize.mImageWidth, imageSize.mImageHeight);

		return bm;
	}

	private Runnable getTask() {
		if (mType == Type.FIFO) {
			return mTaskQueue.removeFirst();
		} else if (mType == Type.LIFO) {
			return mTaskQueue.removeLast();
		}
		return null;
	}

	private String md5(String path) {
		byte[] digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			digest = md.digest(path.getBytes());
			return byte2hex02(digest);
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	private String byte2hex02(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		String tmp = null;
		for (byte b : bytes) {
			// 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
			tmp = Integer.toHexString(0xFF & b);
			// 每个字节8为，转为16进制标志，2个16进制位
			if (tmp.length() == 1) {
				tmp = "0" + tmp;
			}
			sb.append(tmp);
		}
		return sb.toString();
	}

	/**
	 * 根据bm通知UI更新
	 * 
	 * @param path
	 * @param imageView
	 * @param bm
	 */
	private void refreachBitmap(String path, ImageView imageView, Bitmap bm) {
		Message message = Message.obtain();
		ImgBeanHolder imgBeanHolder = new ImgBeanHolder();
		imgBeanHolder.setBitmap(bm);
		imgBeanHolder.setPath(path);
		imgBeanHolder.setImageView(imageView);
		message.obj = imgBeanHolder;
		mUIHandler.sendMessage(message);
	}

	/**
	 * 将bitmap 加载到LruCache
	 * 
	 * @param path
	 * @param bm
	 */
	private void addBitmapToLruCache(String path, Bitmap bm) {
		if (getBitmapFromLruCache(path) == null) {
			if (bm != null) {
				mLruCache.put(path, bm);
			}
		}
	}

	/**
	 * 根据图片显示的高度和宽度压缩图片
	 * 
	 * @param path
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private Bitmap decodeSampledBitmapFromPath(String path, int reqWidth, int reqHeight) {
		// 获取图片的宽度和高度，并不把图片加载到内存中
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		options.inSampleSize = ImageSizeUtil.caculateInSampleSize(options, reqWidth, reqHeight);

		// 使用获取的inSampleSize 再次解析图片
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);

		return bitmap;
	}

	/**
	 * 
	 * @param buildTadk
	 */
	private synchronized void addTask(Runnable task) {
		mTaskQueue.add(task);

		try {
			if (mPoolThreadHandler == null)
				mSemaphorePoolThreadHandler.acquire();
		} catch (InterruptedException e) {
		}
		mPoolThreadHandler.sendEmptyMessage(0x110);
	}

	/**
	 * 获取图片缓存地址
	 * 
	 * @param context
	 * @param uniqueName
	 * @return
	 */
	private File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath = "";
		Log.i(TAG, "getDiskCacheDir...context:" + context + " ,uniqueName:" + uniqueName);
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			if(context == null){
				Log.i(TAG, "context is null");
			}else if(context.getExternalCacheDir() == null){
				Log.i(TAG, "context.getExternalCacheDir() is null!");
			}
			Log.i(TAG, "path : " + context.getExternalCacheDir().getPath());
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			Log.i(TAG, "path : " + context.getCacheDir().getPath());
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}

	/**
	 * 根据path在缓存中获取bitmap
	 * 
	 * @param path
	 * @return
	 */
	private Bitmap getBitmapFromLruCache(String path) {
		return mLruCache.get(path);
	}

}
