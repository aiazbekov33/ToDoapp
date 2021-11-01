package com.example.toDoApp.interf;

import com.example.toDoApp.ui.create.TaskModel;

public interface OnItemClickListener {
    void onItemClick(TaskModel taskModel,int position);
}
