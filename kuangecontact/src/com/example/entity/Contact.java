package com.example.entity;

public class Contact {
	private int id;
	private int photoId;
	private String name;
	private String email;
	private String phone;
	private String 	address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "contact id=" + id + "\n photoId=" + photoId + "\n name=" + name
				+ "\n email=" + email + "\n phone=" + phone+ "\n address=" + address ;
	}
	
	
}
