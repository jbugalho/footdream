package com.bugalho.footdream.ui.register;

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

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.R;
import com.bugalho.footdream.RegisterActivities.ClubeRegister;
import com.bugalho.footdream.RegisterActivities.JogadorRegister;
import com.bugalho.footdream.RegisterActivities.TreinadorRegister;

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
        View root = inflater.inflate(R.layout.register_fragment, container, false);

        Button treinador = root.findViewById(R.id.Treinador);
        Button jogador = root.findViewById(R.id.Jogador);
        Button clube = root.findViewById(R.id.Clube);

        treinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TreinadorRegister.class);
                startActivity(intent);
            }
        });
        jogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JogadorRegister.class);
                startActivity(intent);
            }
        });
        clube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClubeRegister.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        return root;
    }
}
