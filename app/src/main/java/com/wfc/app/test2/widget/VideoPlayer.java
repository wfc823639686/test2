package com.wfc.app.test2.widget;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by wangfengchen on 16/7/18.
 * sp
 */
public class VideoPlayer extends SurfaceView implements View.OnClickListener,
        SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private static final String TAG = "VideoPlayer";
    private MediaPlayer mediaPlayer;

    public VideoPlayer(Context context) {
        super(context);
        init();
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置SurfaceView的类型...
        getHolder().setKeepScreenOn(true);
        getHolder().addCallback(this);
    }

    public void setUrl(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");
        if (view == this) {
            if (mediaPlayer.isPlaying()) {
                Log.d(TAG, "click pause");
                mediaPlayer.pause();
            } else {
                Log.d(TAG, "click start");
                mediaPlayer.start();
            }
        }
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

    /**
     * 通过onPrepared播放
     */
    @Override
    public void onPrepared(MediaPlayer arg0) {
//        int videoWidth = mediaPlayer.getVideoWidth();
//        int videoHeight = mediaPlayer.getVideoHeight();
//        if (videoHeight != 0 && videoWidth != 0) {
//            arg0.start();
//        }
        Log.e("mediaPlayer", "onPrepared");
    }

    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}
