package com.example.springmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class User_Music_Adapter extends RecyclerView.Adapter<User_Music_Adapter.UMViewHolder>{

    Context context;
    ArrayList<Music> arrayList;

    public User_Music_Adapter(Context context, ArrayList<Music> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_music_adapter,parent,false);
        return new UMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UMViewHolder holder, int position) {
        Music music = arrayList.get(position);
        holder.txtmusic.setText(music.getMusicName());
        holder.txttencasi.setText(music.getSingerName());
        Picasso.with(context).load(music.getMusicImages()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class UMViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,menu;
        TextView txtmusic,txttencasi;

        public UMViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagesmusic_user);
            txtmusic = itemView.findViewById(R.id.txttenbaihat_user);
            txttencasi = itemView.findViewById(R.id.txttencasi_user);
            menu = itemView.findViewById(R.id.menumusic_user);

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
                        SharedPreferences.Editor editor = load.edit();
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
                    intent.putExtra("baihat",arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
