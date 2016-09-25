package com.kuange.musiccilent.adapter;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kuange.musiccilent.R;
import com.kuange.musiccilent.entity.Music;
import com.kuange.musiccilent.util.BitmapUtils;
import com.kuange.musiccilent.util.GloabalConsts;
import com.kuange.musiccilent.util.HttpUtils;

public class MusicAdapter extends BaseAdapter{

	private Context context;
	private List<Music> musics;
	private LayoutInflater inflater;
	//声明任务队列
	private List<ImageLoadTask> tasks=new ArrayList<ImageLoadTask>();
	//声明加载图片所需的工作线程
	private Thread workThread;
	private boolean isLoop=true;
	private ListView listView;
	//添加用于缓存图片的HashMap
	private Map<String, SoftReference<Bitmap>>  maps= new HashMap<String, SoftReference<Bitmap>>();
	
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case HANDLER_LOAD_IMAGE_SUCCESS:
				ImageLoadTask task=(ImageLoadTask) msg.obj;
				//通过tag获取相应的ImageView
				ImageView iv=(ImageView)listView.findViewWithTag(task.path);
				if(iv!=null&&task.bitmap!=null){
					iv.setImageBitmap(task.bitmap);
				}
				break;
			}
		};
	};
	public static final int HANDLER_LOAD_IMAGE_SUCCESS=0;
	
	public MusicAdapter(Context context,List<Music> musics ,ListView listView){
		this.context=context;
		this.musics=musics;
		this.listView=listView;
		this.inflater=LayoutInflater.from(context);
		//初始化工作线程
		workThread=new Thread(){
			@Override
			public void run() {
				//不断的轮循任务队列
				//如果队列中有任务对象 则获取执行
				//如果没有任务对象则等待
				while(isLoop){
					if(!tasks.isEmpty()){
						//删除并获取下标为0的任务对象
						ImageLoadTask task=tasks.remove(0);
						Bitmap bitmap=loadbBitmap(task);
						task.bitmap=bitmap;
						//在主线程中给相应的imageView设置Bitmap
						Message msg=new Message();
						msg.what=HANDLER_LOAD_IMAGE_SUCCESS;
						msg.obj=task;
						handler.sendMessage(msg);
					}else{
						try {
							synchronized (workThread) {
								workThread.wait();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		workThread.start();
	}
	
	public Bitmap loadbBitmap(ImageLoadTask task){
		//去内存缓存中查找是否已经缓存该图片
		SoftReference<Bitmap> ref=maps.get(task.path);
		if(ref!=null && ref.get()!=null){
			Log.i("info", "从内存缓存中读取的Bitmap...");
			return ref.get();
		}
		
		//去文件缓存中查找
		String path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),task.path).getAbsolutePath();
		//String path=new File(context.getCacheDir(), task.path).getAbsolutePath();
		Bitmap bit=BitmapUtils.loadBitmap(path);
		if(bit!=null){
			Log.i("info", "从文件缓存中读取...");
			return bit;
		}
		try {
			//发送http请求下载图片
			String httpPath=GloabalConsts.BASEURL+task.path;
			HttpEntity entity=HttpUtils.getEntity(HttpUtils.METHOD_GTE, httpPath, null);
			byte[] bytes=EntityUtils.toByteArray(entity);
			//接收图片时对图片进行压缩 压缩成50*50的图片
			Bitmap bitmap=BitmapUtils.loadbitmap(bytes, 50, 50);
			
			// 将图片存入外部存储设备里 
			BitmapUtils.save(bitmap, new File(path));
			
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
	}
	
	@Override
	public int getCount() {
		
		return musics.size();
	}
	

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return musics.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_lv_music, null);
			holder.ivAlbum=(ImageView) convertView.findViewById(R.id.ivAlbum);
			holder.tvName=(TextView) convertView.findViewById(R.id.tvName);
			holder.tvSinger=(TextView) convertView.findViewById(R.id.tvSinger);
			holder.tvAuthor=(TextView) convertView.findViewById(R.id.tvAuthor);
			holder.tvDuration=(TextView) convertView.findViewById(R.id.tvDuration);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		Music music=musics.get(position);
		
		holder.tvName.setText(music.getName());
		holder.tvSinger.setText(music.getSinger());
		holder.tvAuthor.setText(music.getAuthor());
		holder.tvDuration.setText(music.getDurationtime());
		
		//给ImageView设置唯一的Tag
		holder.ivAlbum.setTag(music.getAlbumpic());
		///向任务队列中添加一个任务对象
		ImageLoadTask task=new ImageLoadTask();
		task.path=music.getAlbumpic();
		tasks.add(task);
		//唤醒工作线程 起来干活轮循任务队列
		synchronized(workThread){
			workThread.notify();
		}
		return convertView;
	}
	
	class ViewHolder{
		private ImageView ivAlbum;
		private TextView tvName;
		private TextView tvSinger;
		private TextView tvAuthor;
		private TextView tvDuration;
	}
	
	//封装一个图片加载任务
	class ImageLoadTask{
		String path;
		Bitmap bitmap;
	}

	public void stopWorkThread(){
		isLoop=false;
		synchronized(workThread){
			workThread.notify();
		}
	}
}
