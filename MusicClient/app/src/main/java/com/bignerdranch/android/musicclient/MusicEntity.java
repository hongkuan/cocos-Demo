package com.bignerdranch.android.musicclient;

/**
 * Created by Administrator on 2016/9/25.
 */
public class MusicEntity {

    /**
     * {"album":"君生今世",
     * "albumpic":"images/junshengjinshi.jpg",
     * "author":"小虫",
     * "composer":"小虫",
     * "downcount":"1896",
     * "durationtime":"4:32",
     * "favcount":"658",
     * "id":1,
     * "musicpath": "musics/yelaixiang.mp3",
     * "name":"夜来香",
     * "singer":"邓丽君"}
     */

    private int mId;
    private String mAlbum;
    private String mAlbumpic;
    private String mAuthor;
    private String mComposer;
    private String mDowncount;
    private String mDurationtime;
    private String mFavcount;
    private String mMusicpath;
    private String mName;
    private String mSinger;

    @Override
    public String toString() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public String getAlbumpic() {
        return mAlbumpic;
    }

    public void setAlbumpic(String albumpic) {
        mAlbumpic = albumpic;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getComposer() {
        return mComposer;
    }

    public void setComposer(String composer) {
        mComposer = composer;
    }

    public String getDowncount() {
        return mDowncount;
    }

    public void setDowncount(String downcount) {
        mDowncount = downcount;
    }

    public String getDurationtime() {
        return mDurationtime;
    }

    public void setDurationtime(String durationtime) {
        mDurationtime = durationtime;
    }

    public String getFavcount() {
        return mFavcount;
    }

    public void setFavcount(String favcount) {
        mFavcount = favcount;
    }

    public String getMusicpath() {
        return mMusicpath;
    }

    public void setMusicpath(String musicpath) {
        mMusicpath = musicpath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSinger() {
        return mSinger;
    }

    public void setSinger(String singer) {
        mSinger = singer;
    }
}
