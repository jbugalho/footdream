package com.bugalho.footdream.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bugalho.footdream.Helper.DatabaseBuilder;
import com.bugalho.footdream.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.bugalho.footdream.Helper.QueryMode;
import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;
import com.bugalho.footdream.UserClass.User;
import com.bugalho.footdream.UserClass.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;



public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    public static User userLogado;
    private Bundle bundle;
    private TextView nome;
    private TextView escalao;
    private TextView posicao;
    private TextView divisao;
    private TextView clube_nome;
    private TextView descricao;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        bundle = getActivity().getIntent().getExtras();

            switch((UserType)bundle.get("tipo")){
                case Clube:
                    loginClube();
                    break;

                case Treinador:
                    loginTreinador();
                    break;

                case Jogador:
                    Log.d("jogador", "onCreate: entra no jogador");
                    loginJogador();
                    break;
                default:
                    break;
            }


        nome = root.findViewById(R.id.nome);
        escalao = root.findViewById(R.id.escalao);
        posicao = root.findViewById(R.id.posicao);
        divisao = root.findViewById(R.id.divisao);
        clube_nome = root.findViewById(R.id.clube_nome);
        descricao = root.findViewById(R.id.descricao);

        return root;



    }

    private void loginClube() {

        DatabaseBuilder checkClube = new DatabaseBuilder(QueryMode.READ,"SELECT * FROM `Clubes` WHERE idClube=" + bundle.getInt("id"));

        checkClube.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while(true) {
                    try {
                        if (resultSet.next()) {
                            userLogado = new User(UserType.Clube,resultSet.getString("nome_clube"),resultSet.getString("descricao"));
                            nome.setText(userLogado.getNome());
                            escalao.setVisibility(View.INVISIBLE);
                            posicao.setVisibility(View.INVISIBLE);
                            divisao.setVisibility(View.INVISIBLE);
                            clube_nome.setVisibility(View.INVISIBLE);
                            descricao.setText("");

                            break;
                        }else break;
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void loginTreinador(){

        DatabaseBuilder checkTreinador = new DatabaseBuilder(QueryMode.READ,"SELECT *,nome_clube FROM `Treinadores`,`Clubes` WHERE Treinadores.idTreinador=" +
                bundle.getInt("id") + " AND Treinadores.Clubes_idClube=Clubes.idClube");

        checkTreinador.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                ResultSet resultSet = (ResultSet) rs;

                while(true) {
                    try {
                        if (resultSet.next()) {
                            userLogado = new User(UserType.Treinador,resultSet.getString("nome_treinador"),resultSet.getString("nome_clube"),resultSet.getString("descricao"));
                            nome.setText(userLogado.getNome());
                            clube_nome.setText(userLogado.getClube());
                            descricao.setText(userLogado.getDescricao());
                            escalao.setVisibility(View.INVISIBLE);
                            posicao.setVisibility(View.INVISIBLE);
                            divisao.setVisibility(View.INVISIBLE);
                            break;
                        }else break;
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void loginJogador() {
        Log.d("teste", "loginJogador: teste");

        DatabaseBuilder checkJogador = new DatabaseBuilder(QueryMode.READ,"SELECT *,nome_clube FROM `Jogadores`,`Clubes` WHERE Jogadores.idJogador=" +
                bundle.getInt("id") +  " AND Jogadores.Clubes_idClube=Clubes.idClube");

        checkJogador.execute(new OnDatabaseBuilderQueryExecuteListener() {
            @Override
            public void OnGetResultHandler(Object rs){
                Log.d("teste", "OnGetResultHandler: teste2");
                ResultSet resultSet = (ResultSet) rs;

                while(true) {
                    try {
                        if (resultSet.next()) {
                            userLogado = new User(UserType.Jogador,resultSet.getString("nome_jogador"),resultSet.getString("escalao"),resultSet.getString("posicao"),resultSet.getString("divisao"),
                                    resultSet.getString("nome_clube"),resultSet.getString("descricao"));
                            nome.setText(userLogado.getNome());
                            escalao.setText(userLogado.getEscalao());
                            posicao.setText(userLogado.getPosicao());
                            divisao.setText(userLogado.getDivisao());
                            clube_nome.setText(userLogado.getClube());
                            descricao.setText(userLogado.getDescricao());
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

