package com.example.canyou.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.canyou.IntroPref;
import com.example.canyou.PreferenceManager;
import com.example.canyou.R;

public class SplashActivity extends AppCompatActivity {
    private PreferenceManager preferenceManager;
    private IntroPref introPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        preferenceManager = new PreferenceManager(this);
        introPref = new IntroPref(this);
new Handler()
        .postDelayed(new Runnable() {
            @Override
            public void run() {
                if (introPref.isFirstTimeLaunch()) {
                    // First-time usage
                    changeActivity( WelcomeSliderActivity.class);
                } else if (preferenceManager.getUser() != null) {
                    // User is logged in
                    changeActivity(MainActivity.class);
                } else {
                    // User is not logged in
                    changeActivity(LoginActivity.class);
                }
            }
        },500);

    }


    public void changeActivity(Class activity) {
        Intent intent=new Intent(SplashActivity.this, activity);
        startActivity(intent);
        finish();
    }
}