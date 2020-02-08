package com.study.view.home;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.R;
import com.study.base.adapter.BaseAdapter;
import com.study.model.bean.IndexBean;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    public BrandAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_brand_item;
    }

    @Override
    protected void bindData(BaseAdapter.BaseViewHolder holder, int positon, Object o) {
        IndexBean.DataBean.BrandListBean bean = (IndexBean.DataBean.BrandListBean) mDatas.get(positon);
        TextView txtName = (TextView) holder.getView(R.id.txt_name);
        TextView txtPrice = (TextView) holder.getView(R.id.txt_price);
        ImageView img = (ImageView) holder.getView(R.id.img);
        txtName.setText(bean.getName());
        txtPrice.setText(bean.getFloor_price()+"元起");
        Glide.with(mContext).load(bean.getNew_pic_url()).into(img);
    }

}
