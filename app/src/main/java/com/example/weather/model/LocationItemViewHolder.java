package com.example.weather.model;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.LayoutEditLocationListItemBinding;

public class LocationItemViewHolder extends RecyclerView.ViewHolder {
    LayoutEditLocationListItemBinding binding;

    public LocationItemViewHolder(LayoutEditLocationListItemBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}
