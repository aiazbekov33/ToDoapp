package com.example.toDoApp.ui.create;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toDoApp.databinding.ItemTaskBinding;
import com.example.toDoApp.ui.home.OnClick;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<TaskModel> list;
    ItemTaskBinding binding;
    OnClick onClick;

    public TaskAdapter(ArrayList<TaskModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setOnClick(OnClick onClick){
        this.onClick = onClick;
    }
    public  void delete(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.onFill(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {
        ItemTaskBinding binding;
        private OnClick onClick;

        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
        public void setOnClick(AdapterView.OnItemLongClickListener onItemLongClickListener){
            this.onClick = onClick;
        }

        public void onFill(TaskModel model) {
            binding.titleTv.setText(model.title);
            binding.timeTv.setText(model.time);
            binding.leftColorV.setBackgroundColor(model.color);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    onClick.onLongClickListener (model, getAdapterPosition());
//                    return false;
                }
            });
            Glide.with(binding.taskIm).load(model.image).centerCrop().into(binding.taskIm);
        }
    }

//    public void setOnClick (OnClick onClick){
//        this.onClick = onClick;
//    }



}