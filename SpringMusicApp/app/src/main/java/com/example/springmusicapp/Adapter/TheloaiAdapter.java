package com.example.springmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Activity.DanhSachActivity;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheloaiAdapter extends RecyclerView.Adapter<TheloaiAdapter.ViewHolderT>{

    Context context;
    ArrayList<Category> arrayList;

    public TheloaiAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolderT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.theloai,parent,false);
        return  new ViewHolderT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderT holder, int position) {
        Category category =arrayList.get(position);
        holder.tentheloai.setText(category.getCategoryName());
        Picasso.with(context).load(category.getCategoryImages()).into(holder.theloaiimages);

        /*holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachActivity.class);
                intent.putExtra("baihattheotheloai",category);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderT extends RecyclerView.ViewHolder{

        ImageView theloaiimages ;
        TextView tentheloai;
        RelativeLayout layout;
        public ViewHolderT(@NonNull View itemView) {
            super(itemView);

            theloaiimages = itemView.findViewById(R.id.theloaiimages);
            tentheloai = itemView.findViewById(R.id.ImageViewText);
            layout = itemView.findViewById(R.id.layoutrandomc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachActivity.class);
                    intent.putExtra("baihattheotheloai",arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }


}
