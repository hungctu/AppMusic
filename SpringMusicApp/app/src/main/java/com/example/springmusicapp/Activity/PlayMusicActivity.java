package com.example.springmusicapp.Activity;
import static com.example.springmusicapp.Activity.MainActivity.Open_Mini_player;
import static com.example.springmusicapp.Fragment.MiniViewFragment.miniviewprocess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.springmusicapp.Adapter.ViewpagerPlayMusicAdapter;
import com.example.springmusicapp.Dialog.EmotionDialog;
import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Fragment.DS_Play_Music_Fragment;
import com.example.springmusicapp.Fragment.MiniViewFragment;
import com.example.springmusicapp.Fragment.MusicImages_PlayMusic_Fragment;
import com.example.springmusicapp.Model.Ketqua;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayMusicActivity extends AppCompatActivity {

    Intent intent;
    Toolbar toolbarplaymusic;
    TextView txttime,txttoltaltime;
    SeekBar seekBartime;
    ImageButton imgplay,imgnext,imgpre,imgshuffer,imgrepeat;
    ImageView menu;
    ViewPager viewPagerplaymusic;
    //static Boolean Open_Mini_player =false;
    SharedPreferences load;
    EmotionDialog emotionDialog = new EmotionDialog();


    static public ArrayList<Music> musics = new ArrayList<>();
    static public ViewpagerPlayMusicAdapter adapter;

    DS_Play_Music_Fragment dsPlayMusicFragment;
    MusicImages_PlayMusic_Fragment musicFragment;
    public static MediaPlayer mediaPlayer;

    public static int position = 0;
    public static boolean repeat = false;
    public  static boolean random = false;
    public static boolean next = false;
    boolean trongds = false;
    boolean emotion = false;
    boolean ngoaiplaylist = false;
    DataService dataService = APIService.getdataservice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        position = 0;
        Log.d("ngoaiplaylist",""+ngoaiplaylist);
        getintent();
        anhxa();
        eventclick();
    }

    private void anhxa() {
        toolbarplaymusic = findViewById(R.id.toolbarplaymusic);
        txttime = findViewById(R.id.txttimemusic);
        txttoltaltime = findViewById(R.id.txttotaltimemusic);
        seekBartime = findViewById(R.id.seekbarplaymusic);
        imgplay = findViewById(R.id.btnplay);
        imgnext = findViewById(R.id.btnnext);
        imgpre = findViewById(R.id.btnpre);
        imgshuffer = findViewById(R.id.btnshuffle);
        imgrepeat = findViewById(R.id.btnrepeat);
        menu = findViewById(R.id.menumusicplaymusic);
        viewPagerplaymusic = findViewById(R.id.Viewpaperplaymusic);


        setSupportActionBar(toolbarplaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarplaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trongds){
                    DanhSachActivity.hienminiviewds();
                }

                if(emotion){
                    EmotionSearchActivity.hienminiviewds();
                }
                //
                ngoaiplaylist=true;
                MainActivity.hienminiview();
                Log.d("ngoaiplaylist",""+ngoaiplaylist);
                finish();
            }
        });

        dsPlayMusicFragment = new DS_Play_Music_Fragment();
        musicFragment = new MusicImages_PlayMusic_Fragment();

        adapter = new ViewpagerPlayMusicAdapter(getSupportFragmentManager());
        adapter.addfragment(musicFragment);
        adapter.addfragment(dsPlayMusicFragment);

        viewPagerplaymusic.setAdapter(adapter);

        musicFragment = (MusicImages_PlayMusic_Fragment) adapter.getItem(0);
        if(musics.size()>0){
            getSupportActionBar().setTitle(musics.get(0).getMusicName());
            new playmusic().execute(musics.get(0).getUrl());
            imgplay.setImageResource(R.drawable.pause);
        }
    }

    @Override
    public void onBackPressed() {
        if(trongds){
            DanhSachActivity.hienminiviewds();
        }

        if(emotion){
            EmotionSearchActivity.hienminiviewds();
        }
        //
        ngoaiplaylist=true;
        MainActivity.hienminiview();
        Log.d("ngoaiplaylist",""+ngoaiplaylist);
        finish();
        super.onBackPressed();
    }

    private void getintent() {
        intent = getIntent();
        musics.clear();
        load = getSharedPreferences("luuthongtindangnhap",MODE_PRIVATE);
        if(intent != null){
            if(intent.hasExtra("baihat")){
                Music music = intent.getParcelableExtra("baihat");
                musics.add(music);
            }
            if(intent.hasExtra("danhsachbaihat")){
                ArrayList<Music> musicArrayList = intent.getParcelableArrayListExtra("danhsachbaihat");
                musics = musicArrayList;
                trongds=true;
            }
            if(intent.hasExtra("emotionmusic")){
                Music music = intent.getParcelableExtra("emotionmusic");
                musics.add(music);
                emotion=true;
            }
        }
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void eventclick(){
        final int[] mid = {0};
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapter.getItem(1)!=null){
                    if(musics.size()>0){
                        Log.d("tag","123");
                        musicFragment.hinhanh(musics.get(0).getMusicImages(),musics.get(0).getMusicName());
                        mid[0] = musics.get(0).getMusicId();
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,200);
                    }
                }
            }
        },500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play);
                    //Toast.makeText(PlayMusicActivity.this, "dang chay", Toast.LENGTH_SHORT).show();
                }else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.pause);
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mid[0]==0){

                }
                else {
                    //Toast.makeText(PlayMusicActivity.this, ""+id[0], Toast.LENGTH_SHORT).show();
                    SharedPreferences load =getSharedPreferences("luuthongtindangnhap",MODE_PRIVATE);
                    int id = load.getInt("userid",0);
                    if(id==0){
                        Intent intent = new Intent(PlayMusicActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        SharedPreferences.Editor editor = load.edit();
                        editor.putInt("musicid", mid[0]);
                        editor.commit();
                        Thembaihatvaoplaylist thembaihatvaoplaylist = new Thembaihatvaoplaylist();
                        thembaihatvaoplaylist.dsplaylist(PlayMusicActivity.this, mid[0]);
                    }
                }
            }
        });

        seekBartime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musics.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position <= musics.size()){
                        imgplay.setImageResource(R.drawable.pause);
                        position++;

                        if(repeat == true){
                            if(position == 0){
                                position = musics.size();
                            }
                            position -= 1;
                        }
                        if(random == true){
                            Random random = new Random();
                            int i = random.nextInt(musics.size());
                            if(i == position){
                                position -=1;
                            }
                            position = i;
                        }
                        if(position > (musics.size()-1)){
                            position = 0;
                        }
                        new playmusic().execute(musics.get(position).getUrl());
                        musicFragment.hinhanh(musics.get(position).getMusicImages(),musics.get(position).getMusicName());
                        getSupportActionBar().setTitle(musics.get(position).getMusicName());
                        seekbarprocess();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },1000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musics.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position <= musics.size()){
                        imgplay.setImageResource(R.drawable.pause);
                        position--;
                        if(position<0){
                            position = musics.size()-1;
                        }
                        if(repeat == true){
                            position += 1;
                        }
                        if(random == true){
                            Random random = new Random();
                            int i = random.nextInt(musics.size());
                            if(i == position){
                                position -=1;
                            }
                            position = i;
                        }
                        new playmusic().execute(musics.get(position).getUrl());
                        musicFragment.hinhanh(musics.get(position).getMusicImages(),musics.get(position).getMusicName());
                        getSupportActionBar().setTitle(musics.get(position).getMusicName());
                        seekbarprocess();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },1000);
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(random == true){
                        random = false;
                        imgshuffer.setImageResource(R.drawable.unshuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.refresh);
                    repeat = true;
                }else {
                    imgrepeat.setImageResource(R.drawable.unrefresh);
                    repeat = false;
                }
            }
        });

        imgshuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(random == false){
                    if(repeat == true){
                        imgrepeat.setImageResource(R.drawable.unrefresh);
                        repeat = false;
                    }
                    imgshuffer.setImageResource(R.drawable.shuffle);
                    random = true;
                }else {
                    imgshuffer.setImageResource(R.drawable.unshuffle);
                    random = false;
                }
            }
        });
    }

    private void seekbarprocess(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    seekBartime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,1);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next=true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },200);
        String musicname = musics.get(position).getMusicName();
        int musicid = musics.get(position).getMusicId();
        Handler handler1 = new Handler();
        MiniViewFragment miniViewFragment = new MiniViewFragment();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    position++;
                    if(musics.size()>0){
                        if(mediaPlayer.isPlaying() || mediaPlayer != null){
                            //position++;
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if( position <= musics.size()){
                            //Toast.makeText(PlayMusicActivity.this, position+"-"+musics.size(), Toast.LENGTH_SHORT).show();
                            imgplay.setImageResource(R.drawable.pause);
                            if(repeat == true){
                                if(position == 0){
                                    position = musics.size();
                                }
                                position -= 1;
                            }
                            if(random == true){
                                Random random = new Random();
                                int i = random.nextInt(musics.size());
                                if(i == position){
                                    position -=1;
                                }
                                position = i;
                            }
                            if(position > (musics.size()-1)){
                                //position = 0;
                                if(trongds){
                                    position = 0;
                                }
                                else{

                                    miniViewFragment.lockbutton();
                                    if(ngoaiplaylist==false){
                                        if(emotion == false){
                                            emotionDialog.updateemotion(Gravity.CENTER,PlayMusicActivity.this,musicid,musicname);
                                        }
                                        else{
                                            Open_Mini_player=false;
                                            finish();
                                        }
                                    }
                                    //Log.d("TAG kiem tra",musics.get(position).getMusicId()+" "+musics.get(position).getMusicName());
                                }

                            }
                            if(trongds==true){
                                new playmusic().execute(musics.get(position).getUrl());
                                musicFragment.hinhanh(musics.get(position).getMusicImages(),musics.get(position).getMusicName());
                                getSupportActionBar().setTitle(musics.get(position).getMusicName());
                            }
                        }
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);

                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },1000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {

                    handler1.postDelayed(this,1000);


                }
            }
        },1000);
    }

    class playmusic extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                mediaPlayer = new MediaPlayer();

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                //Toast.makeText(PlayMusicActivity.this, s, Toast.LENGTH_SHORT).show();
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();

            } catch (IOException e) {e.printStackTrace();}
            //getdata(s);
            /*Toast.makeText(PlayMusicActivity.this, getdata(s).getMusicName(), Toast.LENGTH_SHORT).show();*/
            //Toast.makeText(PlayMusicActivity.this, musics.get(position).getMusicName(), Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = load.edit();
            editor.remove("musicimg");
            editor.remove("musicname");
            editor.remove("urlmusic");
            editor.remove("position");
            editor.apply();
            mediaPlayer.start();
            if(emotion == false){
                updatetotalview(musics.get(position).getMusicId(),musics.get(position).getMusicName());
            }
            else {
                int emotionid = load.getInt("emotionid",0);
                Log.d("Tag test",emotionid+" success");
                UpdateEmotion(musics.get(position).getMusicId(),emotionid,musics.get(position).getMusicName());
            }
            TotalTimeMusic();
            seekbarprocess();
            Open_Mini_player=true;
            miniviewprocess();
        }
    }

    private void updatetotalview(int musicid,String musicname){
        final int userid = load.getInt("userid",0);
        Call<List<Ketqua>> rmusics = dataService.capnhatluotnghe(musicid,userid);
        rmusics.enqueue(new Callback<List<Ketqua>>() {
            @Override
            public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                ArrayList<Ketqua> ketqua = (ArrayList<Ketqua>) response.body();
                if(ketqua == null){
                    Log.d("Tag Update TV fail", musicid+"-"+musicname+"-"+userid+"");
                }else{
                String success = ketqua.get(0).getSuccess();
                if(success.equals("success")){
                    Log.d("Tag Update Total View",musicname+" success");
                }else{
                    Log.d("Tag Update Total View",musicname+" lỗi");
                }
            }
            }
            @Override
            public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                Log.d("Tag Update TV fail",t.getMessage());
            }
        });
    }

    private void UpdateEmotion(int music_id,int emotion_id,String musicname){
        int user_id = load.getInt("userid",0);
        Call<List<Ketqua>> emotions = dataService.capnhatcamxuc2(music_id,user_id,emotion_id);
        emotions.enqueue(new Callback<List<Ketqua>>() {
            @Override
            public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                ArrayList<Ketqua> ketqua = (ArrayList<Ketqua>) response.body();
                if(ketqua == null){
                    Log.d("Tag Update Emotion fail", music_id+"-"+musicname+"-"+user_id+"-"+emotion_id);
                }else{
                    String success = ketqua.get(0).getSuccess();
                    if(success.equals("success")){
                        Log.d("Tag  Update Emotion",musicname+" success");
                        /*Open_Mini_player=false;
                        finish();*/
                    }else{
                        Log.d("Tag  Update Emotion",musicname+" lỗi");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                Log.d("Tag Update Emotion fail", t.getMessage());
            }
        });
    }

    private void TotalTimeMusic() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        txttoltaltime.setText(dateFormat.format(mediaPlayer.getDuration()));
        seekBartime.setMax(mediaPlayer.getDuration());
    }
}