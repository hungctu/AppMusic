package com.example.springmusicapp.Dialog;

import static com.example.springmusicapp.Adapter.PlaylistthembaihatAdapter.itemposition;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.springmusicapp.Adapter.PlaylistthembaihatAdapter;
import com.example.springmusicapp.Model.Ketqua;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Thembaihatvaoplaylist {
    int music_id;
    SharedPreferences load;
    PlaylistthembaihatAdapter adapter;
    DataService dataService = APIService.getdataservice();
    static Dialog dialog = null;
    public void dsplaylist(Context context,int musicid){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.thembaihatvaoplaylistdialog);
        music_id = musicid;
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        RelativeLayout relativeLayout = dialog.findViewById(R.id.taoplaylist);
        RecyclerView recyclerView = dialog.findViewById(R.id.dsplaylist);

        load = context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
        int id = load.getInt("userid",0);
        String fullname = load.getString("fullname","");
        getdata(id,context,recyclerView);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Taoplaylist taoplaylist = new Taoplaylist();
                taoplaylist.taoplaylist(context,musicid,id,fullname);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void thembaihat(Context context,int id){
        load = context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
        music_id=load.getInt("musicid",0);
        //Toast.makeText(context, music_id+" - "+id, Toast.LENGTH_SHORT).show();
        Call<List<Ketqua>> thembaihat = dataService.thembaihatpl(music_id,id);
        thembaihat.enqueue(new Callback<List<Ketqua>>() {
            @Override
            public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                ArrayList<Ketqua> ketquas = (ArrayList<Ketqua>) response.body();
                String success = ketquas.get(0).getSuccess();
                if(success.equals("DaTonTai")){
                    Toast.makeText(context, "Bài hát đã có trong playlist", Toast.LENGTH_SHORT).show();
                }
                else if(success.equals("0")){
                    Toast.makeText(context, "Thêm bài hát vào playlist thất bại", Toast.LENGTH_SHORT).show();
                }else {
                    if(dialog == null){
                        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Đã thêm bài hát vào playlist", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    //Toast.makeText(context, "Đã thêm bài hát vào playlist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                Log.d("TAG thêm bài hát",t.getMessage());
            }
        });

    };


    private void getdata(int id,Context context,RecyclerView recyclerView) {
        DataService dataService = APIService.getdataservice();
        Call<List<MusicList>> callmusiclist = dataService.getplaylist(id);
        callmusiclist.enqueue(new Callback<List<MusicList>>() {
            @Override
            public void onResponse(Call<List<MusicList>> call, Response<List<MusicList>> response) {
                ArrayList<MusicList> musicLists = (ArrayList<MusicList>) response.body();
                adapter = new PlaylistthembaihatAdapter(context,musicLists);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<MusicList>> call, Throwable t) {
                Log.d("TAG",t.getMessage());
            }
        });

    }
}
