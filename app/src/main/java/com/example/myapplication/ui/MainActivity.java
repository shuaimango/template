package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragment.LoginPhoneFragment;
import com.example.mylibrary.util.Util_view;



/**
 *
 */

public class MainActivity extends FragmentActivity {
    ImageView iv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_splash = findViewById(R.id.iv_splash);
        iv_splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
//        loadRootFragment(R.id.activity_fragment, LoginPhoneFragment.newInstance(null));
                initData();
                finish();
            }
        }, 2000);
    }

    protected void initData() {
    }


}
