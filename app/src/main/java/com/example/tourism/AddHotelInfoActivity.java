package com.example.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.ModelClasses.Address;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityAddHotelInfoBinding;

import java.util.Objects;

public class AddHotelInfoActivity extends AppCompatActivity
{
    ActivityAddHotelInfoBinding binding;
    RetrofitViewModel retrofitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddHotelInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnCreateHotel.setOnClickListener(v -> createHotel());

        binding.spinnerFacility.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.hotel_facility)));

        binding.spinnerFoodType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.food_type)));

        binding.btnViewHotelInfo.setOnClickListener(v -> startActivity(new Intent(this, ViewHotelInfo.class)));

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, AddHotelInfoActivity.this);
            }
        });

        retrofitViewModel.successResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog("Hotel Created Successfully!", AddHotelInfoActivity.this);
                clearData();
            }
        });
    }

    private void clearData()
    {
        binding.edtHotelName.setText("");
        binding.edtLocation.setText("");
        binding.edtMobileNumber.setText("");
        binding.edtAddress.setText("");
        binding.edtRoomAvailability.setText("");
    }

    private void createHotel()
    {
        String hotelName = !Objects.requireNonNull(binding.edtHotelName.getText()).toString().isEmpty() ? binding.edtHotelName.getText().toString() : "";
        String location = !Objects.requireNonNull(binding.edtLocation.getText()).toString().isEmpty() ? binding.edtLocation.getText().toString() : "";
        String mobile = !Objects.requireNonNull(binding.edtMobileNumber.getText()).toString().isEmpty() ? binding.edtMobileNumber.getText().toString() : "";
        String address = !Objects.requireNonNull(binding.edtAddress.getText()).toString().isEmpty() ? binding.edtAddress.getText().toString() : "";
        int roomAvailability = !Objects.requireNonNull(binding.edtRoomAvailability.getText()).toString().isEmpty() ? Integer.parseInt(binding.edtRoomAvailability.getText().toString()) : 0;
        if (!hotelName.isEmpty() && !mobile.isEmpty() && !address.isEmpty() && !location.isEmpty() && roomAvailability > 0)
        {
            Address addressModel = new Address();
            addressModel.setArea(address);
            binding.constraintProgressBar.setVisibility(View.VISIBLE);
            Hotel hotel = new Hotel();
            hotel.insertHotel(hotelName, location, addressModel, mobile, roomAvailability);
            retrofitViewModel.insertHotel(hotel);
        }
        else
        {
            if (hotelName.isEmpty())
            {
                binding.hotelNameLayout.setError("Hotel name required!");
            }
            if (location.isEmpty())
            {
                binding.locationLayout.setError("Location required!");
            }
            if (mobile.isEmpty())
            {
                binding.mobileNumberLayout.setError("Mobile number required!");
            }
            if (address.isEmpty())
            {
                binding.addressLayout.setError("Address required!");
            }
            if (roomAvailability == 0)
            {
                binding.roomAvailLayout.setError("Room availability required!");
            }
        }
    }
}
