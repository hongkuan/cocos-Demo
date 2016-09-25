package com.kuange.musiccilent.biz;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kuange.musiccilent.activity.MainActivity;
import com.kuange.musiccilent.entity.Music;
import com.kuange.musiccilent.util.HttpUtils;
import com.kuange.musiccilent.util.JSONParser;






import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MusicBiz extends AsyncTask<String, String, List<Music>>{
	
	private static final String TAG = "kuange.MusicBiz";
	
	private Context context;
	
	public MusicBiz(Context context){
		this.context=context;
	}

	
	//执行异步任务时 在工作线程中执行
	//执行完毕后 将会把返回值交给onPostExtcute方法
	protected List<Music> doInBackground(String... params) {
		//调用HttpUtils的工具方法 
		try {
			Log.d(TAG, "start to asyncTask! params:"+params[0]);
			HttpEntity entity=HttpUtils.getEntity(HttpUtils.METHOD_GTE, params[0], null);
			String json=EntityUtils.toString(entity);
			Log.d(TAG, "result:"+json);
			//json:{"result":"ok","data":[{},{}]}
			JSONObject obj=new JSONObject(json);
			String result=obj.getString("result");
			if("ok".equals(result)){
				JSONArray ary=obj.getJSONArray("data");
				List<Music> musics=JSONParser.parse(ary);
				Log.d(TAG, "into:"+musics.toString());
				return musics;
			}
			Log.d(TAG, "end to asyncTask!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//在主线程中执行
	@Override
	protected void onPostExecute(List<Music> result) {
		//更新UI
		Log.d(TAG, "update to ui!");
		MainActivity act =(MainActivity) context;
		act.updateListView(result);
	}
	
}
