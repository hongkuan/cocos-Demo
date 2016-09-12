package com.example.yuolu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

import com.example.fragment.ContactFragment;
import com.example.kuangecontact.R;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private RadioButton r1;
	private RadioButton r2;
	private RadioButton r3;
	private RadioButton r4;
	private ArrayList<Fragment> fs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("2222", "22222");
		setView();
		FragmentManager fm=getSupportFragmentManager();
		//构建Fragment的集合
		fs=new ArrayList<Fragment>();
		fs.add(new ContactFragment());
		fs.add(new ContactFragment());
		fs.add(new ContactFragment());
		fs.add(new ContactFragment());
		//设置适配器
		Log.i("1111", "viewPager="+viewPager);
		MyPagerAdapter dapter=new MyPagerAdapter(fm, fs);
		viewPager.setAdapter(dapter);
		//设置和菜单栏联动
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					r1.setChecked(true);
					break;
				case 2:
					r2.setChecked(true);
					break;
				case 3:
					r3.setChecked(true);
					break;
				case 4:
					r4.setChecked(true);
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void setView() {
		Log.i("1111", "11111");
		viewPager=(ViewPager)findViewById(R.id.viewPager);
		Log.i("1111", "viewPager="+viewPager);
		r1=(RadioButton)findViewById(R.id.radio1);
		r2=(RadioButton)findViewById(R.id.radio2);
		r3=(RadioButton)findViewById(R.id.radio3);
		r4=(RadioButton)findViewById(R.id.radio4);
		Log.i("1111", "11111");
	}
//设置和viewpager联动
	public void doClick(View view){
		switch (view.getId()) {
		case R.id.radio1:
			viewPager.setCurrentItem(0);
			break;
		case R.id.radio2:
			viewPager.setCurrentItem(1);
			break;
		case R.id.radio3:
			viewPager.setCurrentItem(2);
			break;
		case R.id.radio4:
			viewPager.setCurrentItem(3);
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class MyPagerAdapter extends FragmentPagerAdapter{

		private List<Fragment> fs;

		public MyPagerAdapter(FragmentManager fm,List<Fragment> fs) {
			super(fm);
			this.fs=fs;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fs.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fs.size();
		}
		
	}

}
