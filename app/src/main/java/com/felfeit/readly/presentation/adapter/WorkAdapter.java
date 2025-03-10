package com.felfeit.readly.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.felfeit.readly.databinding.ItemWorksBinding;
import com.felfeit.readly.domain.model.Work;

import java.util.Objects;

public class WorkAdapter extends ListAdapter<Work, WorkAdapter.WorkViewHolder> {

    private OnClickListener onItemClickListener;
    private OnRemoveClickListener onRemoveClickListener;

    public WorkAdapter(OnClickListener onItemClickListener, @Nullable OnRemoveClickListener onRemoveClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
        this.onRemoveClickListener = onRemoveClickListener;
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

//    public void setOnClickListener(OnClickListener listener) {
//        this.onItemClickListener = listener;
//    }
//
//    public void setOnRemoveClickListener(OnRemoveClickListener listener) {
//        this.onRemoveClickListener = listener;
//    }

    public interface OnClickListener {
        void onItemClick(Work work);
    }

    public interface OnRemoveClickListener {
        void onRemoveClick(Work work);
    }

    @NonNull
    @Override
    public WorkAdapter.WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorksBinding binding = ItemWorksBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WorkViewHolder(binding, onItemClickListener, onRemoveClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.WorkViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class WorkViewHolder extends RecyclerView.ViewHolder {
        private final ItemWorksBinding binding;
        private final OnClickListener onItemClickListener;
        private final OnRemoveClickListener onRemoveClickListener;

        public WorkViewHolder(ItemWorksBinding binding, OnClickListener itemClickListener, OnRemoveClickListener removeClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onItemClickListener = itemClickListener;
            this.onRemoveClickListener = removeClickListener;
        }

        public void bind(Work work) {
            binding.itemWorksTitle.setText(work.getTitle());
            binding.itemWorksAuthor.setText(work.getAuthorName());
            binding.itemWorksPublishYear.setText("First Published: " + work.getFirstPublishYear());
            binding.itemWorksEditionCount.setText("Edition Count: " + work.getEditionCount());
//            binding.itemWorksRemoveButton.setVisibility(work.isSaved() ? View.VISIBLE : View.GONE);

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

            // Klik item untuk navigasi ke detail
            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(work);
                }
            });

            // Klik tombol Remove
//            binding.itemWorksRemoveButton.setOnClickListener(v -> {
//                if (onRemoveClickListener != null) {
//                    onRemoveClickListener.onRemoveClick(work);
//                }
//            });
        }
    }
}
