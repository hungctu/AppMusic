package com.example.springmusicapp.Activity;

import static com.example.springmusicapp.Activity.PlayMusicActivity.mediaPlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.springmusicapp.Adapter.MainviewpaperAdapter;
import com.example.springmusicapp.Fragment.Home_fragment;
import com.example.springmusicapp.Fragment.Personalized_fragment;
import com.example.springmusicapp.Fragment.Search_fragment;
import com.example.springmusicapp.R;
import com.google.android.material.tabs.TabLayout;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    static{
        if(OpenCVLoader.initDebug()){
            Log.d("Tag Main Activity: " ,"OpenCv is loaded");
        }
        else {
            Log.d("Tag Main Activity: " ,"OpenCv failed");
        }
    }

    public static TabLayout tabLayout;
    ViewPager viewPager;
    static FrameLayout frameLayout;
    public MainviewpaperAdapter mainviewpaperAdapter = new MainviewpaperAdapter(getSupportFragmentManager());
    SharedPreferences save;
    public static Boolean Open_Mini_player = false;
    public static String link;
    public static String anhbaihat;
    public static String tenbaihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        hienminiview();
        trangthai();
        init();
        kiemtra();
    }

    private void trangthai() {
        save = getSharedPreferences("luuthongtindangnhap",MODE_PRIVATE);
        save.getInt("userid",0);
        save.getString("fullname","");
        save.getString("tendangnhap","");
        save.getString("matkhau","");
        save.getInt("musicid",0);
        save.getInt("playlistid",0);
        save.getBoolean("trangthai",false);
        save.getString("urlmusic","");
        save.getString("musicimg","");
        save.getString("musicname","");
        save.getInt("position",-1);
        save.getInt("emotionid",0);

        /*SharedPreferences.Editor editor = save.edit();
        editor.remove("userid");
        editor.commit();*/
    }

    private void init(){
        mainviewpaperAdapter.addfragment(new Home_fragment(),"Trang chủ");
        mainviewpaperAdapter.addfragment(new Personalized_fragment(),"Cá nhân");
        mainviewpaperAdapter.addfragment(new Search_fragment(),"Tìm kiếm");
        viewPager.setAdapter(mainviewpaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.canhan);
        tabLayout.getTabAt(2).setIcon(R.drawable.searching);
    }
    private void anhxa() {
        viewPager = (ViewPager) findViewById(R.id.Viewpaper);
        tabLayout = (TabLayout) findViewById(R.id.Tablayout);
        frameLayout = findViewById(R.id.framelayout);
        frameLayout.setVisibility(View.GONE);
    }

    private void kiemtra() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                    if(save.getInt("userid",0)==0){
                        startActivity(intent);
                    }
                    //tabLayout.getTabAt(0).select();
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
    }
    protected static void hienminiview(){
        if(Open_Mini_player==true){
            frameLayout.setVisibility(View.VISIBLE);
        }
        else {
            frameLayout.setVisibility(View.GONE);
        }
    }
}