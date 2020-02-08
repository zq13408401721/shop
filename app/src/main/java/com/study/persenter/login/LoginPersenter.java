package com.study.persenter.login;

import com.study.component.CommonSubscriber;
import com.study.interfaces.login.LoginContract;
import com.study.model.HttpManager;
import com.study.model.bean.LoginInfo;
import com.study.persenter.BasePersenter;
import com.study.utils.RxUtils;

public class LoginPersenter extends BasePersenter<LoginContract.View> implements LoginContract.Persenter{
    /**
     * p层业务逻辑处理
     * 登录
     * @param username
     * @param pw
     */
    @Override
    public void login(String username, String pw) {
        addSubscribe(HttpManager.getTestApi().login(username,pw)
        .compose(RxUtils.<LoginInfo>rxScheduler())
        .subscribeWith(new CommonSubscriber<LoginInfo>(mView){

            @Override
            public void onNext(LoginInfo loginInfo) {
                mView.loginReturn(loginInfo);
            }
        }));
    }

}
