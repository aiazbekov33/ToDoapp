package com.example.toDoApp.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.toDoApp.interf.OnItemClickListener;
import com.example.toDoApp.ui.create.TaskAdapter;
import com.example.toDoApp.ui.create.TaskModel;
import com.example.toDoApp.utils.App;
import com.example.toDoApp.R;
import com.example.toDoApp.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    ArrayList<TaskModel> list = new ArrayList<>();
    private TaskAdapter adapter = new TaskAdapter(getDataFromDataBase());
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController     = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.createTaskFragment);
            }
        });

        initAdapter();
        onLongDelete();
    }

    private void onLongDelete() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(TaskModel taskModel,int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Выбери действие");
                alertDialog.setMessage("Вы хотите удалить?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().taskDao().delete(taskModel);
                        adapter.delete(getDataFromDataBase());
                        Toast.makeText(getActivity(), "Удалено", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });

    }

    private ArrayList<TaskModel> getDataFromDataBase(){
        return (ArrayList<TaskModel>) App.getInstance().taskDao().getAll();
    }

    private void initAdapter() {
        binding.taskRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}