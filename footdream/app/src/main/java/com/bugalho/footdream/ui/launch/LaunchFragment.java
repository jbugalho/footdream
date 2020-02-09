package com.bugalho.footdream.ui.launch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.Launch;
import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;
import com.bugalho.footdream.ui.register.RegisterFragment;
import com.bugalho.footdream.ui.register.RegisterViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.transform.Result;

public class LaunchFragment extends Fragment {

    private LaunchViewModel launchViewModel;

    public static LaunchFragment newInstance() {
        return new LaunchFragment();
    }

    private EditText email;
    private EditText passwordUsada;
    private TextView texto;
    private Button registar;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        launchViewModel = ViewModelProviders.of(this).get(LaunchViewModel.class);
        View root = inflater.inflate(R.layout.launch_fragment, container, false);

        Button loginButton = root.findViewById(R.id.login);
        email = root.findViewById(R.id.email);
        passwordUsada = root.findViewById(R.id.password);
        texto = root.findViewById(R.id.message);
        registar = root.findViewById(R.id.register);
        emailLayout = root.findViewById(R.id.LayoutEmail);
        passwordLayout = root.findViewById(R.id.LayoutPassword);

        registar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((AppCompatActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new RegisterFragment()).commit();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clubeLogin();


            }
        });
        return root;
    }

    private void clubeLogin(){
        DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Clubes WHERE email='" + email.getText() + "' and password='" + passwordUsada.getText() + "';");
        Log.d("onClick", "onClick: teste");
        queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while (true) {
                    try {
                        if (!resultSet.next()){

                            emailLayout.setError("E-mail ou Password errados!");
                            passwordLayout.setError("E-mail ou Password errados!");
                            break;
                        }

                        if(resultSet.getString("password").equals(passwordUsada.getText().toString())){
                            Log.d("login", "OnGetResultHandler: " + resultSet.getString("password"));
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            break;
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

    }
}
