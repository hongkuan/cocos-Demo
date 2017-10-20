package com.kuange.musiconline.dao;

import java.util.List;

import com.kuange.musiconline.entity.Music;

public interface MusicDao {
	/**
	 * 查询数据库所有音乐文件
	 * @return
	 * @throws Exception
	 */
	public List<Music> filAll()throws Exception;
	
	/**
	 * 更新music名字
	 * @param id 需要更新的歌曲id
	 * @param Music 需要更新的歌曲
	 * @throws Exception
	 */
	public void updateMusicName(int id, Music music)throws Exception;
	
	/**
	 * 删除当前id的music
	 * @param id 需要删除的id
	 * @throws Exception
	 */
	public void deleteMusic(int id)throws Exception;
	
	/**
	 * 添加一条music
	 * @param music
	 * @throws Exception
	 */
	public void addMusic(Music music)throws Exception;
	
	/**
	 * 获取当前id的music
	 * @param id 
	 * @throws Exception
	 */
	public Music getMusic(int id)throws Exception;
}
