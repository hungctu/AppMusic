package com.example.springmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.LoginActivity;
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmotionMusicAdapter extends RecyclerView.Adapter<EmotionMusicAdapter.ViewholderEmotionMusic>{

    Context context;
    ArrayList<Music> arrayList;

    public EmotionMusicAdapter(Context context, ArrayList<Music> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewholderEmotionMusic onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.emotionmusicadapter,parent,false);
        return new ViewholderEmotionMusic(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderEmotionMusic holder, int position) {
        Music music = arrayList.get(position);
        holder.tenbaihat.setText(music.getMusicName());
        holder.tencasi.setText(music.getSingerName());
        Picasso.with(context).load(music.getMusicImages()).into(holder.hinhbaihat);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewholderEmotionMusic extends RecyclerView.ViewHolder{

        ImageView hinhbaihat,menu;
        TextView tenbaihat,tencasi;

        public ViewholderEmotionMusic(@NonNull View itemView) {
            super(itemView);

            hinhbaihat = itemView.findViewById(R.id.hinhbhemotion);
            tenbaihat = itemView.findViewById(R.id.tenbaihatemotion);
            tencasi = itemView.findViewById(R.id.tencasiemotion);
            menu = itemView.findViewById(R.id.menumusicemotion);

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences load =context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
                    int id = load.getInt("userid",0);
                    if(id==0){
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                    else {
                        SharedPreferences save = context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = save.edit();
                        editor.putInt("musicid",arrayList.get(getPosition()).getMusicId());
                        editor.commit();
                        Thembaihatvaoplaylist thembaihatvaoplaylist = new Thembaihatvaoplaylist();
                        thembaihatvaoplaylist.dsplaylist(context,arrayList.get(getPosition()).getMusicId());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("emotionmusic",arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
