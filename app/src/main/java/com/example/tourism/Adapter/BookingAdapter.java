package com.example.tourism.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.ModelClasses.Booking;
import com.example.tourism.databinding.ListItemBookingInfoBinding;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder>
{
    List<Booking> list;

    public BookingAdapter()
    {

    }

    public void bookingList(List<Booking> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBookingInfoBinding binding = ListItemBookingInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position)
    {
        holder.binding.userName.setText(list.get(position).getBookerName() != null && !list.get(position).getBookerName().isEmpty() ? list.get(position).getBookerName() : "");
        holder.binding.bookedDate.setText(list.get(position).getBookedDate() != null && !list.get(position).getBookedDate().isEmpty() ? list.get(position).getBookedDate() : "");

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder
    {
        ListItemBookingInfoBinding binding;

        public BookingViewHolder(@NonNull ListItemBookingInfoBinding itemView)
        {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
