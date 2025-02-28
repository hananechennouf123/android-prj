package com.example.oujdashop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000; // 3 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

            Intent intent;
            if (isLoggedIn) {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                android.util.Log.d("SplashScreen", "Redirection vers MainActivity");
            } else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                android.util.Log.d("SplashScreen", "Redirection vers LoginActivity");
            }
            startActivity(intent);
            finish();
        }, SPLASH_TIME);
    }
}
