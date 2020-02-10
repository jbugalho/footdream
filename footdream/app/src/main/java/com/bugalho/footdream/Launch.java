package com.bugalho.footdream;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bugalho.footdream.ui.launch.LaunchFragment;
import com.bugalho.footdream.ui.register.RegisterFragment;

public class Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up notitle
        setContentView(R.layout.launch_activity);
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, LaunchFragment.newInstance())
                        .commitNow();
            }

    }
}
