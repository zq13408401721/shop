package com.study.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.study.R;
import com.study.base.BaseActivity;
import com.study.interfaces.home.HomeContract;
import com.study.model.bean.IndexBean;
import com.study.persenter.home.HomePersenter;
import com.study.view.login.LoginActivity;
import com.study.view.home.*;
import com.study.view.own.OwnFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    HomeFragment homeFragment;
    OwnFragment ownFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigation);

        homeFragment = new HomeFragment();
        ownFragment= new OwnFragment();

        fragmentManager = getSupportFragmentManager();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_home:
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.fragmentBox,homeFragment).commit();
                        break;
                    case R.id.item_own:
                        FragmentTransaction ft1 = fragmentManager.beginTransaction();
                        ft1.replace(R.id.fragmentBox,ownFragment).commit();
                        break;
                }
                return false;
            }
        });
    }
}
