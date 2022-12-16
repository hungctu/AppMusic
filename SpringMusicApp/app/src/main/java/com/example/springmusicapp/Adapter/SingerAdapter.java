package com.example.springmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.DanhSachActivity;
import com.example.springmusicapp.Model.Singer;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHoldersinger>{

    Context context;
    ArrayList<Singer> singers;

    public SingerAdapter(Context context, ArrayList<Singer> singers) {
        this.context = context;
        this.singers = singers;
    }

    @NonNull
    @Override
    public ViewHoldersinger onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.casi,parent,false);
        return new ViewHoldersinger(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldersinger holder, int position) {
        Singer singer = singers.get(position);
        holder.singernames.setText(singer.getSingerName());
        Picasso.with(context).load(singer.getSingerImages()).into(holder.singerimages);

    }

    @Override
    public int getItemCount() {
        return singers.size();
    }

    public class ViewHoldersinger extends RecyclerView.ViewHolder{

        /*CardView cardView;*/
        CircleImageView singerimages;
        TextView singernames;

        public ViewHoldersinger(@NonNull View itemView) {
            super(itemView);

            /*cardView = itemView.findViewById(R.id.cardviewcasi);*/
            singernames = itemView.findViewById(R.id.txtcasi);
            singerimages = itemView.findViewById(R.id.imagecasi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachActivity.class);
                    intent.putExtra("baihattheocasi",singers.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
