package com.example.weather.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.database.Location;
import com.example.weather.databinding.FragmentEditLocationBinding;
import com.example.weather.model.LocationItem;
import com.example.weather.model.LocationListAdapter;
import com.example.weather.viewModel.EditLocationViewModel;
import com.example.weather.viewModel.ForecastViewModel;

import java.util.List;

public class EditLocationFragment extends Fragment {
    private FragmentEditLocationBinding binding;
    private EditLocationViewModel viewModel;

    public EditLocationFragment() {
        super(R.layout.fragment_edit_location);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditLocationBinding.inflate(inflater, container, false);
        binding.listEditLocation.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL)
        );
        LocationListAdapter adapter = new LocationListAdapter(DIFF_UTIL_ITEM_CALLBACK);
        binding.listEditLocation.setAdapter(adapter);
        binding.listEditLocation.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory.from(EditLocationViewModel.initializer)
        ).get(EditLocationViewModel.class);

        viewModel.loadLocations().observe(getViewLifecycleOwner(), result ->{
            Log.d("EditLocationFragment", "getLocation.observe");
            adapter.submitList(result);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.editText.setFilters();
        binding.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = binding.editText.getText().toString();
                if(!text.isEmpty() && actionId == EditorInfo.IME_ACTION_SEARCH){

                }
                return true;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    DiffUtil.ItemCallback<Location> DIFF_UTIL_ITEM_CALLBACK = new DiffUtil.ItemCallback<Location>() {
        @Override
        public boolean areItemsTheSame(@NonNull Location oldItem, @NonNull Location newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Location oldItem, @NonNull Location newItem) {
            return false;
        }
    };
}