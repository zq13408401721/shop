package com.study.view.catalog;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.R;
import com.study.base.adapter.BaseAdapter;
import com.study.model.bean.CatalogItem;
import com.study.model.bean.CatalogTabBean;

import java.util.List;

public class CatalogAdapter extends BaseAdapter {

    public CatalogAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.catalog_list_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CatalogItem bean = (CatalogItem) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.img_icon);
        Glide.with(mContext).load(bean.img).into(img);
        TextView txtName = (TextView) holder.getView(R.id.txt_name);
        txtName.setText(bean.name);
    }
}
