package com.example.springmusicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

public class MusicImages_PlayMusic_Fragment extends Fragment {

    View view;
    ImageView imageView;
    TextView tenbh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.musicimages_playmusic_fragment,container,false);
        anhxa();
        return view;
    }

    public void hinhanh(String link,String tenbaihai) {
        Picasso.with(getActivity()).load(link).into(imageView);
        tenbh.setText(tenbaihai);
    }

    private void anhxa() {
        imageView = view.findViewById(R.id.backgroundplaymusic);
        tenbh = view.findViewById(R.id.tenbaihat);
    }
}
