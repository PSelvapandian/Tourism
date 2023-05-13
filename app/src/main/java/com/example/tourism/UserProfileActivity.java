package com.example.tourism;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.ModelClasses.CustomUser;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity
{
    ActivityUserProfileBinding binding;
    RetrofitViewModel retrofitViewModel;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getUserData();

        binding.ivBack.setOnClickListener(v -> finish());

        retrofitViewModel.loginResult.observe(this, customUser -> {
            if (customUser != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                setUser(customUser);
            }
        });

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, UserProfileActivity.this);
            }
        });
    }

    private void setUser(CustomUser customUser)
    {
        binding.name.setText(customUser.getName() != null && !customUser.getName().isEmpty() ? customUser.getName() : "");
        binding.email.setText(customUser.getEmail() != null && !customUser.getEmail().isEmpty() ? customUser.getEmail() : "");
        binding.mobile.setText(customUser.getMobile() != null && !customUser.getMobile().isEmpty() ? customUser.getMobile() : "");
        binding.address.setText(customUser.getAddress() != null && customUser.getAddress().getStreet() != null && !customUser.getAddress().getStreet().isEmpty() ? customUser.getAddress().getStreet() : (customUser.getAddress().getArea() != null && !customUser.getAddress().getArea().isEmpty() ? customUser.getAddress().getArea() : ""));
    }

    private void getUserData()
    {
        binding.constraintProgressBar.setVisibility(View.VISIBLE);
        retrofitViewModel.getUserData(sharedPreferenceHelper.getUserId());
    }
}
