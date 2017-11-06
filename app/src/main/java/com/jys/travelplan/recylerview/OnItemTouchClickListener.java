package com.jys.travelplan.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.blend.base.utils.LogUtil;

/**
 * Created by Mr.Su on 2017/10/15.
 * RecyclerView的点击和长按监听-->通过“手势”（GestureDetector）实现
 */

public  abstract class OnItemTouchClickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector mGestureDetector;
    private RecyclerView mRecyclerView;

    public OnItemTouchClickListener(RecyclerView recyclerView) {
        mGestureDetector = new GestureDetector(recyclerView.getContext(), mOnGestureListener);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (mGestureDetector != null && mGestureDetector.onTouchEvent(e)) {
            return true;
        }
        return false;
    }


    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                int postion = mRecyclerView.getChildAdapterPosition(view);
                LogUtil.e("RecyclerView的", "第" + postion + "个");
                onItemClickListener(view, postion, e);
            }
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                int postion = mRecyclerView.getChildAdapterPosition(view);
                LogUtil.e("RecyclerView的", "第" + postion + "个");
                onItemLongClickListener(view, postion, e);
            }
        }
    };

    protected abstract void onItemClickListener(View view, int position, MotionEvent event);

    protected abstract void onItemLongClickListener(View view, int position, MotionEvent event);


}
