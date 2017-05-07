package com.solarexsoft.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 06/05/2017
 *    Desc:
 * </pre>
 */

public class FlowLayout extends ViewGroup {
    private List<List<View>> mAllRowViews = new ArrayList<>();
    private List<View> mRowView = new ArrayList<>();
    private List<Integer> mRowHeight = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int iWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int iHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int iHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth = 0;
        int measuredHeight = 0;
        if (iWidthMode == MeasureSpec.EXACTLY && iHeightMode == MeasureSpec.EXACTLY) {
            measuredWidth = iWidthSize;
            measuredHeight = iHeightSize;
        } else {
            int curLineWidth = 0;
            int curLineHeight = 0;
            int childWidth = 0;
            int childHeight = 0;
            int count = getChildCount();

            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
                childWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
                childHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;
                if (childWidth + curLineWidth > iWidthSize) {
                    measuredWidth = Math.max(measuredWidth, curLineWidth);
                    measuredHeight += curLineHeight;
                    mRowHeight.add(curLineHeight);
                    mAllRowViews.add(mRowView);

                    mRowView = new ArrayList<>();
                    curLineWidth = childWidth;
                    curLineHeight = childHeight;
                    mRowView.add(child);
                } else {
                    curLineWidth += childWidth;
                    curLineHeight = Math.max(curLineHeight, childHeight);
                    mRowView.add(child);
                }

                if (i == count - 1) {
                    measuredWidth = Math.max(measuredWidth, curLineWidth);
                    measuredHeight += curLineHeight;
                    mRowHeight.add(curLineHeight);
                    mAllRowViews.add(mRowView);
                }
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = mAllRowViews.size();
        int curTop = 0;
        int curLeft = 0;
        int left, top, right, bottom;
        for (int i = 0; i < lineCount; i++) {
            ArrayList<View> rowView = (ArrayList) mAllRowViews.get(i);
            for (View view : rowView) {
                MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
                left = curLeft + mlp.leftMargin;
                top = curTop + mlp.topMargin;
                right = left + view.getMeasuredWidth();
                bottom = top + view.getMeasuredHeight();

                view.layout(left, top, right, bottom);
                curLeft += view.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            }
            curLeft = 0;
            curTop += mRowHeight.get(i);
        }
        mAllRowViews.clear();
        mRowHeight.clear();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int index);
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, finalI);
                }
            });
        }
    }
}
