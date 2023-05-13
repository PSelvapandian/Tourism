package com.example.tourism.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.Enum.RolesEnum;
import com.example.tourism.Interfaces.BookingInterface;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewHotelInfo;
import com.example.tourism.databinding.ListItemAllHotelsBinding;

import java.util.List;

public class HotelInfoAdapter extends RecyclerView.Adapter<HotelInfoAdapter.HotelInfoViewHolder>
{
    int selectedPosition = -1;
    List<Hotel> hotelList;
    BookingInterface bookingInterface;
    SharedPreferenceHelper sharedPreferenceHelper;

    public HotelInfoAdapter()
    {

    }

    public void hotelList(List<Hotel> hotelList, Context context, BookingInterface bookingInterface)
    {
        this.hotelList = hotelList;
        this.bookingInterface = bookingInterface;
        sharedPreferenceHelper = new SharedPreferenceHelper(context);
    }

    @NonNull
    @Override
    public HotelInfoAdapter.HotelInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemAllHotelsBinding binding = ListItemAllHotelsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HotelInfoViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull HotelInfoAdapter.HotelInfoViewHolder holder, int position)
    {
        holder.binding.hotelName.setText(hotelList.get(position).getHotelName() != null && !hotelList.get(position).getHotelName().isEmpty() ? hotelList.get(position).getHotelName() : "");
        holder.binding.address.setText(hotelList.get(position).getAddress() != null && hotelList.get(position).getAddress().getArea() != null && !hotelList.get(position).getAddress().getArea().isEmpty() ? hotelList.get(position).getAddress().getArea() : "");
        holder.binding.mobileNumber.setText(hotelList.get(position).getHotelMobile() != null && !hotelList.get(position).getHotelMobile().isEmpty() ? hotelList.get(position).getHotelMobile(): "");

        holder.binding.btnSelect.setOnClickListener(v -> {
            selectedPosition = position;
            bookingInterface.bookedHotel(hotelList.get(position));
            notifyDataSetChanged();
        });

        if (sharedPreferenceHelper.getRole().equalsIgnoreCase(RolesEnum.USER.name()))
        {
            if (position == selectedPosition) {
                holder.binding.btnSelect.setVisibility(View.GONE);
            }
            else
            {
                holder.binding.btnSelect.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            holder.binding.btnSelect.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return hotelList != null ? hotelList.size() : 0;
    }

    public class HotelInfoViewHolder extends RecyclerView.ViewHolder
    {
        ListItemAllHotelsBinding binding;

        public HotelInfoViewHolder(@NonNull ListItemAllHotelsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
