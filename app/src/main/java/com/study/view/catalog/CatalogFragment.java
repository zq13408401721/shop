package com.study.view.catalog;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.study.R;
import com.study.base.BaseFragment;
import com.study.base.adapter.BaseAdapter;
import com.study.interfaces.IPersenter;
import com.study.interfaces.catalog.*;
import com.study.model.bean.CatalogItem;
import com.study.model.bean.CatalogListBean;
import com.study.model.bean.CatalogTabBean;
import com.study.persenter.catalog.CatalogPersenter;
import com.study.view.category.CategoryActivity;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class CatalogFragment extends BaseFragment<CatalogContract.View, CatalogContract.Persenter> implements CatalogContract.View,
        VerticalTabLayout.OnTabSelectedListener, BaseAdapter.OnItemClickListener {

    VerticalTabLayout verticalTabLayout;

    ImageView img;
    TextView txtDesc;
    TextView txtTitle;
    RecyclerView recyclerView;
    List<String> titles;

    List<CatalogItem> list;
    CatalogAdapter catalogAdapter;
    //竖导航的列表
    List<CatalogTabBean.DataBean.CategoryListBean> categoryList;


    @Override
    protected int getLayout() {
        return R.layout.fragment_catalog;
    }

    @Override
    protected void initView(View view) {
        verticalTabLayout = view.findViewById(R.id.verticalTab);
        img = view.findViewById(R.id.img);
        txtTitle = view.findViewById(R.id.txt_title);
        txtDesc = view.findViewById(R.id.txt_desc);
        recyclerView = view.findViewById(R.id.recyclerview);

        titles = new ArrayList<>();

        list = new ArrayList<>();
        catalogAdapter = new CatalogAdapter(list);
        //禁止recyclerview垂直滚动
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(catalogAdapter);
        catalogAdapter.setOnItemClickListener(this);
        verticalTabLayout.addOnTabSelectedListener(this);
    }

    //创建竖导航的tabadapter
    TabAdapter tabAdapter = new TabAdapter() {
        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                    .setContent(titles.get(position))//设置数据   也有设置字体颜色的方法
                    .build();
            return title;
        }

        @Override
        public int getBackground(int position) {
            return Color.parseColor("#D81B60");
        }
    };

    @Override
    protected void initData() {
        //加载分类的tab数据
        persenter.getCatalogTabData();
    }

    @Override
    protected CatalogContract.Persenter createPersenter() {
        return new CatalogPersenter();
    }

    @Override
    public void showErrMsg(String err) {

    }

    //加载分类数据的导航返回
    @Override
    public void getCatalogTabDataReturn(CatalogTabBean result) {
        categoryList = result.getData().getCategoryList();
        titles.clear();
        //筛选竖导航的标题数据
        for(CatalogTabBean.DataBean.CategoryListBean item : result.getData().getCategoryList()) {
            titles.add(item.getName());
        }
        verticalTabLayout.setTabAdapter(tabAdapter);

        updateInfo(result.getData().getCurrentCategory().getBanner_url(),result.getData().getCurrentCategory().getFront_desc(),
                result.getData().getCurrentCategory().getName()+"分类");
        //刷新列表
        list.clear();
        for(CatalogTabBean.DataBean.CurrentCategoryBean.SubCategoryListBean item:result.getData().getCurrentCategory().getSubCategoryList()){
            CatalogItem catalogItem = new CatalogItem();
            catalogItem.id = item.getId();
            catalogItem.img= item.getWap_banner_url();
            catalogItem.name = item.getName();
            list.add(catalogItem);
        }
        catalogAdapter.notifyDataSetChanged();
    }

    //点击竖导航的tab请求列表数据的返回
    @Override
    public void getCatalogListReturn(CatalogListBean result) {

        updateInfo(result.getData().getCurrentCategory().getBanner_url(),result.getData().getCurrentCategory().getFront_desc(),
                result.getData().getCurrentCategory().getName()+"分类");

        list.clear();
        for(CatalogListBean.DataBean.CurrentCategoryBean.SubCategoryListBean item:result.getData().getCurrentCategory().getSubCategoryList()){
            CatalogItem catalogItem = new CatalogItem();
            catalogItem.id = item.getId();
            catalogItem.img= item.getWap_banner_url();
            catalogItem.name = item.getName();
            list.add(catalogItem);
        }
        catalogAdapter.notifyDataSetChanged();
    }

    //刷新右边的界面
    private void updateInfo(String imgUrl,String des,String title){
        if(!TextUtils.isEmpty(imgUrl)){
            Glide.with(context).load(imgUrl).into(img);
        }
        txtDesc.setText(des);
        txtTitle.setText(title);
    }

    //刷新列表
    private void updateList(){

    }

    //竖导航切换
    @Override
    public void onTabSelected(TabView tab, int position) {
        if(categoryList != null){
            int id = categoryList.get(position).getId();
            //请求id对应的列表数据
            persenter.getCatalogList(id);
        }
    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }

    //点击网格类别的条目接口回调
    @Override
    public void onItemClick(View v, int position) {
        int id = list.get(position).id;
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
