package com.example.springmusicapp.Activity;

import static com.example.springmusicapp.Activity.MainActivity.Open_Mini_player;
import static com.example.springmusicapp.Activity.PlayMusicActivity.mediaPlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.springmusicapp.Adapter.DanhsachbaihatAdapter;
import com.example.springmusicapp.Adapter.DanhsachbaihatplaylistAdapter;
import com.example.springmusicapp.Model.Category;
import com.example.springmusicapp.Model.Ketqua;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.Model.Singer;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachActivity extends AppCompatActivity {

    Toolbar toolbar;
    CircleImageView circleImageView;
    TextView ten;
    ImageButton button;
    RecyclerView recyclerView;
    ImageView background;
    static FrameLayout frameLayout1;

    DanhsachbaihatAdapter adapter;
    DanhsachbaihatplaylistAdapter adapterpl;
    SharedPreferences load;
    DataService dataService = APIService.getdataservice();
    ArrayList<Music> musicArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhxa();
        GetdataIntent();
        hienminiviewds();
    }

    private void anhxa() {
        circleImageView = findViewById(R.id.hinhnends);
        ten = findViewById(R.id.tends);
        button = findViewById(R.id.actionbutton);
        recyclerView = findViewById(R.id.dsbaihat);
        background = findViewById(R.id.background);
        toolbar = findViewById(R.id.dstoolbar);
        button.setEnabled(false);

        frameLayout1 = findViewById(R.id.framelayoutds);
        frameLayout1.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetdataIntent() {
        Intent intent = getIntent();
        if(intent!=null){
            if(intent.hasExtra("baihattheotheloai")){
                Category category = (Category) intent.getSerializableExtra("baihattheotheloai");
                ten.setText(category.getCategoryName());
                Picasso.with(DanhSachActivity.this).load(category.getCategoryImages()).into(background);
                //getdata(category.getCategoryId(),0);
                getdata(category.getCategoryId(),0);
            }
            if(intent.hasExtra("baihattheoplaylist")){
                MusicList musicList = (MusicList) intent.getSerializableExtra("baihattheoplaylist");
                int id = musicList.getMusicListId();
                ten.setText(musicList.getMusicListName());
                circleImageView.setImageResource(R.drawable.playlist);
                load = this.getSharedPreferences("luuthongtindangnhap",MODE_PRIVATE);

                SharedPreferences.Editor editor = load.edit();
                //Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
                editor.putInt("playlistid",id);
                editor.commit();

                getdata(musicList.getMusicListId(),1);
            }
            if(intent.hasExtra("baihattheocasi")){
                Singer singer = (Singer) intent.getSerializableExtra("baihattheocasi");
                ten.setText(singer.getSingerName());
                Picasso.with(DanhSachActivity.this).load(singer.getSingerImages()).into(circleImageView);
                getdata(singer.getSingerId(),2);
            }
        }
    }

    private void getdata(int id, int i) {
        Call<List<Music>> musics =null;

        if(i == 0 || i ==2){
            if(i==0){musics=dataService.baihattheotheloai(id);}
            if(i==2){musics=dataService.baihattheocasi(id);}
            musics.enqueue(new Callback<List<Music>>() {
                @Override
                public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                    musicArrayList = (ArrayList<Music>) response.body();
                    adapter = new DanhsachbaihatAdapter(DanhSachActivity.this,musicArrayList);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(DanhSachActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                    eventclick();
                }

                @Override
                public void onFailure(Call<List<Music>> call, Throwable t) {
                    Log.d("TAG",t.getMessage());
                }
            });

        }
        if(i == 1){
            musics = dataService.musicinplaylist(id);

            musics.enqueue(new Callback<List<Music>>() {
                @Override
                public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                    musicArrayList = (ArrayList<Music>) response.body();
                    adapterpl = new DanhsachbaihatplaylistAdapter(DanhSachActivity.this,musicArrayList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(DanhSachActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapterpl);

                    eventclick();
                }

                @Override
                public void onFailure(Call<List<Music>> call, Throwable t) {
                    Log.d("TAG",t.getMessage());
                }
            });
        }
    }

    public void xoabaihat(int musicid,int listid){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.deletemusic);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        Button xoa,huy;
        xoa = dialog.findViewById(R.id.btnxoa);
        huy = dialog.findViewById(R.id.btnhuyxoa);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Ketqua>> ketqua = dataService.xoabaihatkhoiplaylist(musicid,listid);
                ketqua.enqueue(new Callback<List<Ketqua>>() {
                    @Override
                    public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                        ArrayList<Ketqua> ketquas = (ArrayList<Ketqua>) response.body();
                        String kq = ketquas.get(0).getSuccess();
                        if(kq.equals("")){
                            Toast.makeText(DanhSachActivity.this, "Xoá bài hát thất bại", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Toast.makeText(DanhSachActivity.this, "Xoá bài hát thành công", Toast.LENGTH_SHORT).show();
                            //adapterpl.notifyDataSetChanged();
                            getdata(listid,1);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                        Log.d("Tag xoá bài hát",t.getMessage());
                    }
                });
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void eventclick(){
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DanhSachActivity.this, "button enabled", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DanhSachActivity.this, PlayMusicActivity.class);
                intent.putExtra("danhsachbaihat",musicArrayList);
                startActivity(intent);
            }
        });
    }

    static public void hienminiviewds(){
        if(Open_Mini_player==true){
            frameLayout1.setVisibility(View.VISIBLE);
        }
        else {
            frameLayout1.setVisibility(View.GONE);
        }
    }
}