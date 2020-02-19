package com.study.model.api;


import com.study.model.bean.CatalogListBean;
import com.study.model.bean.CatalogTabBean;
import com.study.model.bean.CategoryListBean;
import com.study.model.bean.CategoryTabBean;
import com.study.model.bean.IndexBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {


    //主页数据接口
    @GET("index")
    Flowable<IndexBean> getIndexData();

    //获取分类导航数据接口
    @GET("catalog/index")
    Flowable<CatalogTabBean> getCatalogTabData();

    //获取列表选中的数据
    @GET("catalog/current")
    Flowable<CatalogListBean> getCatalogList(@Query("id") int id);

    //获取分类Tab数据的接口goods/category { id: this.data.id }
    @GET("goods/category")
    Flowable<CategoryTabBean> getCategoryTab(@Query("id") int id);

    //商品分类列表数据goods/list{categoryId: that.data.id, page: that.data.page, size: that.data.size}
    @GET("goods/list")
    Flowable<CategoryListBean> getCategoryList(@Query("categoryId") int categoryId,@Query("page") int page,@Query("size") int size);








}
