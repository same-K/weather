package com.example.weather.model;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weather.database.Location;
import com.example.weather.databinding.LayoutEditLocationListItemBinding;
import com.example.weather.view.EditLocationFragment;

public class LocationListAdapter extends ListAdapter<Location, LocationItemViewHolder> {

    private EditLocationFragment.Listener listener;

    public LocationListAdapter(DiffUtil.ItemCallback<Location> diffCallback, EditLocationFragment.Listener listener){
        super(diffCallback);
        this.listener = listener;

    }

    @NonNull
    @Override
    public LocationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutEditLocationListItemBinding binding = LayoutEditLocationListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LocationItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationItemViewHolder holder, int position){
        Log.d("LocationListAdapter", "onBindViewHolder");
        Location item = getItem(position);
        holder.binding.textLocationCityName.setText((item.cityName));
        holder.binding.imageDeleteLocation.setOnClickListener(l ->{
            this.listener.onExecute(item);
        });
    }
}
