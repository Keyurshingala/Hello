package com.example.adapter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.A3.ImgInGridActivity;
import com.example.adapter.A9.ActivityA9;
import com.example.adapter.I4.CustomListActivity;
import com.example.adapter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainActivity mContext;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        binding.btnI1I2.setOnClickListener(v -> {
            startActivity(new Intent(mContext, SpinnerListSearchViewActivity.class));
        });
        binding.btnI3.setOnClickListener(v -> {
            startActivity(new Intent(mContext, EmployeeNamePositionActivity.class));
        });
        binding.btnI4.setOnClickListener(v -> {
            startActivity(new Intent(mContext, CustomListActivity.class));
        });
        binding.btnA3.setOnClickListener(v -> {
            startActivity(new Intent(mContext, ImgInGridActivity.class));
        });
        binding.btnA9.setOnClickListener(v -> {
            startActivity(new Intent(mContext, ActivityA9.class));
        });

    }
}