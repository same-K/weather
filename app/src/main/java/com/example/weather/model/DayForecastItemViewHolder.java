package com.example.weather.model;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.LayoutForecastByDayBinding;

public class DayForecastItemViewHolder extends RecyclerView.ViewHolder {
    LayoutForecastByDayBinding binding;

    public DayForecastItemViewHolder(LayoutForecastByDayBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}
