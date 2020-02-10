package com.study.interfaces.category;

import com.study.interfaces.IBaseView;
import com.study.interfaces.IPersenter;
import com.study.model.bean.CategoryListBean;
import com.study.model.bean.CategoryTabBean;

public interface CategoryConstract {

    //分类列表页面的V层
    interface View extends IBaseView{

        //加载分类列表的tab数据返回
        void getCategoryTabReturn(CategoryTabBean result);
        //获取分类类别的tab商品数据返回
        void getGoodsListReturn(CategoryListBean result);

    }

    //分类列表页面的p层
    interface Persenter extends IPersenter<View>{

        //加载分类列表的tab数据
        void getCategoryTab(int id);
        //获取分类列表tab所对应的商品数据
        void getGoodsList(int categoryId,int page,int size);

    }

}
