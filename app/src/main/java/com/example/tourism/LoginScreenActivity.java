package com.example.tourism;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.Enum.RolesEnum;
import com.example.tourism.ModelClasses.CustomUser;
import com.example.tourism.ModelClasses.LoginRequestBuilder;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityLoginScreenBinding;

import java.util.Locale;
import java.util.Objects;

public class LoginScreenActivity extends AppCompatActivity implements TextWatcher
{
    ActivityLoginScreenBinding binding;
    SharedPreferenceHelper sharedPreferenceHelper;
    RetrofitViewModel retrofitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(LoginScreenActivity.this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        binding.btnLogin.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        binding.btnLogin.setOnClickListener(v -> loginAccount());

        binding.btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, UserRegistrationActivity.class));
            finish();
        });

        binding.edtUserName.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEND)
            {
                loginAccount();
                return true;
            }
            return false;
        });

        binding.edtPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEND)
            {
                loginAccount();
                return true;
            }
            return false;
        });

        retrofitViewModel.loginResult.observe(this, customUser -> {
            if (customUser != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                Toast.makeText(this, "User created successfully...!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, UserHomeScreenActivity.class));
                finish();
            }
        });

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, LoginScreenActivity.this);
            }
        });

        binding.edtUserName.addTextChangedListener(this);
        binding.edtPassword.addTextChangedListener(this);
    }

    private void loginAccount()
    {
        String userName = !Objects.requireNonNull(binding.edtUserName.getText()).toString().isEmpty() ? binding.edtUserName.getText().toString().trim() : "";
        String password = !Objects.requireNonNull(binding.edtPassword.getText()).toString().isEmpty() ? binding.edtPassword.getText().toString().trim() : "";
        if (!userName.isEmpty() && !password.isEmpty())
        {
            if (userName.toLowerCase(Locale.ROOT).equals("admin") && password.toLowerCase(Locale.ROOT).equals("admin"))
            {
                sharedPreferenceHelper.setLoginStatus(false);
                sharedPreferenceHelper.setRole(RolesEnum.ADMIN.name());
                binding.constraintProgressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> {
                    binding.constraintProgressBar.setVisibility(View.GONE);
                    startActivity(new Intent(LoginScreenActivity.this, MainActivity.class));
                    finish();
                }, 4000);
            }
            else
            {
                binding.constraintProgressBar.setVisibility(View.VISIBLE);
                LoginRequestBuilder loginRequestBuilder = new LoginRequestBuilder();
                loginRequestBuilder.setUserName(userName);
                loginRequestBuilder.setPassword(password);
                retrofitViewModel.loginAccount(loginRequestBuilder);
            }
        }
        else
        {
            if (binding.edtUserName.getText().toString().isEmpty())
            {
                binding.userNameLayout.setError("Username required");
            }
            if (binding.edtPassword.getText().toString().isEmpty())
            {
                binding.passwordLayout.setError("Password required");
            }
        }
    }

    private void showWarningDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreenActivity.this);
        builder.setMessage("Invalid username password, try again!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> dialog.cancel());
        builder.create().show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        if (s == binding.edtUserName.getEditableText())
        {
            binding.userNameLayout.setErrorEnabled(false);
        }
        if (s == binding.edtPassword.getEditableText())
        {
            binding.passwordLayout.setErrorEnabled(false);
        }
    }
}
