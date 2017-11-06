package com.jys.travelplan.recylerview;

import android.content.Context;

import java.util.List;

/**
 * Created by Sunflower on 2015/8/17.
 */
public abstract class QuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {
    public QuickAdapter(Context context, int layoutResId) {
        super(context.getApplicationContext(), layoutResId);
    }

    public QuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context.getApplicationContext(), layoutResId, data);
    }


}
