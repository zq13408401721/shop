package com.study.view.home;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.R;
import com.study.base.BaseFragment;
import com.study.interfaces.home.HomeContract;
import com.study.model.bean.IndexBean;
import com.study.persenter.home.HomePersenter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeContract.View, HomeContract.Persenter> implements HomeContract.View {

    RecyclerView recyclerView;
    TextView txtTitle;

    List<IndexBean.DataBean.BrandListBean> brandList;
    private BrandAdapter brandAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        recyclerView = view.findViewById(R.id.recyclerview);
        txtTitle.setText("品牌制造商直供");
        brandList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        brandAdapter = new BrandAdapter(brandList);
        recyclerView.setAdapter(brandAdapter);
    }

    @Override
    protected void initData() {
        ((HomePersenter)persenter).getHomeData();
    }

    @Override
    protected HomeContract.Persenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void homeDataReturn(IndexBean result) {
        brandAdapter.addData(result.getData().getBrandList());
    }

    @Override
    public void showErrMsg(String err) {

    }
}
