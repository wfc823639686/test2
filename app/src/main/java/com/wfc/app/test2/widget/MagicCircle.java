package com.wfc.app.test2.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.wfc.app.test2.utils.DensityUtils;

/**
 * 代码写的比较仓猝,以后再优化写法和补充那些数值的具体含义,求勿喷QAQ
 */
public class MagicCircle extends View {

    private Path mPath;
    private Paint mFillCirclePaint;


    private float blackMagic = 0.551915024494f;
    private int width, height;
    private int centerX, centerY;
    private int radius;

    float[] ps = {
            radius, 0,
            radius, radius * blackMagic,
            radius * blackMagic, radius,
            0, radius,
            -radius * blackMagic, radius,
            -radius, radius * blackMagic,
            -radius, 0,
            -radius, -radius * blackMagic,
            -radius * blackMagic, -radius,
            0, -radius,
            radius * blackMagic, -radius,
            radius, -radius * blackMagic
    };


    public MagicCircle(Context context) {
        this(context, null, 0);
    }

    public MagicCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mFillCirclePaint = new Paint();
        mFillCirclePaint.setColor(0xFFfe626d);
        mFillCirclePaint.setStyle(Paint.Style.FILL);
        mFillCirclePaint.setStrokeWidth(1);
        mFillCirclePaint.setAntiAlias(true);
        mPath = new Path();
    }

    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        centerX = width / 2;
        centerY = height / 2;
        radius = DensityUtils.dip2px(getContext(), 50);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.translate(radius, radius);
//        canvas.drawCircle(0, 0, radius, mFillCirclePaint);
        drawStatus0(canvas);
    }

    void drawStatus0(Canvas canvas) {
        mPath.moveTo(ps[0], ps[1]);
        mPath.cubicTo(ps[2], ps[3], ps[4], ps[5], ps[6], ps[7]);
        mPath.cubicTo(ps[8], ps[9], ps[10], ps[11], ps[12], ps[13]);
        mPath.cubicTo(ps[14], ps[15], ps[16], ps[17], ps[18], ps[19]);
        mPath.cubicTo(ps[20], ps[21], ps[22], ps[23], ps[0], ps[1]);
        canvas.drawPath(mPath,mFillCirclePaint);
    }

    void drawStatus1(Canvas canvas) {

    }
}