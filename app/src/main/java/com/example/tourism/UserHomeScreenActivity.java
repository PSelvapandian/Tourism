package com.example.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.databinding.ActivityUserHomeBinding;

public class UserHomeScreenActivity extends AppCompatActivity
{
    ActivityUserHomeBinding binding;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        binding.btnViewHotels.setOnClickListener(v -> startActivity(new Intent(this, ViewHotelInfo.class)));

        binding.btnViewRoute.setOnClickListener(v -> startActivity(new Intent(this, ViewRouteInfo.class)));

        binding.cardUserProfile.setOnClickListener(v -> startActivity(new Intent(this, UserProfileActivity.class)));

        binding.cardViewRooms.setOnClickListener(v -> startActivity(new Intent(this, ViewTravelInfo.class)));

        binding.btnBookYourTour.setOnClickListener(v -> startActivity(new Intent(this, UserBookingActivity.class)));

        binding.cardViewBooking.setOnClickListener(v -> startActivity(new Intent(this, ViewUserBookingInfo.class)));

        binding.cardLogout.setOnClickListener(v -> logoutAccount());
    }

    private void logoutAccount()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserHomeScreenActivity.this);
        builder.setMessage("Do you want to logout!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            sharedPreferenceHelper.setUserId("");
            sharedPreferenceHelper.setUserName("");
            sharedPreferenceHelper.setLoginStatus(false);
            sharedPreferenceHelper.setRole("");
            startActivity(new Intent(this, LoginScreenActivity.class));
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.create().show();
    }
}
