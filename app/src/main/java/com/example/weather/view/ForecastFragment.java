package com.example.weather.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.database.Forecast;
import com.example.weather.databinding.FragmentWeatherBinding;
import com.example.weather.model.DayForecastItem;
import com.example.weather.model.DayForecastListAdapter;
import com.example.weather.model.ForecastRepository;
import com.example.weather.model.HourForecastItem;
import com.example.weather.model.HourForecastListAdapter;
import com.example.weather.model.SummaryItem;
import com.example.weather.viewModel.ForecastViewModel;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastFragment extends Fragment {

    private FragmentWeatherBinding binding;

    private ForecastViewModel viewModel;

    private int cityId;

    public ForecastFragment(){
        super(R.layout.fragment_weather);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory.from(ForecastViewModel.initializer)
        ).get(ForecastViewModel.class);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        activity.addMenuProvider(menuProvider);
    }

    private MenuProvider menuProvider = new MenuProvider() {
        @Override
        public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {

        }

        @Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case android.R.id.home:
                    Log.d("ForecastFragment", "onClickHome");
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, HomeFragment.class, null)
                            .setReorderingAllowed(true)
                            .commit();
                default:
                    break;
            }
            return true;
        }
    };

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Bundle bundle = getArguments();
        this.cityId = bundle.getInt("CITY_ID");
        Log.d("WeatherFragment", "onCreateView:"+cityId);
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        HourForecastListAdapter hourAdapter = new HourForecastListAdapter(DIFF_UTIL_ITEM_CALLBACK);
        DayForecastListAdapter dayAdapter = new DayForecastListAdapter(DIFF_UTIL_ITEM_CALLBACK2);

        binding.listForecastByHour.setAdapter(hourAdapter);
        binding.listForecastByDay.setAdapter(dayAdapter);
        binding.listForecastByHour.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.listForecastByDay.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        viewModel.getLocation(cityId).observe(getViewLifecycleOwner(), result -> {
            binding.textWeatherCityName.setText(result.cityName);
        });
        viewModel.load(cityId).observe(getViewLifecycleOwner(), list -> {
            Log.d("ForecastFragment", "observe");
            List<HourForecastItem> lists = new ArrayList<>();
            List<DayForecastItem> list2 = new ArrayList<>();
            for(int i=0; i<5; i++){
                Forecast forecast = list.get(i);
                HourForecastItem item = new HourForecastItem();
                // TODO
                item.datetime = new SimpleDateFormat("HH:mm").format(new Date(forecast.datetime));
                item.weatherId = forecast.weatherId;
                item.temperature = Math.round(forecast.temperature) + "℃";
                lists.add(item);
                // TODO
                DayForecastItem item2 = new DayForecastItem();
                item2.datetime = new SimpleDateFormat("MM:dd").format(new Date(forecast.datetime));
                item2.weatherId = forecast.weatherId;
                item2.temperature = Math.round(forecast.temperature) + "℃";
                list2.add(item2);
            }
            hourAdapter.submitList(lists);
            dayAdapter.submitList(list2);
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().removeMenuProvider(menuProvider);
        binding = null;
        onLoadFailureListener = null;
    }

    private ForecastRepository.OnFailureListener onLoadFailureListener = new ForecastRepository.OnFailureListener() {
        @Override
        public void onFailure() {
            // TODO Show dialog
        }
    };

    DiffUtil.ItemCallback<DayForecastItem> DIFF_UTIL_ITEM_CALLBACK2 = new DiffUtil.ItemCallback<DayForecastItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull DayForecastItem oldItem, @NonNull DayForecastItem newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DayForecastItem oldItem, @NonNull DayForecastItem newItem) {
            return false;
        }
    };

    DiffUtil.ItemCallback<HourForecastItem> DIFF_UTIL_ITEM_CALLBACK = new DiffUtil.ItemCallback<HourForecastItem>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull HourForecastItem oldItem, @NonNull HourForecastItem newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull HourForecastItem oldItem, @NonNull HourForecastItem newItem) {
            return false;
        }
    };
}