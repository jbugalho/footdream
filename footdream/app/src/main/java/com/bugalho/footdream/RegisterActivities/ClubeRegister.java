package com.bugalho.footdream.RegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.LoadingDialog;
import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;
import com.bugalho.footdream.UserClass.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubeRegister extends AppCompatActivity {

    private Button inserir;
    private EditText nomeClube;
    private EditText emailInsert;
    private EditText password;
    private LoadingDialog loadingDialog;
    public boolean errorEmail = false;
    public boolean errorNome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clube_register);

        inserir = findViewById(R.id.buttonInsert);
        nomeClube = findViewById(R.id.nomeClube);
        emailInsert = findViewById(R.id.emailInsert);
        password =  findViewById(R.id.passwordInsert);
        loadingDialog = new LoadingDialog(this);


        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingAnimation();
                DatabaseBuilder verificarEmail = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Clubes WHERE email='" + emailInsert.getText() + "'");
                verificarEmail.execute(new OnDatabaseBuilderQueryExecuteListener() {
                        @Override
                        public void OnGetResultHandler(Object rs) {

                            Log.d("verificaremial", "OnGetResultHandler: Verificar email");
                            ResultSet resultSet = (ResultSet) rs;

                            while (true) {
                                try {
                                    if (resultSet.next()) {
                                        // já existe
                                        Log.d("Já email", "Já existe email");
                                        errorEmail = true;
                                        break;

                                    } else {
                                        errorEmail = false;
                                        break;
                                    }

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            }

                            DatabaseBuilder verificarNome = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM Clubes WHERE nome_clube='" + nomeClube.getText() + "'");
                            verificarNome.execute(new OnDatabaseBuilderQueryExecuteListener() {
                                @Override
                                public void OnGetResultHandler(Object rs) {

                                    ResultSet resultSet = (ResultSet) rs;

                                    while(true) {
                                        try {
                                            if (resultSet.next()) {
                                                // já existe
                                                Log.d("Já existe", "Já existe nome do clube");

                                                errorNome = true;

                                                break;

                                            } else {
                                                errorNome = false;
                                                break;
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    if (!errorEmail && !errorNome)
                                        insertNewClub();
                                    else loadingDialog.dismissDialog();

                                }
                            });

                        }

                    });
            }
        });
    }

    private void insertNewClub() {
        DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.WRITE,
                "INSERT INTO Clubes (nome_clube,email,password) VALUES ('" + nomeClube.getText() + "','" + emailInsert.getText() + "','" + password.getText() + "')");


        queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object resultSet) {
                Integer rowsAffected = (Integer) resultSet;

                if (rowsAffected > 0) {
                    Log.d("user_add", "utilizador adicionado com sucesso");
                } else {
                    nomeClube.setError("Este clube já se encontra registado");
                    emailInsert.setError("Este clube já se encontra registado");
                }
            }
        });

        DatabaseBuilder getId = new DatabaseBuilder(QueryMode.READ,"SELECT idClube FROM Clubes WHERE nome_clube='" + nomeClube.getText() + "'");
        getId.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while(true) {
                    try {
                        if (resultSet.next()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("tipo",UserType.Clube);
                            intent.putExtra("id",resultSet.getInt("idClube"));
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
