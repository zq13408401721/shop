package com.study.view.login;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.R;
import com.study.base.BaseActivity;
import com.study.interfaces.IPersenter;
import com.study.interfaces.login.LoginContract;
import com.study.model.bean.LoginInfo;
import com.study.persenter.login.LoginPersenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.img_head)
    ImageView imgHead;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Glide.with(this).load("https://upload-images.jianshu.io/upload_images/18862771-c44cbd4dc0841510?imageMogr2/auto-orient/strip|imageView2/1/w/180/h/128/format/webp").into(imgHead);
        ((LoginPersenter) persenter).login("zq1", "123456");
    }

    @Override
    protected IPersenter initPersenter() {
        return new LoginPersenter();
    }

    /**
     * 登录成功返回
     *
     * @param loginInfo
     */
    @Override
    public void loginReturn(LoginInfo loginInfo) {

    }

    /**
     * 登录失败返回
     *
     * @param err
     */
    @Override
    public void showErrMsg(String err) {

    }

}
