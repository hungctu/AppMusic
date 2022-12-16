package com.example.springmusicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Adapter.TopMusicAdapter;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopMusicFragment extends Fragment {
    View view;
    RecyclerView ScrollView;
    TextView textView;
    TopMusicAdapter adapter;
    Runnable runnable;
    Handler handler;
    int item = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.topmusicfragment,container,false);
        anhxa();
        getdata();
        return view;
    }

    private void anhxa() {
        ScrollView = view.findViewById(R.id.scrollviewtopmusic);
        textView =   view.findViewById(R.id.txttopmusic);
    }

    private void getdata() {
        DataService dataService = APIService.getdataservice();
        Call<List<Music>> listCall = dataService.getTopMusicData();
        listCall.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ArrayList<Music> topmusics = (ArrayList<Music>) response.body();

                adapter = new TopMusicAdapter(getActivity(),topmusics);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                ScrollView.setLayoutManager(layoutManager);
                ScrollView.setAdapter(adapter);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        item++;
                        if(item>=9){
                            item = 0;
                        }
                        ScrollView.smoothScrollToPosition(item);
                        handler.postDelayed(runnable,5000);
                    }
                };
                handler.postDelayed(runnable,5000);


            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("TAG TopMusic", t.getMessage());
            }
        });
    }
}
