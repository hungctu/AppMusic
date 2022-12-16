package com.example.springmusicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Dialog.Thembaihatvaoplaylist;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlaylistthembaihatAdapter extends RecyclerView.Adapter<PlaylistthembaihatAdapter.ViewHolderpltbh>{
    static public int itemposition=-1;
    Context context;
    ArrayList<MusicList> musicLists;

    public PlaylistthembaihatAdapter(Context context, ArrayList<MusicList> musicLists) {
        this.context = context;
        this.musicLists = musicLists;
    }

    @NonNull
    @Override
    public ViewHolderpltbh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlistthembaihat,parent,false);
        return new ViewHolderpltbh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderpltbh holder, int position) {
        MusicList musicList = musicLists.get(position);
        holder.textView.setText(musicList.getMusicListName());
    }

    @Override
    public int getItemCount() {
        return musicLists.size();
    }

    public class ViewHolderpltbh extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        CircleImageView imageView;
        TextView textView;

        public ViewHolderpltbh(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.thembaihatlayout);
            imageView = itemView.findViewById(R.id.playlisticon);
            textView = itemView.findViewById(R.id.pltxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Thembaihatvaoplaylist thembaihatvaoplaylist = new Thembaihatvaoplaylist();
                    itemposition=getPosition();
                    //Toast.makeText(context, ""+itemposition, Toast.LENGTH_SHORT).show();
                    thembaihatvaoplaylist.thembaihat(context,musicLists.get(getPosition()).getMusicListId());
                }
            });
        }
    }
}
