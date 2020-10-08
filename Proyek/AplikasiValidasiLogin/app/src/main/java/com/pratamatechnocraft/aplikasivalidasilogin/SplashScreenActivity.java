package com.pratamatechnocraft.aplikasivalidasilogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin()) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }

    boolean isLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
   }
}