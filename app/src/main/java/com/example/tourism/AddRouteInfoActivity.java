package com.example.tourism;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.ModelClasses.Route;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityAddRouteInfoBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class AddRouteInfoActivity extends AppCompatActivity implements TextWatcher {
    ActivityAddRouteInfoBinding binding;
    RetrofitViewModel retrofitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRouteInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        binding.ivBack.setOnClickListener(v -> finish());

        binding.spinnerReservationType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.reservation_type)));

        binding.btnViewRouteInfo.setOnClickListener(v -> startActivity(new Intent(this, ViewRouteInfo.class)));

        binding.edtPickUpTime.setOnClickListener(v -> getTimeAndSet(binding.edtPickUpTime));
        binding.edtDropTime.setOnClickListener(v -> getTimeAndSet(binding.edtDropTime));

        binding.btnCreateRoute.setOnClickListener(v -> createRoute());

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, AddRouteInfoActivity.this);
            }
        });

        retrofitViewModel.successResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog("Route Created Successfully!", AddRouteInfoActivity.this);
                clearData();
            }
        });

        binding.edtFrom.addTextChangedListener(this);
        binding.edtToAddress.addTextChangedListener(this);
        binding.edtAmount.addTextChangedListener(this);
        binding.edtPickUpPointAddress.addTextChangedListener(this);
        binding.edtDropPointAddress.addTextChangedListener(this);
        binding.edtPickUpTime.addTextChangedListener(this);
        binding.edtDropTime.addTextChangedListener(this);
    }

    private void clearData()
    {
        binding.edtFrom.setText("");
        binding.edtToAddress.setText("");
        binding.edtAmount.setText("");
        binding.edtPickUpTime.setText("");
        binding.edtPickUpPointAddress.setText("");
        binding.edtDropPointAddress.setText("");
        binding.edtDropTime.setText("");
    }

    private void createRoute()
    {
        String reservationType = binding.spinnerReservationType.getSelectedItem().toString();
        String from = !Objects.requireNonNull(binding.edtFrom.getText()).toString().isEmpty() ? binding.edtFrom.getText().toString() : "";
        String to = !Objects.requireNonNull(binding.edtToAddress.getText()).toString().isEmpty() ? binding.edtToAddress.getText().toString() : "";
        double amount = !Objects.requireNonNull(binding.edtAmount.getText()).toString().isEmpty() ? Double.parseDouble(binding.edtAmount.getText().toString()) : 0;
        String pickUpPoint = !Objects.requireNonNull(binding.edtPickUpPointAddress.getText()).toString().isEmpty() ? binding.edtPickUpPointAddress.getText().toString() : "";
        String pickUpTime = !Objects.requireNonNull(binding.edtPickUpTime.getText()).toString().isEmpty() ? binding.edtPickUpTime.getText().toString() : "";
        String dropPoint = !Objects.requireNonNull(binding.edtDropPointAddress.getText()).toString().isEmpty() ? binding.edtDropPointAddress.getText().toString() : "";
        String dropTime = !Objects.requireNonNull(binding.edtDropTime.getText()).toString().isEmpty() ? binding.edtDropTime.getText().toString() : "";
        if (!from.isEmpty() && !to.isEmpty() && amount > 0 && !pickUpPoint.isEmpty() && !pickUpTime.isEmpty() && !dropPoint.isEmpty() && !dropTime.isEmpty())
        {
            Route route = new Route();
            route.insertRoute(from, to, reservationType, pickUpPoint, pickUpTime, dropPoint, dropTime, amount);
            binding.constraintProgressBar.setVisibility(View.VISIBLE);
            retrofitViewModel.insertRoute(route);
        }
        else
        {
            if (from.isEmpty())
            {
                binding.fromLayout.setError("From location required!");
            }
            if (to.isEmpty())
            {
                binding.toLayout.setError("To location required!");
            }
            if (amount > 0)
            {
                binding.amountLayout.setError("Amount required!");
            }
            if (pickUpPoint.isEmpty())
            {
                binding.pickUpLayout.setError("Pickup point required!");
            }
            if (pickUpTime.isEmpty())
            {
                binding.pickUpTimeLayout.setError("Pickup time required!");
            }
            if (dropPoint.isEmpty())
            {
                binding.dropLayout.setError("Drop point required!");
            }
            if (dropTime.isEmpty())
            {
                binding.dropTimeLayout.setError("Drop time required!");
            }
        }

    }

    private void getTimeAndSet(TextInputEditText edtPickUpTime)
    {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddRouteInfoActivity.this, R.style.timePicker,(timePicker, selectedHour, selectedMinute) -> edtPickUpTime.setText( selectedHour + ":" + selectedMinute), hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

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
        if (s == binding.edtFrom.getEditableText())
        {
            binding.fromLayout.setErrorEnabled(false);
        }
        if (s == binding.edtToAddress.getEditableText())
        {
            binding.toLayout.setErrorEnabled(false);
        }
        if (s == binding.edtAmount.getEditableText())
        {
            binding.amountLayout.setErrorEnabled(false);
        }
        if (s == binding.edtPickUpPointAddress.getEditableText())
        {
            binding.pickUpLayout.setErrorEnabled(false);
        }
        if (s == binding.edtPickUpTime.getEditableText())
        {
            binding.pickUpTimeLayout.setErrorEnabled(false);
        }
        if (s == binding.edtDropTime.getEditableText())
        {
            binding.dropTimeLayout.setErrorEnabled(false);
        }
        if (s == binding.edtDropPointAddress.getEditableText())
        {
            binding.dropLayout.setErrorEnabled(false);
        }
    }
}
