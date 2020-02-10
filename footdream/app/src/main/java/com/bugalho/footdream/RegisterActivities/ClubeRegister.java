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

public class ClubeRegister extends AppCompatActivity {

    private Button inserir;
    private EditText nomeClube;
    private EditText emailInsert;
    private EditText password;
    private LoadingDialog loadingDialog;

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
                DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.WRITE,
                        "INSERT INTO Clubes (nome_clube,email,password) VALUES ('" + nomeClube.getText() + "','" + emailInsert.getText() + "','" + password.getText() + "')");


                queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
                    @Override
                    public void OnGetResultHandler(Object resultSet) {
                        Integer rowsAffected = (Integer) resultSet;

                        if (rowsAffected > 0) {
                            Log.d("user_add", "utilizador adicionado com sucesso");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("tipo", UserType.Clube);
                            intent.putExtra("nome", nomeClube.getText().toString());
                            intent.putExtra("descricao", " ");
                            startActivity(intent);
                            finish();
                        } else {
                            nomeClube.setError("Este clube já se encontra registado");
                            emailInsert.setError("Este clube já se encontra registado");
                        }
                    }
                });
            }
        });



    }
}
