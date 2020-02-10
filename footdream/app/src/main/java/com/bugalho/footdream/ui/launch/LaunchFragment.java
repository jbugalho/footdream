package com.bugalho.footdream.ui.launch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.Launch;
import com.bugalho.footdream.LoadingDialog;
import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;
import com.bugalho.footdream.User;
import com.bugalho.footdream.UserType;
import com.bugalho.footdream.ui.register.RegisterFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.transform.Result;

public class LaunchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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
    private LoadingDialog loadingDialog;
    private Spinner spinner;
    private String selecionado;

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
        loadingDialog = new LoadingDialog(getActivity());
        spinner = root.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);


        registar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment()).commit();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(selecionado){
                    case "Clube": clubeLogin(); break;
                    case "Jogador": jogadorLogin(); break;
                    case "Treinador": treinadorLogin(); break;
                    default:
                        Toast.makeText(getContext(), "Selecione um tipo de Login",Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    private void clubeLogin(){
        loadingDialog.startLoadingAnimation();
        DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Clubes WHERE email='" + email.getText() + "' and password='" + passwordUsada.getText() + "';");
        Log.d("onClick", "onClick: teste");
        queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while (true) {
                    try {
                        if (!resultSet.next()){
                            loadingDialog.dismissDialog();
                            emailLayout.setError("E-mail ou Password errados!");
                            passwordLayout.setError("E-mail ou Password errados!");
                            break;
                        }

                        if(resultSet.getString("password").equals(passwordUsada.getText().toString())){
                            Log.d("login", "OnGetResultHandler: " + resultSet.getString("password"));
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("tipo",UserType.Clube);
                            intent.putExtra("nome", resultSet.getString("nome_clube"));
                            intent.putExtra("descricao", resultSet.getString("descricao"));

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

    private void jogadorLogin(){
        loadingDialog.startLoadingAnimation();
        DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Jogadores WHERE email='" + email.getText() + "' and password='" + passwordUsada.getText() + "';");
        Log.d("onClick", "onClick: teste");
        queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while (true) {
                    try {
                        if (!resultSet.next()){
                            loadingDialog.dismissDialog();
                            emailLayout.setError("E-mail ou Password errados!");
                            passwordLayout.setError("E-mail ou Password errados!");
                            break;
                        }

                        if(resultSet.getString("password").equals(passwordUsada.getText().toString())){
                            Log.d("login", "OnGetResultHandler: " + resultSet.getString("password"));
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("tipo",UserType.Jogador);
                            intent.putExtra("nome", resultSet.getString("nome_jogador"));
                            intent.putExtra("descricao", resultSet.getString("descricao"));

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

    private void treinadorLogin(){
        loadingDialog.startLoadingAnimation();
        DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Treinadores WHERE email='" + email.getText() + "' and password='" + passwordUsada.getText() + "';");
        Log.d("onClick", "onClick: teste");
        queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while (true) {
                    try {
                        if (!resultSet.next()){
                            loadingDialog.dismissDialog();
                            emailLayout.setError("E-mail ou Password errados!");
                            passwordLayout.setError("E-mail ou Password errados!");
                            break;
                        }

                        if(resultSet.getString("password").equals(passwordUsada.getText().toString())){
                            Log.d("login", "OnGetResultHandler: " + resultSet.getString("password"));
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("tipo",UserType.Treinador);
                            intent.putExtra("nome", resultSet.getString("nome_treinador"));
                            intent.putExtra("descricao", resultSet.getString("descricao"));

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selecionado = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
