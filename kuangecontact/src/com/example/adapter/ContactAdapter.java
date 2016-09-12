package com.example.adapter;

import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.Contact;
import com.example.kuangecontact.R;

public class ContactAdapter extends BaseAdapter{

	private Context context;
	private List<Contact> cs;
	private LayoutInflater inflater;

	public ContactAdapter(Context context,List<Contact> cs){
		this.context=context;
		this.cs=cs;
		cs.add(0,new Contact());
		Log.i("iiiii", "cs="+cs);
		this.inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_gv_contact, null);
			holder.imageView=(ImageView) convertView.findViewById(R.id.ivPhoto);
			holder.textView=(TextView) convertView.findViewById(R.id.tvName);
			convertView.setTag(holder);
		}
		holder=(ViewHolder) convertView.getTag();
		//判断是否第一个item
		if(position==0){
			holder.imageView.setImageResource(R.drawable.img02_07);
			holder.textView.setText("添加联系人");
		}else{
			
			Contact contact=cs.get(position);
			holder.textView.setText(contact.getName());
			Bitmap bitmap=findByIdBitmap(contact.getPhotoId());
			if(bitmap==null){
				holder.imageView.setImageResource(R.drawable.img01g_15);
			}else{
				holder.imageView.setImageBitmap(bitmap);
			}
		}
		return convertView;
	}


	class ViewHolder{
		ImageView imageView;
		TextView textView;
	}
	/**
	 * 通过photoId 从data表中加载一个Bitmap
	 * @param Id
	 * @return
	 */
	private Bitmap findByIdBitmap(int Id) {
		Bitmap bitmap=null;
		ContentResolver r=context.getContentResolver();
		Uri uri=Data.CONTENT_URI;
		String[] projection={Data.DATA15};
		Cursor c = r.query(uri, projection, Data._ID+"="+Id, null, null);
		if(c.moveToNext()){
			byte[] bytes=c.getBlob(0);
			bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		}
		c.close();
		return bitmap;
	}
}
