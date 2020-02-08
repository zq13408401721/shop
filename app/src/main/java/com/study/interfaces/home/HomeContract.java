package com.study.interfaces.home;

import com.study.interfaces.IBaseView;
import com.study.interfaces.IPersenter;
import com.study.model.bean.IndexBean;

public interface HomeContract {

    //主页数据返回的接口
    interface View extends IBaseView{
        void homeDataReturn(IndexBean resulut);
    }

    //p层 接口给到View层调用，用于触发主页数据加载
    interface Persenter extends IPersenter<View>{
        void getHomeData();
    }

}
