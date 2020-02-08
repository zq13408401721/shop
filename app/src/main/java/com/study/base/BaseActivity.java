package com.study.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.study.interfaces.IBaseView;
import com.study.interfaces.IPersenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V extends IBaseView,P extends IPersenter> extends AppCompatActivity implements IBaseView {

    //获取布局文件
    protected abstract int getLayout();
    //初始化view
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //初始化p层
    protected abstract P initPersenter();

    protected P persenter;

    Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        persenter = initPersenter();
        persenter.attchView(this);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(persenter != null){
            persenter.attchView(this);
        }
    }

    /**
     * 登录失败返回
     * @param err
     */
    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        if(persenter != null){
            persenter.detachView();
        }

    }
}
