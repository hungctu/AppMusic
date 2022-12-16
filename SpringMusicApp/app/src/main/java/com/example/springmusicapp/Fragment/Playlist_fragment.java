package com.example.springmusicapp.Fragment;

import android.content.SharedPreferences;
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

import com.example.springmusicapp.Adapter.PlaylistAdapter;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Playlist_fragment extends Fragment {

    View view;
    RecyclerView danhsach;
    PlaylistAdapter adapter;
    SharedPreferences load;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.playlist_fragment,container,false);
        load = this.getContext().getSharedPreferences("luuthongtindangnhap",this.getContext().MODE_PRIVATE);
        anhxa();
        getdata(load.getInt("userid",0));
        return view;
    }

    private void anhxa() {
        danhsach = view.findViewById(R.id.danhsachbaihattheouser);

    }

    private void getdata(int id) {
        DataService dataService = APIService.getdataservice();
        Call<List<MusicList>> callmusiclist = dataService.getplaylist(id);
        callmusiclist.enqueue(new Callback<List<MusicList>>() {
            @Override
            public void onResponse(Call<List<MusicList>> call, Response<List<MusicList>> response) {
                ArrayList<MusicList> musicLists = (ArrayList<MusicList>) response.body();
                adapter = new PlaylistAdapter(getActivity(),musicLists);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                danhsach.setLayoutManager(layoutManager);
                danhsach.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MusicList>> call, Throwable t) {
                Log.d("TAG ds playlist",t.getMessage());
            }
        });
    }
}
