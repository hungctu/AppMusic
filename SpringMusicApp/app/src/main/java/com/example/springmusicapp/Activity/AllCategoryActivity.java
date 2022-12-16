package com.example.springmusicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.springmusicapp.Adapter.CategoryAdapter;
import com.example.springmusicapp.Model.Category;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        anhxa();
        getdata();
    }

    private void getdata() {
        DataService dataService = APIService.getdataservice();
        Call<List<Category>> category = dataService.getCategory();
        category.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                ArrayList<Category> categories = (ArrayList<Category>) response.body();
                adapter = new CategoryAdapter(AllCategoryActivity.this,categories);

                LinearLayoutManager layoutManager = new LinearLayoutManager(AllCategoryActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("TAG","1000");
            }
        });
    }

    private void anhxa() {
        recyclerView = findViewById(R.id.allcate);
        toolbar = findViewById(R.id.catetoolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("THỂ LOẠI");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}