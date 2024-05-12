package com.example.weather.model;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.LayoutCandidateListItemBinding;


public class CandidateItemViewHolder extends RecyclerView.ViewHolder {

    LayoutCandidateListItemBinding binding;

    public CandidateItemViewHolder(LayoutCandidateListItemBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}
