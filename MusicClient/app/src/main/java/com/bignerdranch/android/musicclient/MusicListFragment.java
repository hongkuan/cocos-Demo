package com.bignerdranch.android.musicclient;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */
public class MusicListFragment extends Fragment {

    private static final String TAG = "MusicListFragment";

    private RecyclerView mMusicListRecyclerView;
    private List<MusicEntity> mMusics = new ArrayList<>();

    public static MusicListFragment newInstance() {
        return new MusicListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new MusicItemsTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        mMusicListRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_music_list_view);
        mMusicListRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        setupAdapter();

        return view;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mMusicListRecyclerView.setAdapter(new MusicAdapter(mMusics));
        }
    }

    private class MusicHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private ImageView mImageView ;

        public MusicHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.fragment_music_item_text_view);
            mImageView = (ImageView) itemView.findViewById(R.id.fragment_music_item_image_view);
        }

        public void bindMusicItem(String name, Drawable drawable) {
            mNameTextView.setText(name);
            mImageView.setImageDrawable(drawable);
        }
    }


    private class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {

        private List<MusicEntity> mItems;

        public MusicAdapter(List<MusicEntity> items) {
            this.mItems = items;
        }

        @Override
        public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.music_item, parent, false);
            return new MusicHolder(view);
        }

        @Override
        public void onBindViewHolder(MusicHolder holder, int position) {
            MusicEntity item = mItems.get(position);
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
            holder.bindMusicItem(item.getName(),drawable);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private class MusicItemsTask extends AsyncTask<String, Void, List<MusicEntity>> {
        @Override
        protected List<MusicEntity> doInBackground(String... parameter) {
            return new MusicFetch().fetchItems();
        }

        @Override
        protected void onPostExecute(List<MusicEntity> musicEntities) {
            mMusics = musicEntities;
            setupAdapter();
        }
    }


}
