package com.bugalho.footdream.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Button inserir = root.findViewById(R.id.buttonInsert);
        final EditText nomeClube = root.findViewById(R.id.nomeClube);
        final  EditText emailInsert = root.findViewById(R.id.emailInsert);
        final EditText password =  root.findViewById(R.id.passowordInsert);


        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.WRITE,
                        "INSERT INTO Clubes (nome_clube,email,password) VALUES ('" + nomeClube.getText()+"','" + emailInsert.getText() + "','" + password.getText() + "')");

                queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
                    @Override
                    public void OnGetResultHandler(Object resultSet) {
                        Integer rowsAffected = (Integer) resultSet;
                        if(rowsAffected > 0) Log.d("user_add", "utilizador adicionado com sucesso");
                    }
                });

            }
        });
        return root;






    }
}