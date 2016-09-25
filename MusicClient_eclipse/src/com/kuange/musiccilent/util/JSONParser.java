package com.kuange.musiccilent.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kuange.musiccilent.entity.Music;

public class JSONParser {
	/**
	 * @param ary
	 * @return
	 * @throws Exception
	 * [{"album":"君生今世",
	 * "albumpic":"images/junshengjinshi.jpg",
	 * "author":"小虫",
	 * "composer":"小虫",
	 * "downcount":"1896",
	 * "durationtime":"4:32",
	 * "favcount":"658",
	 * "id":1,
	 * "musicpath":"musics/yelaixiang.mp3",
	 * "name":"夜来香",
	 * "singer":"邓丽君"}]
	 */
	public static List<Music> parse(JSONArray ary)throws Exception{
		List<Music> musics=new ArrayList<Music>();
		for(int i=0;i<ary.length();i++){
			JSONObject res=ary.getJSONObject(i);
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
		return musics;
	}
}
