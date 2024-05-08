package com.example.weather.model;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.LayoutForecastByHourBinding;

public class HourForecastItemViewHolder extends RecyclerView.ViewHolder {

    LayoutForecastByHourBinding binding;

    public HourForecastItemViewHolder(LayoutForecastByHourBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
