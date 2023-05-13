package com.example.tourism;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tourism.Adapter.RoomInfoAdapter;
import com.example.tourism.Interfaces.BookingInterface;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityViewTravelInfoBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewTravelInfo extends AppCompatActivity implements BookingInterface
{
    ActivityViewTravelInfoBinding binding;
    RetrofitViewModel retrofitViewModel;
    RoomInfoAdapter roomInfoAdapter;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityViewTravelInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getAllRooms();

        binding.ivBack.setOnClickListener(v -> finish());

        retrofitViewModel.allRooms.observe(this, list -> {
            if (list != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                if (list.size() > 0)
                {
                    setAllRooms(list);
                }
                else
                {
                    setAllRooms(new ArrayList<>());
                }
            }
        });

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, ViewTravelInfo.this);
            }
        });
    }

    private void setAllRooms(List<Room> list)
    {
        roomInfoAdapter = new RoomInfoAdapter();
        binding.recyclerviewTravelInfo.setLayoutManager(new LinearLayoutManager(this));
        roomInfoAdapter.roomList(list, this, this);
        binding.recyclerviewTravelInfo.setAdapter(roomInfoAdapter);
        binding.emptyString.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void getAllRooms()
    {
        binding.constraintProgressBar.setVisibility(View.VISIBLE);
        retrofitViewModel.getAllRooms();
    }

    @Override
    public void bookedHotel(Hotel hotel) {

    }

    @Override
    public void bookedRoom(Room room)
    {
        Gson gson = new Gson();
        String roomJson = gson.toJson(room);
        sharedPreferenceHelper.setRoomInfo(roomJson);
        MessageDialog.showAlertDialog("Your Room Selected Successfully!", ViewTravelInfo.this);
    }

    @Override
    public void bookedRoute(Route route) {

    }
}
