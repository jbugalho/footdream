package com.bugalho.footdream.RegisterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.LoadingDialog;
import com.bugalho.footdream.R;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreinadorRegister extends AppCompatActivity {
    private List<String> clubes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        clubes.add("Selecione o seu clube: ");

        Spinner spinner;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinador_register);

        spinner = findViewById(R.id.spinner);
        LoadingDialog loadingDialog = new LoadingDialog(this);


        DatabaseBuilder queryClubes = new DatabaseBuilder(QueryMode.READ,"SELECT `nome_clube` FROM `Clubes`");
        loadingDialog.startLoadingAnimation();
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
        SpinnerAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,clubes);
        spinner.setAdapter(adapter);
        loadingDialog.dismissDialog();


        

    }
}
