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
import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;
import com.bugalho.footdream.User;
import com.bugalho.footdream.UserType;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        EditText teste = root.findViewById(R.id.nomeClube);
        EditText teste1 = root.findViewById(R.id.emailInsert);
        teste.setText(MainActivity.userLogado.getNome());
        teste1.setText(MainActivity.userLogado.getUserType().toString());




        return root;
    }
}