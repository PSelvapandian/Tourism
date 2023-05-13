package com.example.tourism;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tourism.Adapter.BookingAdapter;
import com.example.tourism.Adapter.HotelInfoAdapter;
import com.example.tourism.Enum.RolesEnum;
import com.example.tourism.ModelClasses.Booking;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityUserBookingInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewUserBookingInfo extends AppCompatActivity
{
    ActivityUserBookingInfoBinding binding;
    RetrofitViewModel retrofitViewModel;
    SharedPreferenceHelper sharedPreferenceHelper;

    BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBookingInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getAllBookings();

        binding.ivBack.setOnClickListener(v -> finish());

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, ViewUserBookingInfo.this);
            }
        });

        retrofitViewModel.allBookingLiveData.observe(this, list -> {
            if (list != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                setBookingData(list.size() > 0 ? list : new ArrayList<>());
            }
        });

    }

    private void setBookingData(List<Booking> list)
    {
        bookingAdapter = new BookingAdapter();
        binding.recyclerviewUserBookingInfo.setLayoutManager(new LinearLayoutManager(this));
        bookingAdapter.bookingList(list);
        binding.recyclerviewUserBookingInfo.setAdapter(bookingAdapter);
        binding.emptyString.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void getAllBookings()
    {
        binding.constraintProgressBar.setVisibility(View.VISIBLE);
        if (sharedPreferenceHelper.getRole().equalsIgnoreCase(RolesEnum.ADMIN.name()))
        {
            retrofitViewModel.getAllBookings();
        }
        else
        {
            String userId = sharedPreferenceHelper.getUserId() != null && !sharedPreferenceHelper.getUserId().isEmpty() ? sharedPreferenceHelper.getUserId() : "";
            retrofitViewModel.getAllBookingsByUserId(userId);
        }
    }
}
