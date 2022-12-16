package com.example.springmusicapp.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import androidx.viewpager.widget.ViewPager;

import com.example.springmusicapp.Activity.MainActivity;
import com.example.springmusicapp.Adapter.MainviewpaperAdapter;
import com.example.springmusicapp.Adapter.PlaylistAdapter;
import com.example.springmusicapp.Model.MusicList;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Personalized_fragment extends Fragment {
    View view;
    SharedPreferences load;
    CircleImageView userimages;
    TextView tenuser,dangxuat;
    TabLayout tabLayoutp;
    ViewPager viewPagerp;
    //RecyclerView danhsach;
    PlaylistAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.personalizedfragment,container,false);
        anhxa();

        MainActivity.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                    kiemtra();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                }
            }
        });

        return view;
    }

    private void anhxa() {
        userimages = view.findViewById(R.id.userimages);
        tenuser = view.findViewById(R.id.tenuser);
        dangxuat = view.findViewById(R.id.dangxuat);
        viewPagerp = view.findViewById(R.id.Viewpaperpe);
        tabLayoutp = view.findViewById(R.id.Tablayoutpe);
        //danhsach = view.findViewById(R.id.danhsachbaihattheouser);
        //playlist = view.findViewById(R.id.txtplaylist);
    }
    private void kiemtra() {
        load = this.getContext().getSharedPreferences("luuthongtindangnhap",this.getContext().MODE_PRIVATE);
        if(load.getInt("userid",0)==0){
            Log.d("TAAAAAAG", "CHƯA ĐĂNG NHẬP ");
        }
        else{
            userimages.setImageResource(R.drawable.user);
            tenuser.setText(load.getString("fullname",""));
            //playlist.setText("Playlist");
            dangxuat.setText("Đăng xuất");
            int id = load.getInt("userid",0);
            MainviewpaperAdapter mainviewpaperAdapter = new MainviewpaperAdapter(getActivity().getSupportFragmentManager());
            mainviewpaperAdapter.addfragment(new Playlist_fragment(),"Playlist");
            mainviewpaperAdapter.addfragment(new RecentlySong_fragment(),"Bài hát nghe nhiều");
            viewPagerp.setAdapter(mainviewpaperAdapter);
            tabLayoutp.setupWithViewPager(viewPagerp);
            tabLayoutp.getTabAt(0);
            tabLayoutp.getTabAt(1);
            //getdata(id);

            dangxuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dangxuat();
                }
            });
        }
    }

    private void Dangxuat() {
        SharedPreferences.Editor editor = load.edit();
        editor.remove("userid");
        editor.remove("fullname");
        editor.remove("playlistid");
        editor.commit();
        MainActivity.tabLayout.getTabAt(0).select();
    }

}
