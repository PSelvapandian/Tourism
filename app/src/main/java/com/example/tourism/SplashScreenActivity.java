package com.example.tourism;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.databinding.ActivitySpalshScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity
{
    ActivitySpalshScreenBinding binding;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpalshScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        clearData();

        if (sharedPreferenceHelper.getLoginStatus())
        {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreenActivity.this, UserHomeScreenActivity.class));
                finish();
            }, 4000);
        }
        else
        {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreenActivity.this, LoginScreenActivity.class));
                finish();
            }, 4000);
        }
    }

    private void clearData()
    {
        sharedPreferenceHelper.setHotelInfo("");
        sharedPreferenceHelper.setRouteInfo("");
        sharedPreferenceHelper.setRoomInfo("");
    }
}
