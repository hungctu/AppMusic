package com.example.springmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.DanhSachActivity;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.R;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolderpl>{

    Context context;
    ArrayList<MusicList> musicLists;

    public PlaylistAdapter(Context context, ArrayList<MusicList> musicLists) {
        this.context = context;
        this.musicLists = musicLists;
    }

    @NonNull
    @Override
    public ViewHolderpl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlist,parent,false);
        return new ViewHolderpl(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderpl holder, int position) {
        MusicList musicList = musicLists.get(position);
        holder.tenplaylist.setText(musicList.getMusicListName());
        holder.ngaylap.setText(musicList.getMusicListDateCreate().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return musicLists.size();
    }

    public class ViewHolderpl extends RecyclerView.ViewHolder{

        RelativeLayout layout;
        TextView tenplaylist,ngaylap;

        public ViewHolderpl(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.playlistlayout);
            tenplaylist = itemView.findViewById(R.id.tenplaylist);
            ngaylap = itemView.findViewById(R.id.txtdatecreate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachActivity.class);
                    intent.putExtra("baihattheoplaylist",musicLists.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
