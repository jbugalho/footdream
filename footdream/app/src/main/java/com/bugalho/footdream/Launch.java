package com.bugalho.footdream;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bugalho.footdream.ui.launch.LaunchFragment;
import com.bugalho.footdream.ui.register.RegisterFragment;

public class Launch extends AppCompatActivity {


    private boolean register = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        if (!register) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, LaunchFragment.newInstance())
                        .commitNow();
            }
        }else{
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, RegisterFragment.newInstance())
                        .commitNow();
            }
        }
    }
}
