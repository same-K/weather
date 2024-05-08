package com.example.weather.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.database.Location;
import com.example.weather.databinding.FragmentHomeBinding;
import com.example.weather.model.SummaryItem;
import com.example.weather.model.SummaryListAdapter;
import com.example.weather.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    public HomeFragment(){
        super(R.layout.fragment_home);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory.from(HomeViewModel.initializer)
        ).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        SummaryListAdapter adapter = new SummaryListAdapter(DIFF_UTIL_ITEM_CALLBACK, listener);
        binding.listLocation.setAdapter(adapter);
        binding.listLocation.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        viewModel.load().observe(getViewLifecycleOwner(), list ->{
            List<SummaryItem> items = new ArrayList<>();
            for (Location location : list) {
                SummaryItem item = new SummaryItem();
                item.cityId = location.cityId;
                item.cityName = location.cityName;
                items.add(item);
            };
            adapter.submitList(items);
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private Listener listener = new Listener() {
        @Override
        public void onExecute(int id) {
            Log.d("HomeFragment","onExecute:"+id);
            Bundle bundle = new Bundle();
            bundle.putInt("CITY_ID", id);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ForecastFragment.class, bundle)
                    .setReorderingAllowed(true)
                    .commit();
        }
    };

    public interface Listener{
        void onExecute(int id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    DiffUtil.ItemCallback<SummaryItem> DIFF_UTIL_ITEM_CALLBACK = new DiffUtil.ItemCallback<SummaryItem>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull SummaryItem oldItem, @NonNull SummaryItem newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SummaryItem oldItem, @NonNull SummaryItem newItem) {
            return false;
        }
    };
}