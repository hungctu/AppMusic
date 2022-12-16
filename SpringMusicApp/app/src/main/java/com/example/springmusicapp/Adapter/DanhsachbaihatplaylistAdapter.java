package com.example.springmusicapp.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.DanhSachActivity;
import com.example.springmusicapp.Model.Music;
import com.example.springmusicapp.Activity.PlayMusicActivity;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachbaihatplaylistAdapter extends RecyclerView.Adapter<DanhsachbaihatplaylistAdapter.ViewHolderdspl>{

    DanhSachActivity context;
    ArrayList<Music> musics;

    public DanhsachbaihatplaylistAdapter(DanhSachActivity context, ArrayList<Music> musics) {
        this.context = context;
        this.musics = musics;
    }

    @NonNull
    @Override
    public ViewHolderdspl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.danhsachbaihatplaylist,parent,false);
        return new ViewHolderdspl(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderdspl holder, int position) {
        Music music = musics.get(position);
        holder.tenbaihat.setText(music.getMusicName());
        holder.tencasi.setText(music.getSingerName());
        Picasso.with(context).load(music.getMusicImages()).into(holder.hinhbaihat);

    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class ViewHolderdspl extends RecyclerView.ViewHolder{

        ImageView hinhbaihat,menu;
        TextView tenbaihat,tencasi;

        public ViewHolderdspl(@NonNull View itemView) {
            super(itemView);
            hinhbaihat = itemView.findViewById(R.id.hinhbhtheodsc);
            tenbaihat = itemView.findViewById(R.id.tenbaihattheodsc);
            tencasi = itemView.findViewById(R.id.tencasic);
            menu = itemView.findViewById(R.id.deletemusic);

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences load = context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
                    int id = load.getInt("playlistid",0);

                    if(id==0){
                        Toast.makeText(context, "Gặp lỗi!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        context.xoabaihat(musics.get(getPosition()).getMusicId(),id);
                        /*XoabaihatDialog xoabaihatDialog = new XoabaihatDialog();
                        xoabaihatDialog.xoabaihat(context,musics.get(getPosition()).getMusicId(),id);*/
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
