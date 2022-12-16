package com.example.springmusicapp.Activity;

import static com.example.springmusicapp.Activity.MainActivity.Open_Mini_player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.springmusicapp.Adapter.DanhsachbaihatAdapter;
import com.example.springmusicapp.Adapter.DsBhUsertheoCXAdapter;
import com.example.springmusicapp.Adapter.EmotionMusicAdapter;
import com.example.springmusicapp.Adapter.SingerAdapter;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Model.Singer;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmotionSearchActivity extends AppCompatActivity {
    
    Toolbar toolbar;
    TextView baihatdanghe,baihattheocamxuc;
    RecyclerView dsbaihatdanghe,dsbaihattheocamxuc;
    SharedPreferences save;
    int emotionid = 0;
    String emotionname="";
    DataService dataService = APIService.getdataservice();

    LinearLayoutManager layoutManager1 = new LinearLayoutManager(EmotionSearchActivity.this);
    LinearLayoutManager layoutManager2 = new LinearLayoutManager(EmotionSearchActivity.this);
    RelativeLayout.LayoutParams params1,params2,params3;

    DsBhUsertheoCXAdapter usertheoCXAdapter;
    EmotionMusicAdapter adapter;

    static FrameLayout frameLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_search);
        save = getSharedPreferences("luuthongtindangnhap",MODE_PRIVATE);
        anhxa();
        getintent();
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbaremotion);
        baihatdanghe = findViewById(R.id.emotiondanghe);
        baihattheocamxuc = findViewById(R.id.baihatnghenhieucamxuc);
        dsbaihatdanghe = findViewById(R.id.dsbaihatdanghe);
        dsbaihattheocamxuc = findViewById(R.id.dsbhtimkiemcamxuc);

        frameLayout2 = findViewById(R.id.framelayoutemotion);
        frameLayout2.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmotionSearchActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getintent() {
        Intent intent = getIntent();
        emotionname = intent.getStringExtra("emotion");
        emotionid = intent.getIntExtra("emotionid",0);
        getSupportActionBar().setTitle(emotionname);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("emotionid",emotionid);
        editor.commit();

        getbaihatusernghe(emotionid);
        getbaihattheocamxuc(emotionid);
    }

    private void getbaihattheocamxuc(int emotionid) {
        Call<List<Music>> emusic = dataService.baihatnghetheocamxuc(emotionid);
        emusic.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ArrayList<Music> emusics = (ArrayList<Music>) response.body();
                if(emusics.isEmpty()){
                    Log.d("Tag Emotion Music fail","khong co du lieu");
                }else {
                    baihattheocamxuc.setText("Danh sách bài hát");
                    baihattheocamxuc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f);
                    params3 = (RelativeLayout.LayoutParams) baihattheocamxuc.getLayoutParams();
                    params3.setMargins(30,0,0,20);

                    adapter = new EmotionMusicAdapter(EmotionSearchActivity.this,emusics);

                    layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                    dsbaihattheocamxuc.setLayoutManager(layoutManager2);
                    dsbaihattheocamxuc.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("Tag Emotion Music fail",t.getMessage());
            }
        });
    }

    private void getbaihatusernghe(int emotionid) {
        int userid = save.getInt("userid",0);

        Call<List<Music>> dsbaihatusernghe = dataService.baihatusernghetheocamxuc(userid,emotionid);
        dsbaihatusernghe.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ArrayList<Music> umusics = (ArrayList<Music>) response.body();
                if(umusics.isEmpty()){
                    Log.d("Tag lay ds cx fail","khong co du lieu");
                }else {
                    baihatdanghe.setText("Bài hát mà bạn đã nghe khi "+emotionname);
                    baihatdanghe.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f);
                    params1 = (RelativeLayout.LayoutParams) baihatdanghe.getLayoutParams();
                    params1.setMargins(30,20,0,15);
                    usertheoCXAdapter = new DsBhUsertheoCXAdapter(EmotionSearchActivity.this,umusics);

                    layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                    dsbaihatdanghe.setLayoutManager(layoutManager1);
                    dsbaihatdanghe.setAdapter(usertheoCXAdapter);
                    params2 = (RelativeLayout.LayoutParams) dsbaihatdanghe.getLayoutParams();
                    params2.setMargins(0,0,0,50);
                }
            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                Log.d("Tag lay ds cx fail",t.getMessage());
            }
        });
    }

    static public void hienminiviewds(){
        if(Open_Mini_player==true){
            frameLayout2.setVisibility(View.VISIBLE);
        }
        else {
            frameLayout2.setVisibility(View.GONE);
        }
    }
}