package com.bugalho.footdream.RegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.LoadingDialog;
import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;
import com.bugalho.footdream.UserClass.User;
import com.bugalho.footdream.UserClass.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class JogadorRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<String> clubes = new ArrayList<String>();
    private EditText email;
    private EditText nome;
    private EditText password;
    private String clubeSelecionado;
    private boolean errorEmail;
    private Spinner spinner;
    private int clubeId;
    private EditText divisao;
    private EditText escalao;
    private EditText posicao;
    private String clubeNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        clubes.add("Selecione o seu clube: ");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador_register);

        spinner = findViewById(R.id.spinner);
        final LoadingDialog loadingDialog = new LoadingDialog(this);

        email = findViewById(R.id.emailInsert);
        nome = findViewById(R.id.nomeTreinador);
        password = findViewById(R.id.passwordInsert);
        divisao = findViewById(R.id.divisaoInsert);
        escalao = findViewById(R.id.escalaoInsert);
        posicao = findViewById(R.id.posicaoInsert);


        DatabaseBuilder queryClubes = new DatabaseBuilder(QueryMode.READ,"SELECT `nome_clube` FROM `Clubes`");

        queryClubes.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs) {

                Log.d("query", "OnGetResultHandler: teste");
                ResultSet resultSet = (ResultSet) rs;
                while (true) {
                    try {
                        Log.d("query", "OnGetResultHandler: teste2");
                        if(resultSet.next()) {
                            clubes.add(resultSet.getString("nome_clube"));
                        }else break;
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        final SpinnerAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,clubes);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button register = findViewById(R.id.buttonInsert);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingAnimation();
                DatabaseBuilder checkEmail = new DatabaseBuilder(QueryMode.READ, "SELECT * FROM Jogadores WHERE email='" + email.getText() + "'");
                checkEmail.execute(new OnDatabaseBuilderQueryExecuteListener() {
                    @Override
                    public void OnGetResultHandler(Object rs) {
                        ResultSet resultSet = (ResultSet) rs;

                        while(true){
                            try{
                                if(resultSet.next()){
                                    errorEmail = true;
                                    email.setError("Este email já se encontra registado");
                                    break;
                                }else{
                                    errorEmail = false;
                                    break;
                                }
                            }catch (SQLException e){
                                e.printStackTrace();
                            }
                        }

                    }
                });


                spinner.getSelectedItem().toString();


                if(!errorEmail){
                    DatabaseBuilder checkClube = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Clubes WHERE nome_clube='" + clubeSelecionado + "'");
                    checkClube.execute(new OnDatabaseBuilderQueryExecuteListener() {
                        @Override
                        public void OnGetResultHandler(Object rs){
                            Log.d("get club", "teste");
                            ResultSet resultSet = (ResultSet) rs;

                            while(true){
                                try {
                                    if(resultSet.next()){
                                        clubeId = resultSet.getInt("idClube");
                                        clubeNome = resultSet.getString("nome_clube");
                                        insertNewPlayer();
                                        break;
                                    }else{
                                        break;
                                    }
                                }catch (SQLException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });


                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        clubeSelecionado = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void insertNewPlayer() {
        DatabaseBuilder registo = new DatabaseBuilder(QueryMode.WRITE,
                "INSERT INTO `Jogadores`(`nome_jogador`, `email`, `password`, `escalao`, `posicao`, `divisao`, `Clubes_idClube`) VALUES ('" + nome.getText() + "','" + email.getText() + "','"
                        + password.getText() + "','"+ escalao.getText() + "','" + posicao.getText() + "','" + divisao.getText() + "','" + clubeId +"')");

        //INSERT INTO `Jogadores`(`nome_jogador`, `email`, `password`, `escalao`, `posicao`, `divisao`, `Clubes_idClube`) VALUES


        registo.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object resultSet) {
                Integer rowsAffected = (Integer) resultSet;

                if (rowsAffected > 0) {
                    Log.d("user_add", "utilizador adicionado com sucesso");

                } else {
                    email.setError("Este email já se encontra registado");
                }
            }
        });

        DatabaseBuilder getId = new DatabaseBuilder(QueryMode.READ,"SELECT idJogador FROM Jogadores WHERE nome_jogador='" + nome.getText() + "'");

        getId.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while(true) {
                    try {
                        if (resultSet.next()) {
                            clubeId = resultSet.getInt("idJogador");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("tipo",UserType.Jogador);
                            intent.putExtra("id",clubeId);
                            startActivity(intent);
                            finish();

                            break;
                        }else break;
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
