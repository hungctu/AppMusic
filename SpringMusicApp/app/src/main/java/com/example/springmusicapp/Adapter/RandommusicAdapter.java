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
import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RandommusicAdapter extends RecyclerView.Adapter<RandommusicAdapter.ViewHolderR>{

    Context context;
    ArrayList<Music> arrayList;

    public RandommusicAdapter(Context context, ArrayList<Music> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolderR onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.randommusic,parent,false);
        return new ViewHolderR(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderR holder, int position) {
        Music music = arrayList.get(position);
        holder.txtrandommusic.setText(music.getMusicName());
        holder.txttencasi.setText(music.getSingerName());
        Picasso.with(context).load(music.getMusicImages()).into(holder.imageViewrandommusic);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderR extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        ImageView imageViewrandommusic,menu;
        TextView txtrandommusic,txttencasi;

        public ViewHolderR(@NonNull View itemView) {
            super(itemView);

            imageViewrandommusic = itemView.findViewById(R.id.imagesrandommusic);
            txtrandommusic = itemView.findViewById(R.id.txttenbaihat);
            txttencasi = itemView.findViewById(R.id.txttencasi);
            relativeLayout = itemView.findViewById(R.id.posiotionrandommusic);
            menu = itemView.findViewById(R.id.menumusic);

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
