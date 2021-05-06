package com.example.adapter.A3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.databinding.ImgInGrideItemBinding;

import java.util.List;

public class GridImgAdapter extends RecyclerView.Adapter<GridImgAdapter.MyViewHolder> {
    Context context;
    List<UserG> imgList;
    onClickListener onClickListener;

    public GridImgAdapter(List<UserG> imgList, Context context, onClickListener onClickListener) {
        this.context = context;
        this.imgList = imgList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImgInGrideItemBinding binding = ImgInGrideItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.gridImg.setImageResource(imgList.get(position).getImg());
        holder.binding.rating.setRating(imgList.get(position).getRating());

        holder.binding.parent.setOnClickListener(v -> {
            onClickListener.onCardClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImgInGrideItemBinding binding;

        public MyViewHolder(@NonNull ImgInGrideItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickListener {
        void onCardClick(int pos);
    }
}
