package com.example.kuijin.mycnblogs.view.page.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kuijin on 2016/9/27.
 */
public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private int orientation;

    private ShapeDrawable shapeDrawable;
    private int shapeWidth = 5;
    private int shapeHeight = 5;

    public RecyclerViewItemDecoration() {
        this.orientation = RecyclerView.VERTICAL;

        initShape();
    }

    public RecyclerViewItemDecoration(int orientation) {
        if (orientation == RecyclerView.HORIZONTAL) {
            this.orientation = RecyclerView.HORIZONTAL;
        } else {
            this.orientation = RecyclerView.VERTICAL;
        }
        initShape();
    }

    private void initShape() {
        shapeDrawable = new ShapeDrawable();
        shapeDrawable.getPaint().setColor(Color.GRAY);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (orientation == RecyclerView.HORIZONTAL) {
            drawHorizontal(c, parent, state);
        } else {
            drawVertical(c, parent, state);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int count = parent.getChildCount();
        for(int index = 0; index < count; index++) {
            View child = parent.getChildAt(index);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + shapeHeight;

            shapeDrawable.setBounds(left, top, right, bottom);
            shapeDrawable.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == RecyclerView.HORIZONTAL) {
            outRect.set(0, 0, shapeWidth, 0);
        } else {
            outRect.set(0, 0, 0, shapeHeight);
        }
    }
}
