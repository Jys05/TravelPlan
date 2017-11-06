package com.jys.travelplan.recylerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


/**
 * @author zhangdeming
 * @date 创建时间 2017/2/8
 * @description RecyclerView的Grid布局所使用的分隔线
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerColor;
    private Paint dividerPaint;
    private int dividerWidth;

    public GridItemDecoration(@ColorInt int color, int width) {
        dividerColor = color;
        dividerPaint = new Paint();
        dividerPaint.setColor(dividerColor);
        dividerWidth = width;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        drawHorizontal(c, spanCount, parent);
        drawVertical(c, spanCount, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas c, int spanCount, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin - dividerWidth;
            final int right = child.getRight() + params.rightMargin
                    + dividerWidth;
            final int bottom1 = child.getTop() - params.topMargin;
            final int top1 = bottom1 - dividerWidth;
            final int top2 = child.getBottom() + params.bottomMargin;
            final int bottom2 = top2 + dividerWidth;
            if (isLastRaw(parent, i, spanCount, childCount)) {
                // 如果是最后一行，则需要绘制底部
                c.drawRect(left, top1, right, bottom1, dividerPaint);
                c.drawRect(left, top2, right, bottom2, dividerPaint);
            } else {
                c.drawRect(left, top1, right, bottom1, dividerPaint);
            }
        }
    }

    public void drawVertical(Canvas c, int spanCount, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int right1 = child.getLeft() + params.leftMargin;
            final int left1 = right1 - dividerWidth;
            final int left2 = child.getRight() - params.rightMargin;
            final int right2 = left2 + dividerWidth;
            if (isLastColum(parent, i, spanCount, childCount)) {
                // 如果是最后一列，则需要绘制右边
                c.drawRect(left1, top, right1, bottom, dividerPaint);
                c.drawRect(left2, top, right2, bottom, dividerPaint);
            } else {
                c.drawRect(left1, top, right1, bottom, dividerPaint);
            }
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
            if (pos + 1 == childCount) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if (pos + spanCount >= childCount) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)
                    return true;
            } else {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount) && isLastColum(parent, itemPosition, spanCount, childCount)) {
            outRect.set(dividerWidth, dividerWidth, dividerWidth, dividerWidth);
        } else if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
            // 如果是最后一行，则需要绘制底部
            outRect.set(dividerWidth, dividerWidth, 0, dividerWidth);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount)) {
            // 如果是最后一列，则需要绘制右边
            outRect.set(dividerWidth, dividerWidth, dividerWidth, 0);
        } else {
            outRect.set(dividerWidth, dividerWidth, 0, 0);
        }
    }
}
