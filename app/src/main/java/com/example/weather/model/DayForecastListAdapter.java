package com.example.weather.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weather.databinding.LayoutForecastByDayBinding;

public class DayForecastListAdapter extends ListAdapter<DayForecastItem, DayForecastItemViewHolder> {

    public DayForecastListAdapter(@NonNull DiffUtil.ItemCallback<DayForecastItem> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public DayForecastItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutForecastByDayBinding binding
                = LayoutForecastByDayBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false);
        return new DayForecastItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DayForecastItemViewHolder holder, int position){
        DayForecastItem item = getItem(position);
        // TODO
    }
}
