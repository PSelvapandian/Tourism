package com.example.tourism;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding binding;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        binding.cardUserProfile.setOnClickListener(v -> startActivity(new Intent(this, UserProfileActivity.class)));

        binding.cardAddTravelInfo.setOnClickListener(v -> startActivity(new Intent(this, AddTravelAgencyActivity.class)));

        binding.cardAddHotelInfo.setOnClickListener(v -> startActivity(new Intent(this, AddHotelInfoActivity.class)));

        binding.cardAddRouteInfo.setOnClickListener(v -> startActivity(new Intent(this, AddRouteInfoActivity.class)));

        binding.cardUserBookingInfo.setOnClickListener(v -> startActivity(new Intent(this, ViewUserBookingInfo.class)));

        binding.cardLogout.setOnClickListener(v -> logoutAccount());

        binding.cardReport.setOnClickListener(v -> startActivity(new Intent(this, ViewReportActivity.class)));
    }

    private void logoutAccount()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to logout!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            sharedPreferenceHelper.setLoginStatus(false);
            sharedPreferenceHelper.setRole("");
            sharedPreferenceHelper.setUserId("");
            sharedPreferenceHelper.setUserName("");
            startActivity(new Intent(this, LoginScreenActivity.class));
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.create().show();
    }
}