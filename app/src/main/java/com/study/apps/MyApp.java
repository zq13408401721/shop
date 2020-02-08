package com.study.apps;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.bumptech.glide.Glide;
import com.study.dao.DaoMaster;
import com.study.dao.DaoSession;
import com.study.utils.MyDaoMaster;

import butterknife.OnClick;

public class MyApp extends Application {

    private static String DB_NAME = "1901A";


    public static MyApp myApp;

    private SQLiteDatabase sqLiteDatabase;
    private MyDaoMaster myDaoMaster;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        initDB();


    }

    private void initDB(){
        myDaoMaster = new MyDaoMaster(this,DB_NAME);
        sqLiteDatabase = myDaoMaster.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }


}
