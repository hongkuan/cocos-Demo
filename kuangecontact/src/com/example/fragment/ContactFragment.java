package com.example.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.adapter.ContactAdapter;
import com.example.biz.ContactBiz;
import com.example.entity.Contact;
import com.example.kuangecontact.R;

public class ContactFragment extends Fragment{
	private GridView gvContacts;
	private List<Contact> contacts;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_contacts, null);
		//初始化控件gridView
		gvContacts=(GridView)view.findViewById(R.id.gvContacts);
		//调用业务模块获得联系人集合
		ContactBiz biz=new ContactBiz(getActivity());
		contacts=biz.findAll();
		//创建Adapter
		ContactAdapter adapter=new ContactAdapter(getActivity(), contacts);
		gvContacts.setAdapter(adapter);
		//设置gridView的事件监听 单击后
		//弹出自定义视图的AlertDialog
		setListeners();
		return view;
	}

	private void setListeners() {
		gvContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//position 点击选中的当前项
				Builder builder=new Builder(getActivity());
				AlertDialog dialog = builder.create();
				dialog.show();
				Window window=dialog.getWindow();
				View v=View.inflate(getActivity(), R.layout.contact_detail, null);
				//获取视图中的控件 然后添加事件监听
				//v.findViewById()
				window.setContentView(v);
			}
		});
	}
}
