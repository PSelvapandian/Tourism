package com.example.tourism;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tourism.Adapter.HotelInfoAdapter;
import com.example.tourism.Interfaces.BookingInterface;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityViewHotelInfoBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewHotelInfo extends AppCompatActivity implements BookingInterface
{
    ActivityViewHotelInfoBinding binding;
    RetrofitViewModel retrofitViewModel;
    HotelInfoAdapter hotelInfoAdapter;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewHotelInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getAllHotels();

        binding.ivBack.setOnClickListener(v -> finish());

        retrofitViewModel.allHotels.observe(this, hotels -> {
            if (hotels != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                if (hotels.size() > 0)
                {
                    setAllHotels(hotels);
                }
                else
                {
                    setAllHotels(new ArrayList<>());
                }
            }
        });

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, ViewHotelInfo.this);
            }
        });
    }

    private void setAllHotels(List<Hotel> hotels)
    {
        hotelInfoAdapter = new HotelInfoAdapter();
        binding.recyclerviewHotelInfo.setLayoutManager(new LinearLayoutManager(this));
        hotelInfoAdapter.hotelList(hotels, this, this);
        binding.recyclerviewHotelInfo.setAdapter(hotelInfoAdapter);
        binding.emptyString.setVisibility(hotels.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void getAllHotels()
    {
        binding.constraintProgressBar.setVisibility(View.VISIBLE);
        retrofitViewModel.getAllHotels();
    }

    @Override
    public void bookedHotel(Hotel hotel)
    {
        Gson gson = new Gson();
        String hotelJson = gson.toJson(hotel);
        sharedPreferenceHelper.setHotelInfo(hotelJson);
        MessageDialog.showAlertDialog("Hotel Selected Successfully!", ViewHotelInfo.this);
    }

    @Override
    public void bookedRoom(Room room) {

    }

    @Override
    public void bookedRoute(Route route)
    {

    }
}
