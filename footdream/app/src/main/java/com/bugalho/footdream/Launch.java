package com.bugalho.footdream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bugalho.footdream.ui.launch.LaunchFragment;

public class Launch extends AppCompatActivity {


    private boolean register = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LaunchFragment.newInstance())
                    .commitNow();
        }
    }
}