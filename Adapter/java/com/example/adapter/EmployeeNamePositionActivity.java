package com.example.adapter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.adapter.databinding.ActivityEmployeeNamePositionBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeNamePositionActivity extends AppCompatActivity {
    EmployeeNamePositionActivity mContext;
    ActivityEmployeeNamePositionBinding binding;
    List<User> userList, filterCityList;
    MyAdapter adapter, filteradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeNamePositionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;

        creatData();
        showData();

        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs != null) {
                    filterCityList = new ArrayList<>();
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).getName().toLowerCase().startsWith(String.valueOf(cs))) {
                            filterCityList.add(userList.get(i));
                        }
                    }
                    filteradapter = new MyAdapter(filterCityList, mContext, null);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(mContext));
                    binding.recycler.setAdapter(filteradapter);
                } else {
                    adapter = new MyAdapter(userList, mContext, null);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(mContext));
                    binding.recycler.setAdapter(adapter);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

    private void showData() {
        adapter = new MyAdapter(userList, mContext, null);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recycler.setAdapter(adapter);
    }

    private void creatData() {
        userList = new ArrayList<>();
        userList.add(new User("Jay", "BlockChain"));
        userList.add(new User("Rahul", "BlockChain"));
        userList.add(new User("Mitesh", "BlockChain"));
        userList.add(new User("Ramesh", "BlockChain"));
        userList.add(new User("Raj", "BlockChain"));
        userList.add(new User("Mohit", "BlockChain"));
        userList.add(new User("Pallav", "BlockChain"));
        userList.add(new User("Jayesh", "BlockChain"));
        userList.add(new User("JayRaj", "BlockChain"));
    }
}