package com.example.tourism.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.ModelClasses.ReportResponseModel;
import com.example.tourism.databinding.ListItemReportDataBinding;

import java.text.DecimalFormat;
import java.util.List;

public class ReportDataAdapter extends RecyclerView.Adapter<ReportDataAdapter.ReportDataViewHolder>
{
    List<ReportResponseModel> list;
    DecimalFormat decimalFormat = new DecimalFormat("##0.00");

    public ReportDataAdapter()
    {

    }

    public void reportDataList(List<ReportResponseModel> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public ReportDataAdapter.ReportDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemReportDataBinding binding = ListItemReportDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReportDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportDataAdapter.ReportDataViewHolder holder, int position)
    {
        String bookingDate = (list.get(position).getBooking() != null && list.get(position).getBooking().getBookedDate() != null && !list.get(position).getBooking().getBookedDate().isEmpty()) ? list.get(position).getBooking().getBookedDate() : "";
        String userName = (list.get(position).getBooking() != null && list.get(position).getBooking().getBookerName() != null && !list.get(position).getBooking().getBookerName().isEmpty()) ? list.get(position).getBooking().getBookerName() : "";
        String from = (list.get(position).getRoute() != null && list.get(position).getRoute().getFromRoute() != null && !list.get(position).getRoute().getFromRoute().isEmpty()) ? list.get(position).getRoute().getFromRoute() : "";
        String pickUpPoint = (list.get(position).getRoute() != null && list.get(position).getRoute().getPickUpPoint() != null && !list.get(position).getRoute().getPickUpPoint().isEmpty()) ? list.get(position).getRoute().getPickUpPoint() : "";
        String dropPoint = (list.get(position).getRoute() != null && list.get(position).getRoute().getDropPoint() != null && !list.get(position).getRoute().getDropPoint().isEmpty()) ? list.get(position).getRoute().getDropPoint() : "";
        String reservationType = (list.get(position).getRoute() != null && list.get(position).getRoute().getReservationType() != null && !list.get(position).getRoute().getReservationType().isEmpty()) ? list.get(position).getRoute().getReservationType() : "";
        String to = (list.get(position).getRoute() != null && list.get(position).getRoute().getToRoute() != null && !list.get(position).getRoute().getToRoute().isEmpty()) ? list.get(position).getRoute().getToRoute() : "";
        String travelAmount = (list.get(position).getRoute() != null && list.get(position).getRoute().getFare() != null) ? decimalFormat.format(list.get(position).getRoute().getFare()) : "00.00";
        holder.binding.bookedDate.setText(bookingDate);
        holder.binding.userName.setText(userName);
        holder.binding.fromPlace.setText(from.concat(" ("+ pickUpPoint + ")"));
        holder.binding.toPlace.setText(to.concat(" ("+ dropPoint + ")"));
        holder.binding.travelAmount.setText(travelAmount);
        holder.binding.reservationType.setText(reservationType);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ReportDataViewHolder extends RecyclerView.ViewHolder
    {
        ListItemReportDataBinding binding;

        public ReportDataViewHolder(@NonNull ListItemReportDataBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
