package com.example.weather.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weather.database.Location;
import com.example.weather.databinding.LayoutEditLocationListItemBinding;

public class LocationListAdapter extends ListAdapter<Location, LocationItemViewHolder> {
    public LocationListAdapter(DiffUtil.ItemCallback<Location> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public LocationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutEditLocationListItemBinding binding = LayoutEditLocationListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LocationItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationItemViewHolder holder, int position){
        Location item = getItem(position);
        holder.binding.textLocationCityName.setText((item.cityName));
    }
}
