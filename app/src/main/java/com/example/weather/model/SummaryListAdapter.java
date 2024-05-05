package com.example.weather.model;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weather.databinding.LayoutWeatherListItemBinding;
import com.example.weather.view.HomeFragment;

public class SummaryListAdapter extends ListAdapter<SummaryItem, SummaryItemViewHolder> {

    HomeFragment.Listener listener;

    public SummaryListAdapter(@NonNull DiffUtil.ItemCallback<SummaryItem> diffCallback, HomeFragment.Listener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SummaryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutWeatherListItemBinding binding = LayoutWeatherListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SummaryItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryItemViewHolder holder, int position) {
        SummaryItem item = getItem(position);
        holder.binding.getRoot().setOnClickListener(l -> {
            this.listener.onExecute(item.cityId);
        });
        holder.binding.textCityName.setText(item.cityName);
    }
}
