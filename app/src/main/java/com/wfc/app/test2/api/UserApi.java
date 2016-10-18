package com.wfc.app.test2.api;

import android.app.job.JobInfo;

import com.wfc.app.test2.bean.Comment;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.EnterpriseResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.model.JobInfoModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class UserApi extends BaseApi{
    //定义接口
    private interface UserService {
        //GET注解不可用@FormUrlEncoded，要用@Query注解引入请求参数
        @GET("api/job/getInfo.htm")
        Observable<JobResult> getJobInfo(@Query("id") int id);

        @GET("api/enterprise/getInfo.htm")
        Observable<EnterpriseResult> getEnterpriseInfo(@Query("id") int id);

        @GET("api/enterprise/getComments.htm")
        Observable<CommentListResult> getEnterpriseComments(@Query("id") int id);

        //POST方法没有缓存，适用于更新数据
//        @FormUrlEncoded
//        @POST("")
//        Observable<JobResult> insertJob(@Field("userName") String userName);
    }

    protected static final UserService service = getRetrofit().create(UserService.class);

    public static Observable<JobResult> getJobInfo(int userId){
        return service.getJobInfo(userId);
    }

    public static Observable<EnterpriseResult> getEnterpriseInfo(int userId){
        return service.getEnterpriseInfo(userId);
    }

    public static Observable<CommentListResult> getEnterpriseComments(int userId){
        return service.getEnterpriseComments(userId);
    }

    //更新用户名接口
//    public static Observable<JobResult> insertJob(String userName){
//        return service.insertJob(userName);
//    }
}