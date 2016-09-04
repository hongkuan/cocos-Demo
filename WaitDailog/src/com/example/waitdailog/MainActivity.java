package com.example.waitdailog;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	Builder mBuilder = null;
	Dialog mDialog = null;

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1000:
				setProgressBarVisibility(false);
				setProgressBarIndeterminateVisibility(false);
				break;

			case 1001:
				if (mDialog != null) {
					mDialog.dismiss();
				}
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		requestWindowFeature(Window.FEATURE_PROGRESS);

		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/*
				 * setProgressBarIndeterminateVisibility(true);
				 * 
				 * setProgressBarVisibility(true); setProgress(4500);
				 * 
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() { try { Thread.sleep(1000); }
				 * catch (Exception e) {} Message msg = new Message(); msg.what
				 * = 1000; mHandler.sendMessage(msg); } }).start();
				 */

				// int id = android.R.attr.progressBarStyleLarge;
				// int id =
				// android.R.drawable.progress_indeterminate_horizontal;

				View progressBar = LayoutInflater.from(MainActivity.this)
						.inflate(R.layout.dailog_view, null);
				// mProgressBar.setIndeterminateDrawable(getResources().getDrawable(id));

				/*
				 * mBuilder = new
				 * AlertDialog.Builder(MainActivity.this,R.style.xg_custom_dialog
				 * ); mBuilder.setView(mProgressBar); mDialog =
				 * mBuilder.create();
				 */
				mDialog = new Dialog(MainActivity.this,
						R.style.xg_custom_dialog);
				RelativeLayout relativeLayout = new RelativeLayout(
						MainActivity.this);
				DisplayMetrics metric = getMetrics(MainActivity.this);

				int width = metric.widthPixels;
				int height = metric.heightPixels;

				int w = progressBar.getWidth();
				int h = progressBar.getHeight();

				int pWidth = (width - progressBar.getWidth()) / 2;
				int pHeight = (height - progressBar.getHeight()) / 2;

				MarginLayoutParams pMargin = new MarginLayoutParams(width,
						height);
				// pMargin.setMargins(10, 10, -1, -1);
				RelativeLayout.LayoutParams pLayoutParams = new RelativeLayout.LayoutParams(
						pMargin);

				relativeLayout.addView(progressBar, pLayoutParams);
				MarginLayoutParams margin = new MarginLayoutParams(
						metric.widthPixels, metric.heightPixels);
				RelativeLayout.LayoutParams dialogLayoutParams = new RelativeLayout.LayoutParams(
						margin);
				mDialog.addContentView(relativeLayout, dialogLayoutParams);
				mDialog.show();

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
						}
						Message msg = new Message();
						msg.what = 1001;
						mHandler.sendMessage(msg);
					}
				}).start();

			}
		});

	}

	/**
	 * @param context
	 * @return
	 */
	private static DisplayMetrics getMetrics(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metric);
		return metric;
	}

}
