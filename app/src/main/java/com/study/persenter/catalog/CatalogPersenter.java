package com.study.persenter.catalog;

import com.study.component.CommonSubscriber;
import com.study.model.HttpManager;
import com.study.model.bean.CatalogListBean;
import com.study.model.bean.CatalogTabBean;
import com.study.persenter.BasePersenter;
import com.study.interfaces.catalog.CatalogContract;
import com.study.utils.RxUtils;

public class CatalogPersenter extends BasePersenter<CatalogContract.View> implements CatalogContract.Persenter {

    //获取分类列表数据
    @Override
    public void getCatalogTabData() {
        addSubscribe(HttpManager.getMyApi().getCatalogTabData()
        .compose(RxUtils.<CatalogTabBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CatalogTabBean>(mView){

            @Override
            public void onNext(CatalogTabBean catalogTabBean) {
                if(catalogTabBean.getErrno() == 0){
                    mView.getCatalogTabDataReturn(catalogTabBean);
                }else{
                    mView.showErrMsg(catalogTabBean.getErrmsg());
                }
            }
        }));
    }

    //获取指定列表的数据
    @Override
    public void getCatalogList(int id) {
        addSubscribe(HttpManager.getMyApi().getCatalogList(id)
                .compose(RxUtils.<CatalogListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CatalogListBean>(mView){

                    @Override
                    public void onNext(CatalogListBean catalogListBean) {
                        if(catalogListBean.getErrno() == 0){
                            mView.getCatalogListReturn(catalogListBean);
                        }else{
                            mView.showErrMsg(catalogListBean.getErrmsg());
                        }
                    }
                }));
    }
}
