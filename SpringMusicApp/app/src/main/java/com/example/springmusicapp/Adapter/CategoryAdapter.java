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
import com.example.springmusicapp.Model.Category;
import com.example.springmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolderC>{
    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolderC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.categoryadapter,parent,false);
        return new ViewHolderC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderC holder, int position) {
        Category category =categories.get(position);
        holder.catetxt.setText(category.getCategoryName());
        Picasso.with(context).load(category.getCategoryImages()).into(holder.cateimages);


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolderC extends RecyclerView.ViewHolder{

        ImageView cateimages;
        TextView catetxt;
        RelativeLayout relativeLayout;

        public ViewHolderC(@NonNull View itemView) {
            super(itemView);

            cateimages = itemView.findViewById(R.id.Categoryimages);
            catetxt = itemView.findViewById(R.id.CategoryText);
            relativeLayout = itemView.findViewById(R.id.categoryLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachActivity.class);
                    intent.putExtra("baihattheotheloai",categories.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
