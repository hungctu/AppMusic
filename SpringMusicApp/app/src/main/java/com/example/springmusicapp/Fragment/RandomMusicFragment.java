package com.example.springmusicapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Adapter.RandommusicAdapter;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomMusicFragment extends Fragment {

    View view;
    RecyclerView recyclerViewrandommusic;
    RandommusicAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.randommusicfragment,container,false);
        anhxa();
        getdata();
        return view;
    }

    private void anhxa() {
        recyclerViewrandommusic = view.findViewById(R.id.recyclerrandommusic);
    }

    private void getdata() {

        DataService dataService = APIService.getdataservice();
        Call<List<Music>> randommusicCall = dataService.getRandommusic();

        randommusicCall.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ArrayList<Music> music = (ArrayList<Music>) response.body();
                adapter = new RandommusicAdapter(getActivity(), music);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerViewrandommusic.setLayoutManager(layoutManager);
                recyclerViewrandommusic.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("TAG RandomMusic", t.getMessage());
            }
        });
    }
}
