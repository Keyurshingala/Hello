package com.example.adapter.A9;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adapter.R;
import com.example.adapter.databinding.ActivityA9Binding;
import com.example.adapter.databinding.ContextCustomDialogBinding;
import com.example.adapter.databinding.NameDisplayDialogBinding;

import java.util.ArrayList;

public class ActivityA9 extends AppCompatActivity {
    ActivityA9 context;
    ActivityA9Binding binding;
    ArrayList<String> nameList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityA9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        nameList = new ArrayList<>();

        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, nameList);
        binding.listView.setAdapter(adapter);

        binding.btnSave.setOnClickListener(v -> {

            String text = binding.etText.getText().toString().trim();
            nameList.add(text);
            adapter.notifyDataSetChanged();

        });

        binding.listView.setOnItemClickListener((parent, view, position, id) -> {

            NameDisplayDialogBinding bind = NameDisplayDialogBinding.inflate(getLayoutInflater());

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(bind.getRoot());

            AlertDialog dialog = builder.create();
            dialog.show();

            bind.tvName.setText(nameList.get(position));

        });

        registerForContextMenu(binding.listView);
    }

    //*****************************************TODO Context Menu*******************************************************//
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_layout, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        ContextCustomDialogBinding dialogBinding = ContextCustomDialogBinding.inflate(getLayoutInflater());

        Dialog dialog = new Dialog(context);

        dialog.setContentView(dialogBinding.getRoot());

        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        switch (item.getItemId()) {
            case R.id.edit:
                dialog.show();
                dialogBinding.etName.setText(nameList.get(info.position));

                dialogBinding.btnDiEdit.setOnClickListener(v -> {
                    String name = dialogBinding.etName.getText().toString().trim();
                    nameList.set(info.position, name);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                });
                break;
            case R.id.delete:
                dialog.dismiss();
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this name")
                        .setPositiveButton("Yes", (dialog1, which) -> {
                            nameList.remove(info.position);
                            adapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("No", null).show();
                break;
            case R.id.exit:
                break;
        }
        return super.onContextItemSelected(item);
    }


}