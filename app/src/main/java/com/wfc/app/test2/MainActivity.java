package com.wfc.app.test2;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.wfc.app.test2.fragment.LatestJobFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "main";

    private SimpleDraweeView mImageView;

    private TextView mUidTv;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.amain_container, new LatestJobFragment(), "latest").commit();
//        mImageView = (SimpleDraweeView) findViewById(R.id.my_image_view);
//        mUidTv = (TextView) findViewById(R.id.main_uid);
//        Uri uri = Uri.parse("http://d.3987.com/adfcz_140314/002.jpg");
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setTapToRetryEnabled(true)
//                .setOldController(mImageView.getController())
//                .setControllerListener(new ControllerListener<ImageInfo>() {
//                    @Override
//                    public void onSubmit(String id, Object callerContext) {
//                        Log.d(TAG, "onSubmit " + id);
//                    }
//
//                    @Override
//                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                        Log.d(TAG, "onFinalImageSet " + id);
//                    }
//
//                    @Override
//                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
//                        Log.d(TAG, "onIntermediateImageSet " + id);
//                    }
//
//                    @Override
//                    public void onIntermediateImageFailed(String id, Throwable throwable) {
//                        Log.d(TAG, "onIntermediateImageFailed " + id);
//                    }
//
//                    @Override
//                    public void onJobInfoFailure(String id, Throwable throwable) {
//                        Log.e(TAG, "onJobInfoFailure " + id, throwable);
//                    }
//
//                    @Override
//                    public void onRelease(String id) {
//                        Log.d(TAG, "onRelease " + id);
//                    }
//                })
//                .build();
////
////        GenericDraweeHierarchyBuilder builder =
////                new GenericDraweeHierarchyBuilder(getResources());
////        GenericDraweeHierarchy hierarchy = builder
////                .setFadeDuration(300)
////                .build();
////        mImageView.setHierarchy(hierarchy);
//        mImageView.setController(controller);
//        getJobInfo("31330");
//        Bundle bundle = getIntent().getExtras();
//        String uid = bundle.getString("uid");
//        mUidTv.setText(uid);
    }

//    void getJobInfo(String id) {
//        Log.d(TAG, "getJobInfo...");
//        Request request = new Request.Builder()
//                .get()
//                .url("http://apitest.shangshaban.com/api/job/getInfo.htm?id="+ id)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onJobInfoFailure(Call call, IOException e) {
//                Log.e(TAG, e.getMessage(), e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.body().string());
//            }
//        });
//    }

}
