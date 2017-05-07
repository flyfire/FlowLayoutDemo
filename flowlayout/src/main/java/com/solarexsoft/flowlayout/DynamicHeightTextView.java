package com.solarexsoft.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <pre>
 *    Author: houruhou
 *    Project: https://solarex.github.io/projects
 *    CreatAt: 07/05/2017
 *    Desc:
 * </pre>
 */

public class DynamicHeightTextView extends TextView {
    private float mHeightRatio;

    public DynamicHeightTextView(Context context) {
        this(context, null);
    }

    public DynamicHeightTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicHeightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .DynamicHeightTextView);
        if (typedArray != null) {
            mHeightRatio = typedArray.getFloat(R.styleable.DynamicHeightTextView_heightRatio, 0f);
            typedArray.recycle();
        }
    }

    public float getHeightRatio() {
        return mHeightRatio;
    }

    public void setHeightRatio(float heightRatio) {
        mHeightRatio = heightRatio;
        if (mHeightRatio > 0) {
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeightRatio > 0){
            int height = (int)(widthMeasureSpec * mHeightRatio);
            setMeasuredDimension(widthMeasureSpec, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
