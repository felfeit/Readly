package com.felfeit.readly.presentation.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.felfeit.readly.presentation.viewmodels.MainViewModel;
import com.felfeit.readly.databinding.ActivityMainBinding;
import com.felfeit.readly.domain.model.Work;
import com.felfeit.readly.presentation.adapter.WorkAdapter;
import com.felfeit.readly.presentation.ui.WorkDetailActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity
        implements WorkAdapter.OnClickListener {

    private ActivityMainBinding binding;
    private WorkAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Setup RecyclerView
        adapter = new WorkAdapter(this, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setItemPrefetchEnabled(true);
        binding.rvWorks.setLayoutManager(layoutManager);
        binding.rvWorks.setAdapter(adapter);


        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup Search Input
        binding.searchInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.searchWorks(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Observe LiveData dari ViewModel
        viewModel.getWorks().observe(this, works -> {
            Log.d("DEBUG", "submitList() Called, Total Items: " + works.size());
            adapter.submitList(works);
            binding.rvWorks.setVisibility(works.isEmpty() ? View.GONE : View.VISIBLE);
            binding.tvEmpty.setVisibility(works.isEmpty() ? View.VISIBLE : View.GONE);
        });

        viewModel.getIsLoading().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.rvWorks.setVisibility(isLoading ? View.GONE : View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
        });

        viewModel.getErrorMessage().observe(this, errorMsg -> {
            if (errorMsg != null && !errorMsg.isEmpty()) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onItemClick(Work work) {
        Intent intent = new Intent(MainActivity.this, WorkDetailActivity.class);
        intent.putExtra("EXTRA_WORK", work); // Mengirim objek Work
        intent.putExtra("EXTRA_OLID", work.getKey());
        startActivity(intent);
    }
}