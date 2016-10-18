package com.wfc.app.test2.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by wangfengchen on 16/9/22.
 */

public class HeadChangeScrollView extends ScrollView {


    int endHeight = 100;

    int status = 1;

    OnChangeListener onChangeListener;

    public HeadChangeScrollView(Context context) {
        super(context);
    }

    public HeadChangeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadChangeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HeadChangeScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(t>0) {
            if(t<endHeight) {
                System.out.println("t "+t);
                System.out.println("s " + (float) t / endHeight);
                if(onChangeListener!=null)
                    onChangeListener.change((float) t / endHeight);
                if(status==2) {
                    System.out.println("title gone");
                    if(onChangeListener!=null)
                        onChangeListener.statusChange(0);
                }
                status = 1;
            } else {
                if(status==1) {
                    System.out.println("title show");
                    if(onChangeListener!=null)
                        onChangeListener.statusChange(1);
                }
                status = 2;
            }
        }
    }

    public interface OnChangeListener {

        void change(float s);

        void statusChange(int t);
    }
}
