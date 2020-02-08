package com.study.persenter.home;

import com.study.component.CommonSubscriber;
import com.study.model.HttpManager;
import com.study.model.bean.IndexBean;
import com.study.model.bean.LoginInfo;
import com.study.persenter.BasePersenter;
import com.study.interfaces.home.HomeContract;
import com.study.utils.RxUtils;

public class HomePersenter extends BasePersenter<HomeContract.View> implements HomeContract.Persenter {
    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getMyApi().getIndexData()
                .compose(RxUtils.<IndexBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<IndexBean>(mView){

                    @Override
                    public void onNext(IndexBean result) {
                        mView.homeDataReturn(result);
                    }
                }));
    }
}
