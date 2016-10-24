package com.wfc.app.test2.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import com.wfc.app.test2.R;
import com.wfc.app.test2.utils.DensityUtils;

public class SlidingSelectView extends ViewGroup {

    private static final String TAG = "SlidingSelectView";

    int photoL, photoT, photoR, photoB, photoSize;

    int width, height;

    ImageView photoIv;

    private float startY;

    private float downY;

    Scroller mScroller;


    public SlidingSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    void initPhotoIv() {
        photoIv = new ImageView(getContext());
        photoIv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        LayoutParams lp = new LayoutParams(
                DensityUtils.dip2px(getContext(), photoSize),
                DensityUtils.dip2px(getContext(), photoSize));
        photoIv.setLayoutParams(lp);
        addView(photoIv);
        setOnTouchListener(photoTouchListener);
    }

    void initSize() {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Log.e(TAG, "get w h->" + width + " " + height);
//        photoSize = width - DensityUtils.dip2px(getContext(), 30 * 2);
        photoSize = DensityUtils.dip2px(getContext(), 240);
        photoL = (width - photoSize) / 2;
        photoR = width - photoL;
        photoT = (height - photoSize) /2;
        photoB = height - photoT;
        Log.e(TAG, "get photo size->" + photoL + " " + photoR
                + " " + photoT + " " + photoB);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(width==0 || height==0) {
            initSize();
            initPhotoIv();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed) {
            Log.e(TAG, "l t r b->" + l + " " + t + " " + r + " " + b);
            photoIv.layout(photoL, photoT, photoR, photoB);
        }
    }

    private OnTouchListener photoTouchListener = (v, event) -> {
        Log.e(TAG, "getAction " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "y " + startY);
                startY = event.getY();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                Log.e(TAG, "y " + y);
                float offY = y - startY;
                photoT = photoT + (int) offY;
                photoB = photoT + photoSize;
                startY = y;
//                photoIv.layout(photoL, photoT, photoR, photoB);
                scrollBy(0,- (int) offY);
                break;
            case MotionEvent.ACTION_UP:
                float offY1 = event.getY() - downY;
                if(Math.abs(offY1) > DensityUtils.dip2px(getContext(), 150)) {
                    //如果滑动超过20dp
                    if(offY1 > 0) {
                        //不感兴趣
                        Log.e(TAG, "不感兴趣");
                        down();
                    } else {
                        Log.e(TAG, "感兴趣");
                        up();
                    }
                }
                //开启滑动,让其回到原点
                mScroller.startScroll(getScrollX(),
                        getScrollY(),
                        -getScrollX() ,-getScrollY());
                break;
        }
        return true;
    };

    void up() {
        photoIv.setVisibility(GONE);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
        valueAnimator.addUpdateListener(animation -> {
            PointF point = (PointF) animation.getAnimatedValue();
            photoIv.setX(point.x);
            photoIv.setY(point.y);

        });
    }

    void down() {
        photoIv.setVisibility(GONE);
    }

    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
        }
        invalidate();//必须要调用
    }
}
