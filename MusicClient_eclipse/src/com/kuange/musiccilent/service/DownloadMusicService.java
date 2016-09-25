package com.kuange.musiccilent.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.kuange.musiccilent.R;
import com.kuange.musiccilent.util.GloabalConsts;
import com.kuange.musiccilent.util.HttpUtils;

public class DownloadMusicService extends IntentService {

	private static final String TAG = "kuange.DownloadMusicService";

	public DownloadMusicService() {
		super("DownloadMusic");
		Log.i(TAG, "DownloadMusicService");
	}

	// 在工作线程中下载执行 可以执行发送http请求
	@Override
	protected void onHandleIntent(Intent intent) {
		String path = intent.getStringExtra("path");
		String httppath = GloabalConsts.BASEURL + path;
		Log.i(TAG, "http=" + httppath);
		// 发送http请求
		try {
			HttpEntity entity = HttpUtils.getEntity(HttpUtils.METHOD_GTE,httppath, null);
			// 获取总数据长度
			long total = entity.getContentLength();
			Log.i(TAG, "total=" + total);
			// 获取下载所需要的输入流
			InputStream is = entity.getContent();
			byte[] buffer = new byte[1024 * 1024];
			int length = 0;
			long current = 0;
			// 声明FileOutputStream
			Log.i(TAG, "FileOutputStream...");
			File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			} else {
				// 文件已经存在
				Log.d(TAG, "file is exist! path:"+path);
				return;
			}
			Log.i(TAG, "FileOutputStream");
			FileOutputStream fos = new FileOutputStream(file);
			// 开始下载发送通知
			sendNotification("音乐开始下载", "音乐下载", "音乐正在下载..");
			while ((length = is.read(buffer)) != -1) {
				// 把buffer中的数据输出到文件输出流中
				fos.write(buffer, 0, length);
				current += length;
				// 每下载一部分数据就需要更新通知内容
				String text = "正在下载音乐, 下载进度"+ Math.floor((current * 1.0 / total) * 100) + "%";
				sendNotification(text, "音乐下载", "音乐正在下载..");
			}
			fos.flush();
			fos.close();
			Log.i("TAG", "music is download finish!");
			// 保存成功 下载完成 发送通知
			cancelNotification(10);
			sendNotification("音乐下载完成", "音乐下载", "音乐下载完成");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	private void cancelNotification(int id) {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(id);
	}

	private void sendNotification(String text, String title, String ticker) {
		Builder builder = new Builder(this);
		Log.i(TAG, "sendNotification...Title: "+title+" text: "+text);
		builder.setContentInfo("")
				.setContentText(text)
				.setContentTitle(title)
				.setSmallIcon(R.drawable.ic_launcher)
				.setSubText("")
				.setLargeIcon(
						BitmapFactory.decodeResource(getResources(),
								android.R.drawable.stat_sys_download))
				.setTicker(ticker).setWhen(System.currentTimeMillis());
		// 创建Notification对象
		Notification n = builder.build();
		Log.i(TAG, "Notification: "+n);
		// 2. 获取用于发送该通知的系统服务
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// 3.调用notify方法发送系统通知
		manager.notify(10, n);
	}

}
