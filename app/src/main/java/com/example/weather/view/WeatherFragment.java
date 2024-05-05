package com.example.weather.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.weather.BuildConfig;
import com.example.weather.R;
import com.example.weather.databinding.FragmentWeatherBinding;
import com.example.weather.model.OpenWeatherApi;
import com.example.weather.model.OpenWeatherService;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OpenWeatherApi api = OpenWeatherService.getService();

        api.getForecast("Tokyo", "metric", "ja", BuildConfig.API_KEY);
/*
        binding.buttonSecond.setOnClickListener(v ->
                NavHostFragment.findNavController(WeatherFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );

 */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}