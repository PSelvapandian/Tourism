package com.example.tourism.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.ModelClasses.ReportResponseModel;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.databinding.ListItemReportsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>
{
    Map<String, List<ReportResponseModel>> map;
    List<Map.Entry<String, List<ReportResponseModel>>> entries;
    ReportDataAdapter reportDataAdapter;

    public ReportAdapter()
    {

    }

    public void reportList(Map<String, List<ReportResponseModel>> map)
    {
        this.map = map;
        entries = new ArrayList<>(map.entrySet());
    }

    @NonNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemReportsBinding binding = ListItemReportsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ReportViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportViewHolder holder, int position)
    {
        holder.binding.tvMonth.setText(entries.get(position).getKey());

        String key = entries.get(position).getKey();
        List<ReportResponseModel> responseModelList = map.get(key);
        reportDataAdapter = new ReportDataAdapter();
        holder.binding.recyclerviewTourReportData.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        reportDataAdapter.reportDataList(responseModelList);
        holder.binding.recyclerviewTourReportData.setAdapter(reportDataAdapter);

        holder.binding.cardComplaint.setOnClickListener(v -> {
            if (responseModelList == null || responseModelList.size() == 0)
            {
                MessageDialog.showToast("There is no data found!", holder.itemView.getContext());
            }
            if (holder.binding.recyclerviewTourReportData.getVisibility() == View.VISIBLE)
            {
                holder.binding.recyclerviewTourReportData.setVisibility(View.GONE);
            }
            else
            {
                holder.binding.recyclerviewTourReportData.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return map != null ? map.size() : 0;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder
    {
        ListItemReportsBinding binding;

        public ReportViewHolder(@NonNull ListItemReportsBinding itemView)
        {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
