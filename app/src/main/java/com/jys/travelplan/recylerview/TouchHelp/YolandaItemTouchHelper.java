package com.jys.travelplan.recylerview.TouchHelp;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Sujiayong on 2017/10/13.
 * recyclerView的拖拽
 */

public class YolandaItemTouchHelper extends ItemTouchHelper {

    private Callback mCallback;

    public YolandaItemTouchHelper(Callback callback) {
        super(callback);
        mCallback = callback;
    }


    public Callback getCallback() {
        return mCallback;
    }
}