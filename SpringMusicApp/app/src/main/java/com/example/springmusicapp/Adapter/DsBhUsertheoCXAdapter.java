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
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DsBhUsertheoCXAdapter extends RecyclerView.Adapter<DsBhUsertheoCXAdapter.Viewholderemotionuser>{
    Context context;
    ArrayList<Music> arrayList;

    public DsBhUsertheoCXAdapter(Context context, ArrayList<Music> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholderemotionuser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dsbhusertheocxadapter,parent,false);
        return new Viewholderemotionuser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholderemotionuser holder, int position) {
        Music music = arrayList.get(position);
        holder.txtmusic.setText(music.getMusicName());
        holder.txtsinger.setText(music.getSingerName());
        Picasso.with(context).load(music.getMusicImages()).into(holder.imagesemotionmusic);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholderemotionuser extends RecyclerView.ViewHolder{


        ImageView imagesemotionmusic;
        TextView txtmusic,txtsinger;

        public Viewholderemotionuser(@NonNull View itemView) {
            super(itemView);

            imagesemotionmusic = itemView.findViewById(R.id.imagesemotionmusicu);
            txtmusic = itemView.findViewById(R.id.tenemotionmusicu);
            txtsinger = itemView.findViewById(R.id.tenemotionsingeru);

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
                    intent.putExtra("emotionmusic",arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
