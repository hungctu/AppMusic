package com.example.springmusicapp.Fragment;

import static com.example.springmusicapp.Activity.PlayMusicActivity.mediaPlayer;
import static com.example.springmusicapp.Activity.PlayMusicActivity.musics;
import static com.example.springmusicapp.Activity.PlayMusicActivity.position;
import static com.example.springmusicapp.Activity.PlayMusicActivity.random;
import static com.example.springmusicapp.Activity.PlayMusicActivity.repeat;
import static com.example.springmusicapp.Activity.PlayMusicActivity.next;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;


public class MiniViewFragment extends Fragment {

    View view;
    ImageView hinhanh;
    TextView ten;
    static ImageButton btnplay;
    static ImageButton btnnext;
    static ImageButton btnpre;

    public MiniViewFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mini_view, container, false);

            anhxa();
            onResume();


        //eventclick();
        return view;
    }
    public void lockbutton(){
        btnplay.setEnabled(false);
        btnnext.setEnabled(false);
        btnpre.setEnabled(false);
    }
    public void unlockbutton(){
        btnplay.setEnabled(true);
        btnnext.setEnabled(true);
        btnpre.setEnabled(true);
    }
    @Override
    public void onResume() {
        super.onResume();
        final String[] mid = {""};
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(musics.size()>0){
                    if(position < musics.size()){
                    Log.d("test","a"+position);
                    Log.d("tagfadsf",musics.size()+"-"+musics.get(position).getMusicName());
                    ten.setText(musics.get(position).getMusicName());
                    Picasso.with(getActivity()).load(musics.get(position).getMusicImages()).into(hinhanh);
                    handler.removeCallbacks(this);
                    }
                }else {
                    handler.postDelayed(this,200);
                }
            }
        },200);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnplay.setImageResource(R.drawable.play);
                }
                else {
                    mediaPlayer.start();
                    btnplay.setImageResource(R.drawable.pause);
                }
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musics.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position < musics.size()){
                        btnplay.setImageResource(R.drawable.pause);
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
                        ten.setText(musics.get(position).getMusicName());
                        Picasso.with(getActivity()).load(musics.get(position).getMusicImages()).into(hinhanh);
                    }
                }
                btnpre.setClickable(false);
                btnnext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnpre.setClickable(true);
                        btnnext.setClickable(true);
                    }
                },1000);
            }
        });

        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musics.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position < musics.size()){
                        btnplay.setImageResource(R.drawable.pause);
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
                        ten.setText(musics.get(position).getMusicName());
                        Picasso.with(getActivity()).load(musics.get(position).getMusicImages()).into(hinhanh);
                    }
                }
                btnnext.setClickable(false);
                btnpre.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnpre.setClickable(true);
                        btnnext.setClickable(true);
                    }
                },1000);
            }
        });
    }

    private void anhxa() {
        hinhanh = view.findViewById(R.id.imageminiview);
        ten = view.findViewById(R.id.tenminiview);
        btnplay = view.findViewById(R.id.playminiview);
        btnnext = view.findViewById(R.id.nextminiview);
        btnpre = view.findViewById(R.id.preminiview);
        //new playmusic().execute(musics.get(position).getUrl());
    }

    private void getdata(String link,String tenbaihai){
        ten.setText(tenbaihai);
        Picasso.with(getActivity()).load(link).into(hinhanh);
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
            mediaPlayer.start();
            //miniviewprocess();
        }
    }
     static public void miniviewprocess(){
        Log.d("test",position+"faf");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    handler.postDelayed(this,1);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
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

        Handler handler1 = new Handler();
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
                        if( position < musics.size()){
                            //Toast.makeText(PlayMusicActivity.this, position+"-"+musics.size(), Toast.LENGTH_SHORT).show();
                            btnplay.setImageResource(R.drawable.pause);
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
                            /*ten.setText(musics.get(position).getMusicName());
                            Picasso.with(getActivity()).load(musics.get(position).getMusicImages()).into(hinhanh);*/

                        }
                    }
                    btnpre.setClickable(false);
                    btnnext.setClickable(false);

                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnpre.setClickable(true);
                            btnnext.setClickable(true);
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
}