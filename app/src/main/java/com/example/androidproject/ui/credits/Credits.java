package com.example.androidproject.ui.credits;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.databinding.FragmentAddContactBinding;
import com.example.androidproject.databinding.FragmentCreditsBinding;
import com.example.androidproject.ui.credits.CreditsViewModel;
import com.example.androidproject.R;

public class Credits extends Fragment {

    private CreditsViewModel mViewModel;

    public static Credits newInstance() {
        return new Credits();
    }

    private FragmentCreditsBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        binding = FragmentCreditsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        //now you can use findViewById using the following syntax
        //TextView textView = root.findViewById(R.id.textView);



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreditsViewModel.class);
        // TODO: Use the ViewModel
    }

}