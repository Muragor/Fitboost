package com.example.fitboost.bottom_nav.vyzov;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitboost.databinding.FragmentHomeBinding;
import com.example.fitboost.databinding.FragmentVyzovBinding;

public class VyzovFragment extends Fragment {
    private FragmentVyzovBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVyzovBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}
