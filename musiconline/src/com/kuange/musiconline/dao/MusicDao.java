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
}
