package com.bugalho.footdream.ui.launch;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bugalho.footdream.R;

public class LaunchFragment extends Fragment {

    private LaunchViewModel launchViewModel;

    public static LaunchFragment newInstance() {
        return new LaunchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.launch_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        launchViewModel = ViewModelProviders.of(this).get(LaunchViewModel.class);
        // TODO: Use the ViewModel
    }

}
