package com.kuange.musiccilent.activity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.kuange.musiccilent.R;
import com.kuange.musiccilent.adapter.MusicAdapter;
import com.kuange.musiccilent.biz.MusicBiz;
import com.kuange.musiccilent.entity.Music;
import com.kuange.musiccilent.service.DownloadMusicService;
import com.kuange.musiccilent.util.GloabalConsts;

public class MainActivity extends Activity {

	private ListView lvMusics;
	private MusicAdapter adater;
	private List<Music> musics;

	private MediaPlayer mMediaPlayer = null;// 各个状态

	private static final String TAG = "kuange.MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setViews();
		setListeners();
		// 执行异步任务对象 下载数据
		MusicBiz biz = new MusicBiz(this);
		biz.execute(GloabalConsts.BASEURL + "loadMusics");
	}

	private void setListeners() {
		lvMusics.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Music music = musics.get(position);
				File file = new File(
						Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
						music.getMusicpath());
				if (!file.exists()) {
					Toast.makeText(MainActivity.this,
							music.getName() + "不存在，请长按下载!", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				String path = file.getAbsolutePath();
				try {

					if (mMediaPlayer == null) {
						mMediaPlayer = new MediaPlayer();
					} else if (mMediaPlayer.isPlaying()) {
						mMediaPlayer.stop();
						mMediaPlayer.reset();
					}
					mMediaPlayer.setDataSource(path);
					mMediaPlayer.prepare();
					mMediaPlayer.start();

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// 长按监听
		lvMusics.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// 弹出下载菜单
				Builder builder = new Builder(MainActivity.this);
				builder.setTitle("操作：").setItems(
						new String[] { "下载", "喜欢", "删除" },
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									// 获取下载音乐的音乐路径
									Music m = musics.get(position);
									// path == musics/xxxx.mp3
									String path = m.getMusicpath();
									// 执行下载操作
									Log.i(TAG, "start to dowmload music!");
									Intent intent = new Intent(
											MainActivity.this,
											DownloadMusicService.class);
									intent.putExtra("path", path);
									startService(intent);
									Log.i(TAG, "sdfs");
									break;
								}

							}
						});
				builder.create().show();
				return false;
			}
		});

	}

	private void setViews() {
		lvMusics = (ListView) findViewById(R.id.music_lv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 接收音乐参数 创建Apdater 更新ListView
	public void updateListView(List<Music> musics) {
		Log.d(TAG, "musics:" + musics);
		if (musics != null && musics.size() != 0) {
			this.musics = musics;
			adater = new MusicAdapter(this, musics, lvMusics);
			lvMusics.setAdapter(adater);

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (adater != null) {
			adater.stopWorkThread();// 给adapter里的工作线程停止防止内存泄露
		}
		super.onBackPressed();
	}

}
