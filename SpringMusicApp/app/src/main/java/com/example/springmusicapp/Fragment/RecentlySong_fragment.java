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

import com.example.springmusicapp.Adapter.User_Music_Adapter;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentlySong_fragment extends Fragment {
    View view;
    RecyclerView danhsach;
    User_Music_Adapter adapter;
    SharedPreferences load;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recentlysong_fragment,container,false);
        load = this.getContext().getSharedPreferences("luuthongtindangnhap",this.getContext().MODE_PRIVATE);
        anhxa();
        getdata(load.getInt("userid",0));
        return view;
    }

    private void getdata(int userid) {
        DataService dataService = APIService.getdataservice();
        Call<List<Music>> callmusiclist = dataService.baihatusernghenhieu(userid);

        callmusiclist.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ArrayList<Music> musics = (ArrayList<Music>) response.body();
                adapter = new User_Music_Adapter(getActivity(),musics);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                danhsach.setLayoutManager(layoutManager);
                danhsach.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("Tag user nghe nhieu",t.getMessage());
            }
        });
    }

    private void anhxa() {
        danhsach = view.findViewById(R.id.srollviewtheloaimu);
    }
}
