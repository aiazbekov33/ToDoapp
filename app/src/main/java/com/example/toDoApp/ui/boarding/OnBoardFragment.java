package com.example.toDoApp.ui.boarding;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.toDoApp.R;
import com.example.toDoApp.utils.Constants;
import com.example.toDoApp.MainActivity;
import com.example.toDoApp.databinding.FragmentOnBoardBinding;

import org.jetbrains.annotations.NotNull;


public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPositionFromViewPagerAdapter();
    }

    private void getPositionFromViewPagerAdapter() {
        if (getArguments()!=null) {
            int position = getArguments().getInt(Constants.FRAGMENT_POSITION);
            switch (position){
                case 0:
                    binding.titleTv.setText("Приветствую");
                    binding.boardImage.setAnimation("move.json");
                    binding.skipBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Animation logoMoveAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.baunce_animation);
                            binding.skipBtn.startAnimation(logoMoveAnimation);
                        }
                    });
                    break;
                case 1:
                    binding.titleTv.setText("Второй экран");
                    binding.boardImage.setAnimation("diagram.json");
                    binding.skipBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Animation logoMoveAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.baunce_animation);
                            binding.skipBtn.startAnimation(logoMoveAnimation);
                        }
                    });
                    break;
                case 2:
                    binding.titleTv.setText("Третий экран");
                    binding.boardImage.setAnimation("analyse.json");
                    binding.startBtn.setVisibility(View.VISIBLE);
                    binding.startBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.IS_SHOW_FILE, Context.MODE_PRIVATE);
                            sharedPreferences.edit().putBoolean(Constants.IS_SHOW,true).apply();
                            startActivity(new Intent(requireContext(), MainActivity.class));
//                            requireActivity().finish();

                            Animation logoMoveAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.baunce_animation);
                            binding.startBtn.startAnimation(logoMoveAnimation);
                        }
                    });
                    break;
            }
        }
    }
}