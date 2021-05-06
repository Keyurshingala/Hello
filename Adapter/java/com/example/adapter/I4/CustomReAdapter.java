package com.example.adapter.I4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.databinding.CustomListItemBinding;

import java.util.List;

public class CustomReAdapter extends RecyclerView.Adapter<CustomReAdapter.MyViewHolder> {
    Context context;
    List<CustomListUser> userList;
    onClickListener onClickListener;

    public CustomReAdapter(List<CustomListUser> userList, Context context, onClickListener onClickListener) {
        this.context = context;
        this.userList = userList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomListItemBinding binding = CustomListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CustomListUser user = userList.get(position);

        holder.binding.ivProfile.setImageResource(user.getImage());
        holder.binding.tvName.setText(user.getName());
        holder.binding.tvPosition.setText(user.getPosition());
        holder.binding.tvDate.setText(user.getDate());
        holder.binding.tvEmail.setText(user.getEmail());

        holder.binding.parent.setOnClickListener(v -> {
            onClickListener.onCardClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CustomListItemBinding binding;

        public MyViewHolder(@NonNull CustomListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickListener {
        void onCardClick(int pos);
    }
}
