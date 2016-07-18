package com.wfc.app.test2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wfc.app.test2.widget.VideoPlayView;
import com.wfc.app.test2.widget.VideoPlayer;

/**
 * Created by wangfengchen on 16/7/18.
 */
public class TestVideoActivity extends Activity implements View.OnClickListener {

    VideoPlayView videoPlayView;

    Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_video);
        videoPlayView = (VideoPlayView) findViewById(R.id.vp);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn2.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                videoPlayView.setUrl("http://ssb-video.shangshaban.com/Video_20160620090441.mp4");
                videoPlayView.setVideoCover("http://ssb-img.shangshaban.com/Image_20160620090440.png");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayView.release();
    }
}
