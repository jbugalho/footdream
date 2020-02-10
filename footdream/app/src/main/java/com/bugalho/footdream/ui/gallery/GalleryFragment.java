package com.bugalho.footdream.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bugalho.footdream.MainActivity;
import com.bugalho.footdream.R;

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