package com.kuange.musiccilent.entity;
import java.io.Serializable;

public class Music implements Serializable{
	private int id;
	private String name;
	private String singer;
	private String author;
	private String composer;
	private String album;
	private String albumpic;
	private String musicpath;
	private String durationtime;
	private String downcount;
	private String favcount;

	public Music() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAlbumpic() {
		return albumpic;
	}

	public void setAlbumpic(String albumpic) {
		this.albumpic = albumpic;
	}

	public String getMusicpath() {
		return musicpath;
	}

	public void setMusicpath(String musicpath) {
		this.musicpath = musicpath;
	}

	public String getDurationtime() {
		return durationtime;
	}

	public void setDurationtime(String durationtime) {
		this.durationtime = durationtime;
	}

	public String getDowncount() {
		return downcount;
	}

	public void setDowncount(String downcount) {
		this.downcount = downcount;
	}

	public String getFavcount() {
		return favcount;
	}

	public void setFavcount(String favcount) {
		this.favcount = favcount;
	}

}
