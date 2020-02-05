package com.bugalho.footdream.ui.register;

import androidx.lifecycle.ViewModelProviders;

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

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.R;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        Button inserir = root.findViewById(R.id.buttonInsert);
        final EditText nomeClube = root.findViewById(R.id.nomeClube);
        final EditText emailInsert = root.findViewById(R.id.emailInsert);
        final EditText password = root.findViewById(R.id.passowordInsert);

        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.WRITE,
                        "INSERT INTO Clubes (nome_clube,email,password) VALUES ('" + nomeClube.getText() + "','" + emailInsert.getText() + "','" + password.getText() + "')");

                queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
                    @Override
                    public void OnGetResultHandler(Object resultSet) {
                        Integer rowsAffected = (Integer) resultSet;
                        if (rowsAffected > 0)
                            Log.d("user_add", "utilizador adicionado com sucesso");
                    }
                });

            }
        });
        return root;
    }
/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }*/

}
