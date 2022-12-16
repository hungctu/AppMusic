package com.example.springmusicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.springmusicapp.Adapter.AdAdepter;
import com.example.springmusicapp.Model.Advertisement;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ad_fragment extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator indicator;
    AdAdepter adAdepter;
    Runnable runnable;
    Handler handler;
    int item;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.adfragment,container,false);
        anhxa();
        Getdata();
        return view;
    }

    private void anhxa() {
        viewPager =  view.findViewById(R.id.adflipper);
        indicator = view.findViewById(R.id.Indicator);
    }

    private void Getdata() {
        DataService dataservice = APIService.getdataservice();
        Call<List<Advertisement>> listCall = dataservice.getAdData();
        listCall.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                ArrayList<Advertisement> ad = (ArrayList<Advertisement>) response.body();

                adAdepter = new AdAdepter(getActivity(),ad);

                viewPager.setAdapter(adAdepter);
                indicator.setViewPager(viewPager);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        item = viewPager.getCurrentItem();
                        item++;
                        if(item>=viewPager.getAdapter().getCount()){
                            item = 0;
                        }
                        viewPager.setCurrentItem(item,true);
                        handler.postDelayed(runnable,10000);
                    }
                };
                handler.postDelayed(runnable,10000);
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
    }
}
