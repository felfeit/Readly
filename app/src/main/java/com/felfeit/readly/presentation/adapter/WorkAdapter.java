package com.felfeit.readly.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.felfeit.readly.databinding.ItemWorksBinding;
import com.felfeit.readly.domain.model.Work;

import java.util.Objects;

public class WorkAdapter extends ListAdapter<Work, WorkAdapter.WorkViewHolder> {


    public WorkAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Work> DIFF_CALLBACK = new DiffUtil.ItemCallback<Work>() {
        @Override
        public boolean areItemsTheSame(@NonNull Work oldItem, @NonNull Work newItem) {
            return Objects.equals(oldItem.getKey(), newItem.getKey()); // Sesuaikan dengan ID unik Work
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Work oldItem, @NonNull Work newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public WorkAdapter.WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorksBinding binding = ItemWorksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.WorkViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class WorkViewHolder extends RecyclerView.ViewHolder {
        private final ItemWorksBinding binding;

        public WorkViewHolder(ItemWorksBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Work work) {
            binding.itemWorksTitle.setText(work.getTitle());
            binding.itemWorksAuthor.setText(work.getAuthorName());
            binding.itemWorksPublishYear.setText("First Published: " + work.getFirstPublishYear());
            binding.itemWorksEditionCount.setText("Edition Count: " + work.getEditionCount());

            if (work.getCoverUrl().equals("https://placehold.co/300x400")) {
                // Tampilkan placeholder
                Glide.with(binding.getRoot().getContext())
                        .load("https://placehold.co/300x400")
                        .into(binding.itemWorksCover);
            } else {
                Glide.with(binding.getRoot().getContext())
                        .load(work.getCoverUrl())
                        .into(binding.itemWorksCover);
            }
        }
    }
}
