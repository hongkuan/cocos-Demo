package com.example.imageloder_zhy;

import android.support.v4.app.Fragment;

public class ImageLoaderTestActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return ImageLoaderTestFragment.newInstence();
	}

}
