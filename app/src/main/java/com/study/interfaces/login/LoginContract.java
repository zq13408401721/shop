package com.study.interfaces.login;

import com.study.interfaces.IBaseView;
import com.study.interfaces.IPersenter;
import com.study.model.bean.LoginInfo;

/**
 * 登陆契约类
 */
public interface LoginContract {

    //登录返回的接口定义
    interface View extends IBaseView{
        void loginReturn(LoginInfo msg);
    }

    //登录的p层接口
    interface Persenter extends IPersenter<View> {
        void login(String username,String pw);
    }


}
