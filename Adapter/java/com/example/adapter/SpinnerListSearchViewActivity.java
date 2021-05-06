package com.example.adapter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.databinding.ActivitySpinnerListSearchviewBinding;

import java.util.ArrayList;

public class SpinnerListSearchViewActivity extends AppCompatActivity {

    ArrayList<String> citylist = new ArrayList<>();
    ArrayList<String> filtercitylist;

    ActivitySpinnerListSearchviewBinding binding;
    ArrayAdapter spin, filteradapter,listadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerListSearchviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createData();
        
        spin = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, citylist);
        binding.spin.setAdapter(spin);

        binding.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.tv.setText(citylist.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, citylist);
        binding.listCity.setAdapter(listadapter);
        binding.listCity.setOnItemClickListener((adapterView, view, position, l) ->
                binding.tv.setText(citylist.get(position))

        );

        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if (cs != null) {
                    filtercitylist = new ArrayList<>();
                    for (int i = 0; i <citylist.size(); i++) {
                        if (citylist.get(i).toLowerCase().startsWith(String.valueOf(cs))) {
                            filtercitylist.add(citylist.get(i));
                        }
                    }
                    filteradapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, filtercitylist);
                    binding.listCity.setAdapter(filteradapter);
                    binding.listCity.setOnItemClickListener((adapterView, view, position, l) -> binding.tv.setText(filtercitylist.get(position)));
                } else {
                    listadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, citylist);
                    binding.listCity.setAdapter(listadapter);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


    }

    private void createData() {
        citylist.add("Ahmedabad");
        citylist.add("Amreli district");
        citylist.add("Anand");
        citylist.add("Banaskantha");
        citylist.add("Bharuch");
        citylist.add("Bhavnagar");
        citylist.add("Dahod");
        citylist.add("The Dangs");
        citylist.add("Gandhinagar");
        citylist.add("Jamnagar");
        citylist.add("Junagadh");
        citylist.add("Kutch");
        citylist.add("Kheda");
        citylist.add("Mehsana");
        citylist.add("Narmada");
        citylist.add("Navsari");
        citylist.add("Patan");
        citylist.add("Panchmahal");
        citylist.add("Porbandar");
        citylist.add("Rajkot");
        citylist.add("Sabarkantha");
        citylist.add("Surendranagar");
        citylist.add("Surat");
        citylist.add("Vyara");
        citylist.add("Vadodara");
        citylist.add("Valsad");
    }
}