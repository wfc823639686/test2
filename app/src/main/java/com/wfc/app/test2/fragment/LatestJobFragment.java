package com.wfc.app.test2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfc.app.test2.R;
import com.wfc.app.test2.adapter.JobListAdapter;
import com.wfc.app.test2.bean.Job;
import com.wfc.app.test2.bean.JobListResult;
import com.wfc.app.test2.presenter.JobListPresenter;
import com.wfc.app.test2.view.IJobListView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class LatestJobFragment extends Fragment implements IJobListView {

    private static final String TAG = "LatestJobFragment";

    JobListPresenter jobListPresenter;
    private RecyclerView mRecyclerView;
    private JobListAdapter jobListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobListPresenter = new JobListPresenter(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_job_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fjob_list_rv);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        jobListAdapter = new JobListAdapter();
        mRecyclerView.setAdapter(jobListAdapter);

        jobListPresenter.getJobs();
    }

    @Override
    public String getOrderBy() {
        return "1";
    }

    @Override
    public String getLast() {
        return "";
    }

    @Override
    public void onLoad(final JobListResult result) {
        if (result!=null && result.getResults()!=null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<Job> jobs = result.getResults();
                    jobListAdapter.addList(jobs);
                    Log.d(TAG, "adapter count "+jobListAdapter.getItemCount());
                }
            });
        }
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onLoadCompleted() {

    }
}
