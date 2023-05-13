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
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.databinding.ListItemAllRoutesBinding;

import java.text.DecimalFormat;
import java.util.List;

public class RouteInfoAdapter extends RecyclerView.Adapter<RouteInfoAdapter.RouteInfoViewHolder>
{
    int selectedPosition = -1;
    List<Route> list;
    BookingInterface bookingInterface;
    RouteInterface routeInterface;
    SharedPreferenceHelper sharedPreferenceHelper;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public RouteInfoAdapter()
    {

    }

    public void insertRoute(List<Route> list, Context context, BookingInterface bookingInterface, RouteInterface routeInterface)
    {
        this.list = list;
        this.bookingInterface = bookingInterface;
        this.routeInterface = routeInterface;
        sharedPreferenceHelper = new SharedPreferenceHelper(context);
    }

    @NonNull
    @Override
    public RouteInfoAdapter.RouteInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemAllRoutesBinding binding = ListItemAllRoutesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RouteInfoViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull RouteInfoViewHolder holder, int position)
    {
        holder.binding.from.setText(list.get(position).getFromRoute() != null && !list.get(position).getFromRoute().isEmpty() ? list.get(position).getFromRoute() : "");
        holder.binding.to.setText(list.get(position).getToRoute() != null && !list.get(position).getToRoute().isEmpty() ? list.get(position).getToRoute() : "");
        holder.binding.amount.setText(list.get(position).getFare() != null ? decimalFormat.format(list.get(position).getFare()) : "00.00");
        holder.binding.pickUpAddress.setText(list.get(position).getPickUpPoint() != null && !list.get(position).getPickUpPoint().isEmpty() ? list.get(position).getPickUpPoint(): "");
        holder.binding.pickUpTime.setText(list.get(position).getPickUpTime() != null && !list.get(position).getPickUpTime().isEmpty() ? list.get(position).getPickUpTime() + " PM" : "");
        holder.binding.dropAddress.setText(list.get(position).getDropPoint() != null && !list.get(position).getDropPoint().isEmpty() ? list.get(position).getDropPoint(): "");
        holder.binding.dropTime.setText(list.get(position).getDropTime() != null && !list.get(position).getDropTime().isEmpty() ? list.get(position).getDropTime() + " AM" : "");

        holder.binding.btnSelect.setOnClickListener(v -> {
            selectedPosition = position;
            bookingInterface.bookedRoute(list.get(position));
            notifyDataSetChanged();
        });

        holder.binding.btnViewMap.setOnClickListener(v -> routeInterface.passRouteDetails(list.get(position)));

        if (sharedPreferenceHelper.getRole().equalsIgnoreCase(RolesEnum.USER.name()))
        {
            if (position == selectedPosition)
            {
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

    public class RouteInfoViewHolder extends RecyclerView.ViewHolder
    {
        ListItemAllRoutesBinding binding;

        public RouteInfoViewHolder(@NonNull ListItemAllRoutesBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface RouteInterface
    {
        void passRouteDetails(Route route);
    }
}
