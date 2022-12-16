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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.LoginActivity;
import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopMusicAdapter extends  RecyclerView.Adapter<TopMusicAdapter.Viewholder>{
    Context context;
    ArrayList<Music> arrayList;

    public TopMusicAdapter(Context context, ArrayList<Music> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topmusic,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Music topmusic = arrayList.get(position);
        holder.txtsinger.setText(topmusic.getSingerName());
        holder.txtmusic.setText(topmusic.getMusicName());
        Picasso.with(context).load(topmusic.getMusicImages()).into(holder.imagestopmusic);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imagestopmusic;
        TextView txtmusic,txtsinger;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imagestopmusic = itemView.findViewById(R.id.imagestopmusic);
            txtmusic = itemView.findViewById(R.id.tentopmusic);
            txtsinger = itemView.findViewById(R.id.tentopmusicsinger);
            cardView = itemView.findViewById(R.id.cardviewtopmusic);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
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
                    return false;
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
