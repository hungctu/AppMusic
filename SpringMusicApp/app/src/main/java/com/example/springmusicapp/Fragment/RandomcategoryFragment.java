package com.example.springmusicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.springmusicapp.Adapter.TheloaiAdapter;
import com.example.springmusicapp.Activity.AllCategoryActivity;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Model.Category;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomcategoryFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    TextView txtxemthem;
    TheloaiAdapter theloaiAdapter;
    Runnable runnable;
    Handler handler;
    int item = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.randomcategoryfragment,container,false);

        anhxa();
        getdata();

        return view;
    }

    private void anhxa() {
        recyclerView = view.findViewById(R.id.srollviewtheloai);
        txtxemthem = view.findViewById(R.id.txtxemthemtheloai);

        txtxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllCategoryActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getdata() {
        DataService dataService = APIService.getdataservice();
        Call<List<Category>> randomcall= dataService.getRandomcategory();
        randomcall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                ArrayList<Category> randomcategories = (ArrayList<Category>) response.body();
                theloaiAdapter = new TheloaiAdapter(getActivity(),randomcategories);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(theloaiAdapter);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        item++;
                        if(item>=5){
                            item = 0;
                        }
                        recyclerView.smoothScrollToPosition(item);
                        handler.postDelayed(runnable,10000);
                    }
                };
                handler.postDelayed(runnable,10000);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("TAGRandomC!", t.getMessage());

            }
        });
    }
}
