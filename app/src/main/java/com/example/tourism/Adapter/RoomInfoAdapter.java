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
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.databinding.ListItemsAllRoomsBinding;

import java.util.List;

public class RoomInfoAdapter extends RecyclerView.Adapter<RoomInfoAdapter.RoomInfoViewHolder>
{
    int selectedPosition = -1;
    List<Room> list;
    BookingInterface bookingInterface;
    SharedPreferenceHelper sharedPreferenceHelper;

    public RoomInfoAdapter()
    {

    }

    public void roomList(List<Room> list, Context context, BookingInterface bookingInterface)
    {
        this.list = list;
        this.bookingInterface = bookingInterface;
        sharedPreferenceHelper = new SharedPreferenceHelper(context);
    }

    @NonNull
    @Override
    public RoomInfoAdapter.RoomInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemsAllRoomsBinding binding = ListItemsAllRoomsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RoomInfoViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull RoomInfoAdapter.RoomInfoViewHolder holder, int position)
    {
        holder.binding.roomFacility.setText(list.get(position).getFacility() != null && !list.get(position).getFacility().isEmpty() ? list.get(position).getFacility() : "NON AC");
        holder.binding.personCount.setText(list.get(position).getNumberOfPerson() != null ? String.valueOf(list.get(position).getNumberOfPerson()) : "0");

        holder.binding.btnSelect.setOnClickListener(v -> {
            selectedPosition = position;
            bookingInterface.bookedRoom(list.get(position));
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
        return list != null ? list.size() : 0;
    }

    public class RoomInfoViewHolder extends RecyclerView.ViewHolder
    {
        ListItemsAllRoomsBinding binding;

        public RoomInfoViewHolder(@NonNull ListItemsAllRoomsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
