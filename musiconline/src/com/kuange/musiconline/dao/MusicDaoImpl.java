package com.kuange.musiconline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kuange.musiconline.entity.Music;
import com.kuange.musiconline.util.DBUtil;

public  class MusicDaoImpl implements MusicDao{

	@Override
	public List<Music> filAll() throws Exception {
		Connection conn=DBUtil.getConnection();
		Statement stat = conn.createStatement();
		String sql="select * from music";
		ResultSet res = stat.executeQuery(sql);
		List<Music> musics=new ArrayList<Music>();
		while(res.next()){
			Music m=new Music();
			m.setId(res.getInt("id"));
			m.setName(res.getString("name"));
			m.setSinger(res.getString("singer"));
			m.setAuthor(res.getString("author"));
			m.setComposer(res.getString("composer"));
			m.setAlbum(res.getString("album"));
			m.setAlbumpic(res.getString("albumpic"));
			m.setMusicpath(res.getString("musicpath"));
			m.setDurationtime(res.getString("durationtime"));
			m.setDowncount(res.getString("downcount"));
			m.setFavcount(res.getString("favcount"));
			musics.add(m);
		}
		DBUtil.close();
		return musics;
	}

	@Override
	public void updateMusicName(int id, Music music) throws Exception {
		Connection conn = DBUtil.getConnection();
		//参数使用? 作为占位符
		String sql = "update music "
				+ "set name = ?,"
				+ "singer = ?,"
				+ "author = ?,"
				+ "composer = ?,"
				+ "album = ?,"
				+ "albumpic = ?,"
				+ "musicpath = ?,"
				+ "durationtime = ?,"
				+ "downcount = ?,"
				+ "favcount = ? "
				+ "where id = ?";
		//预编译sql语句
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		//对应sql语句，给对应的sql语句传参数
		prepareStatement.setString(1, music.getName());
		prepareStatement.setString(2, music.getSinger());
		prepareStatement.setString(3, music.getAuthor());
		prepareStatement.setString(4, music.getComposer());
		prepareStatement.setString(5, music.getAlbum());
		prepareStatement.setString(6, music.getAlbumpic());
		prepareStatement.setString(7, music.getMusicpath());
		prepareStatement.setString(8, music.getDurationtime());
		prepareStatement.setString(9, music.getDowncount());
		prepareStatement.setString(10, music.getFavcount());
		prepareStatement.setInt(11, music.getId());
		
		prepareStatement.execute();
		DBUtil.close();
	}

	
	@Override
	public void deleteMusic(int id) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from music "
				+ "where id = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, id);
		
		prepareStatement.execute();
		DBUtil.close();
	}
	
	@Override
	public void addMusic(Music music) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into music (id,name,singer,author,composer,album,albumpic,musicpath,durationtime,downcount,favcount) values "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, music.getId());
		prepareStatement.setString(2, music.getName());
		prepareStatement.setString(3, music.getSinger());
		prepareStatement.setString(4, music.getAuthor());
		prepareStatement.setString(5, music.getComposer());
		prepareStatement.setString(6, music.getAlbum());
		prepareStatement.setString(7, music.getAlbumpic());
		prepareStatement.setString(8, music.getMusicpath());
		prepareStatement.setString(9, music.getDurationtime());
		prepareStatement.setString(10, music.getDowncount());
		prepareStatement.setString(11, music.getFavcount());
		prepareStatement.execute();
		DBUtil.close();
	}
	
	@Override
	public Music getMusic(int id) throws Exception {
		Music music = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from music where id = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, id);
		ResultSet resultSet = prepareStatement.executeQuery();
		if(resultSet.next()){
			music = new Music();
			music.setId(resultSet.getInt("id"));
			music.setName(resultSet.getString("name"));
			music.setSinger(resultSet.getString("singer"));
			music.setAuthor(resultSet.getString("author"));
			music.setComposer(resultSet.getString("composer"));
			music.setAlbum(resultSet.getString("album"));
			music.setAlbumpic(resultSet.getString("albumpic"));
			music.setMusicpath(resultSet.getString("musicpath"));
			music.setDurationtime(resultSet.getString("durationtime"));
			music.setDowncount(resultSet.getString("downcount"));
			music.setFavcount(resultSet.getString("favcount"));
		}
		DBUtil.close();
		return music;
	}
}
