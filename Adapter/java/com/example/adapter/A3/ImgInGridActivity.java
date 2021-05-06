package com.example.adapter.A3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.adapter.R;
import com.example.adapter.databinding.ImgInGridActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class ImgInGridActivity extends AppCompatActivity implements GridImgAdapter.onClickListener {
    ImgInGridActivity context;
    ImgInGridActivityBinding binding;
    GridImgAdapter gridImgAdapter;
    List<UserG> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ImgInGridActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        createImgList();
        gridImgAdapter = new GridImgAdapter(imgList, context, this);
        binding.imgInGrid.setLayoutManager(new GridLayoutManager(context, 2));
        binding.imgInGrid.setAdapter(gridImgAdapter);

    }

    private void createImgList() {
        imgList = new ArrayList<>();
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
        imgList.add(new UserG(3, R.drawable.ic_launcher_background));
    }

    @Override
    public void onCardClick(int pos) {
        Toast.makeText(context, "Rating is " + imgList.get(pos).getRating(), Toast.LENGTH_SHORT).show();
    }
}