package com.bugalho.footdream.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.R;
import com.bugalho.footdream.UserClass.User;
import com.bugalho.footdream.UserClass.UserType;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<User> jogadores = new ArrayList<User>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = root.findViewById(R.id.listView);



        DatabaseBuilder queryClubes = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM `Jogadores`,`Clubes` WHERE Jogadores.Clubes_idClube=Clubes.idClube");
        queryClubes.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs) {

                Log.d("query", "OnGetResultHandler: teste");
                ResultSet resultSet = (ResultSet) rs;
                while (true) {
                    try {
                        Log.d("query", "OnGetResultHandler: teste2");
                        if(resultSet.next()) {
                            arrayList.add(resultSet.getString("nome_jogador") + " Posição: " + resultSet.getString("posicao") + ". Escalão: " +
                                    resultSet.getString("escalao")+ ". Clube atual: " + resultSet.getString("nome_clube"));
                        }else break;
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

       for(int i = 0;i < jogadores.size();i++){
           arrayList.add(jogadores.get(i).getNome() + "Posição: " + jogadores.get(i).getPosicao() + " Clube atual: " + jogadores.get(i).getClube());
       }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        return root;
    }
}