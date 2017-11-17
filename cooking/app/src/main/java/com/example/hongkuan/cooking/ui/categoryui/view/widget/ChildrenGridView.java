package com.example.hongkuan.cooking.ui.categoryui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by hongk on 2017/11/13.
 */

public class ChildrenGridView extends GridView {
    public ChildrenGridView(Context context) {
        super(context);
    }

    public ChildrenGridView(Context context, AttributeSet atts){
        super(context, atts);
    }

    public ChildrenGridView(Context context, AttributeSet atts, int defStyle){
        super(context, atts, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
