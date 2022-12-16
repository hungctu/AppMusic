package com.example.springmusicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Adapter.PlayMusicAdapter;
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.R;


public class DS_Play_Music_Fragment extends Fragment {
    View view;
    RecyclerView recyclerViewplaymusic;
    PlayMusicAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dsplaymusicfragment,container,false);
        anhxa();
        ganadapter();

        return view;
    }

    private void ganadapter() {
        if(PlayMusicActivity.musics.size()>0){
            adapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.musics);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerViewplaymusic.setLayoutManager(layoutManager);
            recyclerViewplaymusic.setAdapter(adapter);
        }
    }

    private void anhxa() {
        recyclerViewplaymusic = view.findViewById(R.id.dsbaihatplaymusic);
    }
}
