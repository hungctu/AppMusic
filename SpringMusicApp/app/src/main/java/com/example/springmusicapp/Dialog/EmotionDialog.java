package com.example.springmusicapp.Dialog;

import static com.example.springmusicapp.Activity.PlayMusicActivity.mediaPlayer;
import static com.example.springmusicapp.Activity.MainActivity.Open_Mini_player;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.springmusicapp.Activity.MainActivity;
import com.example.springmusicapp.Model.Ketqua;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmotionDialog {
    DataService dataService = APIService.getdataservice();
    RelativeLayout happy,surprise,neutral,sad,fear,disgusted,angry;
    SharedPreferences load;
    public void updateemotion(int gravity, Context context,int musicid,String musicname){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.emotion_dialog);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        happy = dialog.findViewById(R.id.happy);
        surprise = dialog.findViewById(R.id.surprise);
        neutral = dialog.findViewById(R.id.neutral);
        sad = dialog.findViewById(R.id.sad);
        fear = dialog.findViewById(R.id.fear);
        disgusted = dialog.findViewById(R.id.disgusted);
        angry = dialog.findViewById(R.id.angry);

        load = context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
        int userid = load.getInt("userid",0);
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là happy.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,7,musicname,context);
                dialog.dismiss();
            }
        });
        
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là surprise.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,1,musicname,context);
                dialog.dismiss();
            }
        });
        
        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là neutral.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,4,musicname,context);
                dialog.dismiss();
            }
        });
        
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là sad.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,5,musicname,context);
                dialog.dismiss();
            }
        });
        
        fear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là fear.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,2,musicname,context);
                dialog.dismiss();
            }
        });
        
        disgusted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là disgusted.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,6,musicname,context);
                dialog.dismiss();
            }
        });
        
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cảm xúc hiện tại của bạn là angry.\nCảm ơn bạn đã đóng góp ý kiến", Toast.LENGTH_SHORT).show();
                UpdateEmotion(musicid,userid,3,musicname,context);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void UpdateEmotion(int music_id,int user_id,int emotion_id,String musicname,Context context){
        Call<List<Ketqua>> emotions = dataService.capnhatcamxuc(music_id,user_id,emotion_id);
        emotions.enqueue(new Callback<List<Ketqua>>() {
            @Override
            public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                ArrayList<Ketqua> ketqua = (ArrayList<Ketqua>) response.body();
                if(ketqua == null){
                    Log.d("Tag Update Emotion fail", music_id+"-"+musicname+"-"+user_id+"-"+emotion_id);
                }else{
                String success = ketqua.get(0).getSuccess();
                if(success.equals("success")){
                    Log.d("Tag  Update Emotion",musicname+" success");
                    if(mediaPlayer != null){
                        Log.d("Tag  Update Emotion","media player is null");
                        Open_Mini_player = false;
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    Open_Mini_player = false;
                    context.startActivity(new Intent(context, MainActivity.class));

                }else{
                    Log.d("Tag  Update Emotion",musicname+" lỗi");
                }
                }
            }

            @Override
            public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                Log.d("Tag Update Emotion fail", t.getMessage());
            }
        });
    }
}
