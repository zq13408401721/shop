package com.study.view.category;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.study.R;
import com.study.base.BaseActivity;
import com.study.interfaces.IPersenter;
import com.study.interfaces.category.CategoryConstract;
import com.study.model.bean.CategoryListBean;
import com.study.model.bean.CategoryTabBean;
import com.study.persenter.category.CategoryPersenter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends BaseActivity<CategoryConstract.View, CategoryConstract.Persenter> implements CategoryConstract.View,
        TabLayout.BaseOnTabSelectedListener {

    ImageView img_back;
    TabLayout tabLayout;
    TextView txtTitle;
    TextView txtDesc;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    List<CategoryListBean.DataBeanX.GoodsListBean> list;




    @Override
    protected int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void initView() {
        img_back = findViewById(R.id.img_back);
        tabLayout = findViewById(R.id.tabLayout);
        txtTitle = findViewById(R.id.txt_title);
        txtDesc = findViewById(R.id.txt_desc);
        recyclerView = findViewById(R.id.recyclerview);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id",0);
        //获取tab相关的数据
        persenter.getCategoryTab(id);
    }

    @Override
    protected CategoryConstract.Persenter initPersenter() {
        return new CategoryPersenter();
    }

    //获取tab数据返回
    @Override
    public void getCategoryTabReturn(CategoryTabBean result) {
        int postion = -1;
        for(int i=0; i<result.getData().getBrotherCategory().size(); i++){
            CategoryTabBean.DataBean.BrotherCategoryBean item = result.getData().getBrotherCategory().get(i);
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(item.getName());
            tab.setTag(item.getId());
            tabLayout.addTab(tab);
            if(result.getData().getCurrentCategory().getId() == item.getId()){
                postion = i;
            }
        }
        txtTitle.setText(result.getData().getCurrentCategory().getName());
        txtDesc.setText(result.getData().getCurrentCategory().getFront_desc());
        //设置选中的tab
        if(postion >= 0){
            tabLayout.getTabAt(postion).select();
            //获取tab对应的列表数据
            persenter.getGoodsList(result.getData().getCurrentCategory().getId(),1,1000);
        }

    }

    //获取到goodlist商品列表数据
    @Override
    public void getGoodsListReturn(CategoryListBean result) {
        list.clear();
        list.addAll(result.getData().getGoodsList());
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int id = (int) tab.getTag();
        persenter.getGoodsList(id,1,1000);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
