package com.kuange.musiconline.dao;

import java.sql.Connection;
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

}
