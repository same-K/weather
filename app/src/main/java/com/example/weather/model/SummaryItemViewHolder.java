package com.example.weather.model;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.LayoutWeatherListItemBinding;

public class SummaryItemViewHolder extends RecyclerView.ViewHolder {

    LayoutWeatherListItemBinding binding;
    public SummaryItemViewHolder(LayoutWeatherListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(SummaryItem item) {
        binding.textCityName.setText(item.cityName);
    }
}
