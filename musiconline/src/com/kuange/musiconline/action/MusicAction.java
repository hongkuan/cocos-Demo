package com.kuange.musiconline.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kuange.musiconline.dao.MusicDao;
import com.kuange.musiconline.entity.Music;
import com.kuange.musiconline.util.DaoFactory;

public class MusicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/thml;charset=utf-8");
		PrintWriter out = response.getWriter();
		//MusicDao dao = new MusicDaoImpl();
		MusicDao dao = (MusicDao) DaoFactory.getInstance("musicDao");
		
		try {
			List<Music> musics = dao.filAll();
			//
			JSONArray ary = JSONArray.fromObject(musics);
			JSONObject obj = new JSONObject();
			obj.put("result", "ok");
			obj.put("data", ary);
			out.println(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// obj.put(key, value)
			// out.println("");
		} finally {
			out.close();
		}

	}
	
	public static void main(String[] args) {
		MusicDao dao = (MusicDao) DaoFactory.getInstance("musicDao");
		List<Music> musics = null;
		try {
			musics = dao.filAll();
			//
			JSONArray ary = JSONArray.fromObject(musics);
			System.out.println(ary.toString());
			
			
			/*if(musics != null){
				Music music = musics.get(0);
				dao.updateMusicName(id, music);
			}*/
			Music music = dao.getMusic(60);
			if(music != null){
				System.out.println(music);
				System.out.println(music.getName());
				//music.setName("青花瓷");
				//dao.updateMusicName(60, music);
				
				dao.deleteMusic(60);
				System.out.println("=================");
				dao.addMusic(music);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
