package com.wfc.app.test2.widget;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wfc.app.test2.R;
import com.wfc.app.test2.utils.DensityUtils;

import java.io.IOException;

/**
 * Created by wangfengchen on 16/7/18.
 * vpv
 */
public class VideoPlayView extends RelativeLayout implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private static final String TAG = "VideoPlayView";
    Context mContext;

    SurfaceView surfaceView;

    MediaPlayer mediaPlayer;

    ImageView videoCoverIv;

    ImageView videoPlayIcon;

    ImageView progressView;

    boolean unPlay = true;

    boolean isLoaded;

    boolean loading;

    private String url;

    Runnable mRunner = new Runnable() {

        @Override
        public void run() {
            try {
                Log.d(TAG, "thread name " + Thread.currentThread().getName());
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler mHandler;

    HandlerThread mHandlerThread;

    public VideoPlayView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public VideoPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    void init() {
        mHandlerThread = new HandlerThread("video-download-ht");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());

        surfaceView = new SurfaceView(mContext);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置SurfaceView的类型...
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(lp);
        addView(surfaceView);
        videoCoverIv = new ImageView(mContext);
        videoCoverIv.setScaleType(ImageView.ScaleType.FIT_XY);
        videoCoverIv.setLayoutParams(lp);
        addView(videoCoverIv);
        videoPlayIcon = new ImageView(mContext);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
                DensityUtils.dip2px(mContext, 55),
                DensityUtils.dip2px(mContext, 55));
        lp1.addRule(RelativeLayout.CENTER_IN_PARENT);
        videoPlayIcon.setImageResource(R.mipmap.img_video);
        videoPlayIcon.setVisibility(GONE);
        addView(videoPlayIcon, lp1);
        progressView = new ImageView(mContext);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                DensityUtils.dip2px(mContext, 23),
                DensityUtils.dip2px(mContext, 23));
        lp2.addRule(RelativeLayout.CENTER_IN_PARENT);
        Glide.with(mContext).load(R.mipmap.loading).into(progressView);
        addView(progressView, lp2);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLoaded) {
                    if(unPlay) {
                        videoCoverIv.setVisibility(GONE);
                    }
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        videoPlayIcon.setVisibility(VISIBLE);
                    } else {
                        mediaPlayer.start();
                        videoPlayIcon.setVisibility(GONE);
                    }
                } else {
                    Toast.makeText(mContext, "没有加载完成", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setVideoCover(String url) {
        Glide.with(getContext()).load(url).into(videoCoverIv);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e("mediaPlayer", "onPrepared");
        isLoaded = true;
        loading = false;
        progressView.setVisibility(GONE);
        videoPlayIcon.setVisibility(VISIBLE);
    }

    void reset() {
        progressView.setVisibility(VISIBLE);
        videoPlayIcon.setVisibility(GONE);
        videoCoverIv.setImageBitmap(null);
        try {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
        reset();
        isLoaded = false;
        loading = true;
        mHandler.removeCallbacks(mRunner);
        mHandler.post(mRunner);
    }

    public void release() {
        mHandlerThread.quit();
        mediaPlayer.release();
    }
}
