package com.example.tourism;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.ModelClasses.Address;
import com.example.tourism.ModelClasses.CustomUser;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityUserRegistrationBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class UserRegistrationActivity extends AppCompatActivity implements TextWatcher {
    ActivityUserRegistrationBinding binding;
    RetrofitViewModel retrofitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        binding.edtDate.setOnClickListener(v -> getDate());

        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginScreenActivity.class));
            finish();
        });

        binding.btnRegister.setOnClickListener(v -> insertUserData());

        retrofitViewModel.loginResult.observe(this, customUser -> {
            if (customUser != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                Toast.makeText(this, "User created successfully...!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginScreenActivity.class));
                finish();
            }
        });

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, UserRegistrationActivity.this);
            }
        });

        binding.edtEmail.addTextChangedListener(this);
        binding.edtPassword.addTextChangedListener(this);
        binding.edtMobileNumber.addTextChangedListener(this);
    }

    private void insertUserData()
    {
        String name = !Objects.requireNonNull(binding.edtName.getText()).toString().isEmpty() ? binding.edtName.getText().toString() : "";
        String address = !Objects.requireNonNull(binding.edtAddress.getText()).toString().isEmpty() ? binding.edtAddress.getText().toString() : "";
        String email = !Objects.requireNonNull(binding.edtEmail.getText()).toString().isEmpty() ? binding.edtEmail.getText().toString() : "";
        String dob = !Objects.requireNonNull(binding.edtDate.getText()).toString().isEmpty() ? binding.edtDate.getText().toString() : "";
        String contactNumber = !Objects.requireNonNull(binding.edtMobileNumber.getText()).toString().isEmpty() ? binding.edtMobileNumber.getText().toString() : "";
        String password = !Objects.requireNonNull(binding.edtPassword.getText()).toString().isEmpty() ? binding.edtPassword.getText().toString() : "";
        if (!email.isEmpty() && !password.isEmpty())
        {
            if (contactNumber.matches("[6-9][0-9]{9}"))
            {
                binding.constraintProgressBar.setVisibility(View.VISIBLE);
                CustomUser customUser = new CustomUser();
                Address addressModel = new Address();
                addressModel.setArea(address);
                customUser.insertCustomUser(name, email, password, dob, addressModel, contactNumber);
                retrofitViewModel.createUser(customUser);
            }
            else
            {
                binding.mobileNumberLayout.setError("Invalid mobile number!");
            }
        }
        else
        {
            if (binding.edtEmail.getText().toString().isEmpty())
            {
                binding.emailLayout.setError("Email required");
            }
            if (binding.edtPassword.getText().toString().isEmpty())
            {
                binding.passwordLayout.setError("Password required");
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void getDate()
    {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            String dateString = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
            binding.edtDate.setText(dateString);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), LoginScreenActivity.class));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
