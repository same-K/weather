package com.example.weather.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weather.databinding.LayoutCandidateListItemBinding;
import com.example.weather.databinding.LayoutEditLocationListItemBinding;

public class CandidateListAdapter extends ListAdapter<CandidateItem, CandidateItemViewHolder> {

    protected CandidateListAdapter(@NonNull DiffUtil.ItemCallback<CandidateItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CandidateItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCandidateListItemBinding binding
                = LayoutCandidateListItemBinding.inflate(LayoutInflater.from(parent.getContext())
        ,parent, false);
        return new CandidateItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateItemViewHolder holder, int position) {
        CandidateItem item = getItem(position);
        holder.binding.textCandidateCityName.setText(item.name);
    }
}
