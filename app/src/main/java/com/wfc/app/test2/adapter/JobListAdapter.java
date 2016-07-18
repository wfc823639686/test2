package com.wfc.app.test2.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.wfc.app.test2.R;
import com.wfc.app.test2.bean.Job;
import com.wfc.app.test2.widget.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class JobListAdapter extends RecyclerView.Adapter implements IListAdapter<Job> {

    private List<Job> list = new ArrayList<Job>();
    private final static String TAG = "JobListAdapter";

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Job job = list.get(position);
        JobViewHolder jobViewHolder = (JobViewHolder) holder;
        jobViewHolder.jobNameTv.setText(job.getJobName());
        Glide.with(jobViewHolder.coverIv.getContext())
                .load(job.getEnterprise().getPhoto())
                .placeholder(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(jobViewHolder.coverIv.getContext()))
                .into(jobViewHolder.coverIv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void replace(List<Job> l) {
        list = l;
        notifyDataSetChanged();
    }

    @Override
    public void addList(List<Job> l) {
        list.addAll(l);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        public View rootView;
        public TextView jobNameTv;
        public SimpleDraweeView coverIv;

        public JobViewHolder(View itemView) {
            super(itemView);
            jobNameTv = (TextView) itemView.findViewById(R.id.ijob_name);
            coverIv = (SimpleDraweeView) itemView.findViewById(R.id.ijob_cover);
//            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
//            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
