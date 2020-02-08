package com.study.model.api;


import com.study.model.bean.IndexBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MyApi {



    @GET("index")
    Flowable<IndexBean> getIndexData();







}
