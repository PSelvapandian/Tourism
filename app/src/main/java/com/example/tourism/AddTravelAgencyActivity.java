package com.example.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.ModelClasses.Room;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityAddTravelInfoBinding;

import java.util.Objects;

public class AddTravelAgencyActivity extends AppCompatActivity
{
    ActivityAddTravelInfoBinding binding;
    RetrofitViewModel retrofitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTravelInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        binding.ivBack.setOnClickListener(v -> finish());

        binding.spinnerFacility.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hotel_facility)));

        binding.btnCreateRooms.setOnClickListener(v -> createRooms());

        binding.btnViewTravelInfo.setOnClickListener(v -> startActivity(new Intent(this, ViewTravelInfo.class)));

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, AddTravelAgencyActivity.this);
            }
        });

        retrofitViewModel.successResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog("Room Created Successfully!", AddTravelAgencyActivity.this);
                clearData();
            }
        });
    }

    private void clearData()
    {
        binding.edtPersonCount.setText("");
    }

    private void createRooms()
    {
        int noOfPerson = !Objects.requireNonNull(binding.edtPersonCount.getText()).toString().isEmpty() ? Integer.parseInt(Objects.requireNonNull(binding.edtPersonCount.getText()).toString()) : 0;
        String facility = binding.spinnerFacility.getSelectedItem().toString();
        if (noOfPerson > 0)
        {
            Room room = new Room();
            room.setFacility(facility);
            room.setNumberOfPerson(noOfPerson);
            binding.constraintProgressBar.setVisibility(View.VISIBLE);
            retrofitViewModel.insertRoom(room);
        }
        else
        {
            MessageDialog.showAlertDialog("Set No of person!", AddTravelAgencyActivity.this);
        }
    }
}
