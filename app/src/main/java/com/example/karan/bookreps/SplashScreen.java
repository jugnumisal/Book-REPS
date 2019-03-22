package com.example.karan.bookreps;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=2800;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


//        ActionBar actionbar=getActionBar();
//        actionbar.hide();

        new Handler().postDelayed(new Runnable()
        {

            public void run(){
                Intent i=new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(i);

                finish();
            }

        },SPLASH_TIME_OUT);
    }
}
