package com.example.springmusicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.CameraActivity;
import com.example.springmusicapp.Adapter.DanhsachbaihatAdapter;
import com.example.springmusicapp.Adapter.SingerAdapter;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Model.Singer;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_fragment extends Fragment {
    View view;

    EditText txtsearch;
    CircleImageView emotion;
    TextView casi,baihat;
    RecyclerView dscs,dsbh;

    SingerAdapter singerAdapter;
    DanhsachbaihatAdapter adapter;

    DataService dataService = APIService.getdataservice();

    LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
    LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
    RelativeLayout.LayoutParams params1,params2,params3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.searchfragment,container,false);
        anhxa();
        return view;
    }

    private void anhxa() {
        txtsearch = view.findViewById(R.id.search);
        emotion = view.findViewById(R.id.camera);
        casi = view.findViewById(R.id.casi);
        baihat = view.findViewById(R.id.baihat);
        dscs = view.findViewById(R.id.dscasitimkiem);
        dsbh = view.findViewById(R.id.dsbhtimkiem);

        eventclick();

    }

    private void eventclick() {
        txtsearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_ENTER){
                    String timkiem = txtsearch.getText().toString();

                    getdatabh(timkiem);
                    getdatacasi(timkiem);
                }
                return false;
            }
        });

        emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void getdatacasi(String timkiem) {
        Call<List<Singer>> timkiemcasi = dataService.timkiemcasi(timkiem);
        timkiemcasi.enqueue(new Callback<List<Singer>>() {
            @Override
            public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {
                ArrayList<Singer> singers = (ArrayList<Singer>) response.body();
                if(singers.isEmpty()){

                }else {
                    casi.setText("Ca sĩ");
                    casi.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f);
                    params1 = (RelativeLayout.LayoutParams) casi.getLayoutParams();
                    params1.setMargins(30,50,0,30);
                    singerAdapter = new SingerAdapter(getActivity(),singers);

                    layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                    dscs.setLayoutManager(layoutManager1);
                    dscs.setAdapter(singerAdapter);
                    params2 = (RelativeLayout.LayoutParams) dscs.getLayoutParams();
                    params2.setMargins(0,0,0,50);
                }
            }

            @Override
            public void onFailure(Call<List<Singer>> call, Throwable t) {
                Log.d("Tag tìm kiếm ca sĩ",t.getMessage());
            }
        });
    }

    private void getdatabh(String timkiem) {
        Call<List<Music>> timkiembaihat = dataService.timkiembaihat(timkiem);
        timkiembaihat.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ArrayList<Music> musicArrayList = (ArrayList<Music>) response.body();
                if(musicArrayList.isEmpty()){

                }else {
                    baihat.setText("Danh sách bài hát");
                    baihat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f);
                    params3 = (RelativeLayout.LayoutParams) baihat.getLayoutParams();
                    params3.setMargins(30,0,0,30);

                    adapter = new DanhsachbaihatAdapter(getActivity(),musicArrayList);

                    layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                    dsbh.setLayoutManager(layoutManager2);
                    dsbh.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("Tag tim kiem bai hat",t.getMessage());
            }
        });
    }

}
