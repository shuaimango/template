package com.example.mylibrary.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;


/**
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount = 2;
    private int firstPosition = 0;
    private boolean isMarginParent;

    public SpaceItemDecoration(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }
    public void setIsMarginParent(boolean isMarginParent) {
        this.isMarginParent = isMarginParent;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(space>0) {
            outRect.bottom = space;
            int itemPadding = space / 2;
            if (spanCount > 1) {
                int i = (parent.getChildLayoutPosition(view) - firstPosition) % spanCount;
                if (i <= 0) {
                    outRect.left = isMarginParent ? space : 0;
                    outRect.right = itemPadding;
                } else if (i == spanCount - 1) {
                    outRect.left = itemPadding;
                    outRect.right = isMarginParent ? space : 0;
                } else {
                    outRect.left = itemPadding;
                    outRect.right = itemPadding;
                }
            }
        }else{
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }

    public static final int SHOW_BEGIN = 0x10000000;
    public static final int SHOW_MIDDLE = 0x20000000;
    public static final int SHOW_END = 0x80000000;
    private int showFlag = SHOW_MIDDLE;

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    private int mOrientation;

    /**
     * 设置分割线的显示,开始,中间结尾
     *
     * @param showFlag {@link #SHOW_BEGIN, #SHOW_MIDDLE,#SHOW_END}
     */
    public void setShowFlag(int showFlag) {
        this.showFlag = showFlag;
    }

    public SpaceItemDecoration(Context context, int orientation) {
        this(context, orientation, null);
    }

    public SpaceItemDecoration(Context context, Drawable divider) {
        mDivider = divider;
        if (mDivider == null) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }
        setOrientation(LinearLayoutManager.VERTICAL);
    }
    public SpaceItemDecoration(Context context, int orientation, Drawable divider) {
        mDivider = divider;
        if (mDivider == null) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }
        setOrientation(orientation);
    }

    public SpaceItemDecoration(Context context, int orientation, int size, int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置形状
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        //填充颜色
        gradientDrawable.setColor(color);
        switch (orientation) {
            case LinearLayoutManager.HORIZONTAL:
                gradientDrawable.setSize(size, Integer.MAX_VALUE);
                break;
            case LinearLayoutManager.VERTICAL:
                gradientDrawable.setSize(Integer.MAX_VALUE, size);
                break;
        }
        mDivider = gradientDrawable;
        if (mDivider == null) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }
        setOrientation(orientation);
    }

    /**
     * @param context
     * @param orientation
     * @param spaceSizedp 注意,这里单位是dp
     */
    public SpaceItemDecoration(Context context, int orientation, float spaceSizedp) {
        this(context, orientation, ConvertUtils.dp2px( spaceSizedp), android.R.color.transparent);
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    protected boolean showBegin() {
        return (showFlag & SHOW_BEGIN) != 0;
    }

    protected boolean showMiddle() {
        return (showFlag & SHOW_MIDDLE) != 0;
    }

    protected boolean showEnd() {
        return (showFlag & SHOW_END) != 0;
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        if (showBegin()) {
            mDivider.setBounds(0, 0, right, mDivider.getIntrinsicHeight());
            mDivider.draw(c);
        }
        final int childCount = parent.getChildCount();
        final int lastIndex = childCount - 1;
        for (int i = 0; i < childCount; i++) {
            boolean canDraw = (i != lastIndex && showMiddle()) ||
                    (i == lastIndex && showEnd());
            if (canDraw) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }


    public void drawHorizontal(Canvas c, RecyclerView parent) {

        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        if (showBegin()) {
            mDivider.setBounds(0, 0, mDivider.getIntrinsicWidth(), bottom);
            mDivider.draw(c);
        }

        final int childCount = parent.getChildCount();
        final int lastIndex = childCount - 1;
        for (int i = 0; i < childCount; i++) {
            boolean canDraw = (i != lastIndex && showMiddle()) ||
                    (i == lastIndex && showEnd());
            if (canDraw) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }


    public Drawable getDivider() {
        return mDivider;
    }
}
