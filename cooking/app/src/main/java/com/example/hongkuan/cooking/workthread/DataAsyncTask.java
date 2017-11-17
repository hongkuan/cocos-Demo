package com.example.hongkuan.cooking.workthread;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by hongk on 2017/11/15.
 */

public abstract class DataAsyncTask extends AsyncTask<Object, Object, Object> {
    private static final String TAG = "DataAsyncTask";
    private final OnResultCallback mCallback;

    public abstract Object isRunBackground(Object... params);

    public DataAsyncTask(OnResultCallback callback){
        this.mCallback = callback;
    }

    @Override
    protected Object doInBackground(Object... params) {
        Log.i(TAG, "doInBackground: "+ Arrays.toString(params));

        return isRunBackground(params);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
        this.mCallback.onCancelledCallback(o);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        this.mCallback.onResultCallback(o);
    }

    public interface OnResultCallback{
        void onResultCallback(Object results);
        void onCancelledCallback(Object results);
    }
}
