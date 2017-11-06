package com.jys.travelplan.recylerview.TouchHelp;

/**
 * Created by Sujiayong on 2017/10/13.
 * recyclerView的拖拽
 * 用法：      DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
 itemTouchHelper.attachToRecyclerView(mRecyclerView);
 itemTouchHelper.setDragEnable(true);
 itemTouchHelper.setSwipeEnable(false);
 （以及在item的layout中设置       android:translationZ="2dp"——这样拖拽时就有高度（实现阴影））
 */

public class DefaultItemTouchHelper extends YolandaItemTouchHelper {

    private DefaultItemTouchHelpCallback itemTouchHelpCallback;

    public DefaultItemTouchHelper(DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener) {
        super(new DefaultItemTouchHelpCallback(onItemTouchCallbackListener));
        itemTouchHelpCallback = (DefaultItemTouchHelpCallback) getCallback();
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    public void setDragEnable(boolean canDrag) {
        itemTouchHelpCallback.setDragEnable(canDrag);
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setSwipeEnable(boolean canSwipe) {
        itemTouchHelpCallback.setSwipeEnable(canSwipe);
    }
}