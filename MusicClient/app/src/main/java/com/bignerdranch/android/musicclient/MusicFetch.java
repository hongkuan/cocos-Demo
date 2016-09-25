package com.bignerdranch.android.musicclient;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */
public class MusicFetch {

    private static final String TAG = "MusicFetch";
    private static final String MusicOnlineUrl = "http://169.254.114.31:8080/musiconline/loadMusics";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0 ;
            byte[] buffer = new byte[1042];
            while ((bytesRead = in.read(buffer))>0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return  out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public List<MusicEntity> fetchItems(){
        List<MusicEntity> items = new ArrayList<>();
        try {
            String jsonString = getUrlString(MusicOnlineUrl);
            Log.i(TAG, "fetchItems: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        }catch (JSONException je ){
            Log.e(TAG, "music to parse JSON: ", je);
        }catch (IOException ioe){
            Log.e(TAG, "music to fetch items: ",ioe);
        }
        return items;
    }

    private void parseItems(List<MusicEntity> items, JSONObject jsonBody) throws IOException,JSONException{
        JSONArray musicItems = jsonBody.getJSONArray("data");

        for (int i =0 ; i < musicItems.length(); i++){
            JSONObject musicJsonObject = musicItems.getJSONObject(i);

            MusicEntity musicEntity = new MusicEntity();
            musicEntity.setId(musicJsonObject.getInt("id"));
            musicEntity.setAlbum(musicJsonObject.getString("album"));
            musicEntity.setAlbumpic(musicJsonObject.getString("albumpic"));
            musicEntity.setAuthor(musicJsonObject.getString("author"));
            musicEntity.setComposer(musicJsonObject.getString("composer"));
            musicEntity.setDowncount(musicJsonObject.getString("downcount"));
            musicEntity.setDurationtime(musicJsonObject.getString("durationtime"));
            musicEntity.setFavcount(musicJsonObject.getString("favcount"));
            musicEntity.setMusicpath(musicJsonObject.getString("musicpath"));
            musicEntity.setName(musicJsonObject.getString("name"));
            musicEntity.setSinger(musicJsonObject.getString("singer"));

            items.add(musicEntity);
        }

    }

}
