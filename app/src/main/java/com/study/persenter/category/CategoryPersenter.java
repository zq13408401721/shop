package com.study.persenter.category;

import com.study.component.CommonSubscriber;
import com.study.model.HttpManager;
import com.study.model.bean.CategoryListBean;
import com.study.model.bean.CategoryTabBean;
import com.study.persenter.BasePersenter;
import com.study.interfaces.category.CategoryConstract;
import com.study.utils.RxUtils;

import io.reactivex.CompletableSource;

public class CategoryPersenter extends BasePersenter<CategoryConstract.View> implements CategoryConstract.Persenter {

    //获取分类列表页面的tab数据
    @Override
    public void getCategoryTab(int id) {
        addSubscribe(HttpManager.getMyApi().getCategoryTab(id)
        .compose(RxUtils.<CategoryTabBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CategoryTabBean>(mView){
            @Override
            public void onNext(CategoryTabBean categoryTabBean) {
                mView.getCategoryTabReturn(categoryTabBean);
            }
        }));
    }

    //获取分类类别页面的商品数据
    @Override
    public void getGoodsList(int categoryId, int page, int size) {
        addSubscribe(HttpManager.getMyApi().getCategoryList(categoryId,page,size)
                .compose(RxUtils.<CategoryListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryListBean>(mView){
                    @Override
                    public void onNext(CategoryListBean categoryListBean) {
                        mView.getGoodsListReturn(categoryListBean);
                    }
                }));
    }
}
