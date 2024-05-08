package com.example.weather.model;

import android.content.res.Resources;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weather.R;
import com.example.weather.databinding.LayoutForecastByHourBinding;
import com.example.weather.view.HomeFragment;

public class HourForecastListAdapter extends ListAdapter<HourForecastItem, HourForecastItemViewHolder> {



    public HourForecastListAdapter(@NonNull DiffUtil.ItemCallback<HourForecastItem> diffCallback) {
        super(diffCallback);
    //    this.listener = listener;
    }

    @NonNull
    @Override
    public HourForecastItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutForecastByHourBinding binding
                = LayoutForecastByHourBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false);
        return new HourForecastItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HourForecastItemViewHolder holder, int position) {
        HourForecastItem item = getItem(position);
        Log.d("Adapter", "onBindiViewHolder");
        holder.binding.textHourForecastTime.setText(item.datetime);
        holder.binding.imageHourForecastIcon.setImageDrawable(WeatherIconUtil.LoadDrawable(holder.itemView.getContext(), item.weatherId));
        holder.binding.textHourForecastTemperature.setText(item.temperature);
    }
}
