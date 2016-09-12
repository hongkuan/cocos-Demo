package com.example.biz;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Contact;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class ContactBiz {
	private Context context;

	public ContactBiz(Context context){
		this.context=context;
	}
	
	public List<Contact> findAll(){
		List<Contact> cs=null;
		ContentResolver r=context.getContentResolver();
		Uri uri=Contacts.CONTENT_URI;
		String[] projection={
				Contacts._ID, //0
				Contacts.PHOTO_ID //1
				};
		Cursor c = r.query(uri, projection, null, null, null);
		cs=new ArrayList<Contact>();
		while(c.moveToNext()){
			Contact contact=new Contact();
			contact.setId(c.getInt(0));
			contact.setPhotoId(c.getInt(1));
			Uri dataUri=Data.CONTENT_URI;
			String[] dataProjection={
					Data._ID,
					Data.MIMETYPE,
					Data.DATA1,
					Data.DATA15
			};
			Cursor datac = r.query(dataUri, dataProjection, 
					Data.RAW_CONTACT_ID+"="+contact.getId(), null, null);
			while(datac.moveToNext()){
				String mimetype=datac.getString(1);
				if(mimetype.equals(Email.CONTENT_ITEM_TYPE)){
					contact.setEmail(datac.getString(2));
					
				}else if(mimetype.equals(Phone.CONTENT_ITEM_TYPE)){
					contact.setPhone(datac.getString(2));
				}else if(mimetype.equals("vnd.android.cursor.item/sip_address")){
					contact.setAddress(datac.getString(2));
				}else if(mimetype.equals("vnd.android.cursor.item/name")){
					contact.setName(datac.getString(2));
				}
			}
			datac.close();
			cs.add(contact);
		}
		c.close();
		return cs;
	}
}
