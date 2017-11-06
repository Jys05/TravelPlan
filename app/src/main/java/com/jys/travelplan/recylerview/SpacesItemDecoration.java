package com.jys.travelplan.recylerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mr.Su on 2017/10/5.
 *  简单的RecylerView的item间距（可用在任意一种Manager，在瀑布流也可以使用）
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
//        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
//        }
    }
}