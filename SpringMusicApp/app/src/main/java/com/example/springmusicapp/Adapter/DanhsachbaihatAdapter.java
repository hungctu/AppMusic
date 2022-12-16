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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolderds>{

    Context context;
    ArrayList<Music> musics;

    public DanhsachbaihatAdapter(Context context, ArrayList<Music> musics) {
        this.context = context;
        this.musics = musics;
    }

    @NonNull
    @Override
    public ViewHolderds onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danhsachbaihat,parent,false);
        return new ViewHolderds(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderds holder, int position) {
        Music music = musics.get(position);
        holder.tenbaihat.setText(music.getMusicName());
        holder.tencasi.setText(music.getSingerName());
        Picasso.with(context).load(music.getMusicImages()).into(holder.hinhbaihat);


    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class ViewHolderds extends RecyclerView.ViewHolder{

        ImageView hinhbaihat,menu;
        TextView tenbaihat,tencasi;

        public ViewHolderds(@NonNull View itemView) {
            super(itemView);
            hinhbaihat = itemView.findViewById(R.id.hinhbhtheods);
            tenbaihat = itemView.findViewById(R.id.tenbaihattheods);
            tencasi = itemView.findViewById(R.id.tencasi);
            menu = itemView.findViewById(R.id.menumusicds);

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("baihat",musics.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }


    }
}
