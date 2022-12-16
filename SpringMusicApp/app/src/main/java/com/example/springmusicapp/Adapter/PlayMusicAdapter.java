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
import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.R;

import java.util.ArrayList;

public class PlayMusicAdapter extends  RecyclerView.Adapter<PlayMusicAdapter.ViewHolderPM>{

    Context context;
    ArrayList<Music> musics;

    public PlayMusicAdapter(Context context, ArrayList<Music> musics) {
        this.context = context;
        this.musics = musics;
    }

    @NonNull
    @Override
    public ViewHolderPM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playmusicadapter,parent,false);
        return new ViewHolderPM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPM holder, int position) {
        Music music = musics.get(position);
        holder.tencasi.setText(music.getSingerName());
        holder.tenbaihat.setText(music.getMusicName());
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class ViewHolderPM extends RecyclerView.ViewHolder{

        TextView tenbaihat,tencasi;
        ImageView menu;

        public ViewHolderPM(@NonNull View itemView) {
            super(itemView);

            tenbaihat = itemView.findViewById(R.id.txtviewmusicplaymusic);
            tencasi = itemView.findViewById(R.id.txtviewsingerplaymusic);
            menu = itemView.findViewById(R.id.menumusicpm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("baihat",musics.get(getPosition()));
                    context.startActivity(intent);
                }
            });

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
                        editor.putInt("musicid",musics.get(getPosition()).getMusicId());
                        editor.commit();
                        Thembaihatvaoplaylist thembaihatvaoplaylist = new Thembaihatvaoplaylist();
                        thembaihatvaoplaylist.dsplaylist(context,musics.get(getPosition()).getMusicId());
                    }
                }
            });
        }
    }
}
