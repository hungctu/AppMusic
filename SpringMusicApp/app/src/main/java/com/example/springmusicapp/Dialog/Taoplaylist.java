package com.example.springmusicapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.springmusicapp.Model.Ketqua;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Taoplaylist {
    DataService dataService = APIService.getdataservice();

    public void taoplaylist(Context context, int musicid, int userid, String fullname) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.taoplaylistdialog);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        EditText tenplaylist = dialog.findViewById(R.id.tenplaylisttxt);
        Button xn = dialog.findViewById(R.id.btntaoplaylist);
        Button huy = dialog.findViewById(R.id.btnhuytaoplaylist);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        xn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenpl = tenplaylist.getText().toString().trim();
                if(tenpl.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên cho playlist", Toast.LENGTH_SHORT).show();
                }
                else {
                    Call<List<Ketqua>> taoplaylist = dataService.taoplaylist(userid,tenpl,musicid);
                    taoplaylist.enqueue(new Callback<List<Ketqua>>() {
                        @Override
                        public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                            ArrayList<Ketqua> ketquas = (ArrayList<Ketqua>) response.body();
                            if(ketquas == null){
                                Toast.makeText(context, musicid+" - "+userid+" -", Toast.LENGTH_SHORT).show();
                            }else{
                                String success = ketquas.get(0).getSuccess();
                                if(success.equals("thatbai")){
                                    Toast.makeText(context, "Tạo playlist thất bại", Toast.LENGTH_SHORT).show();
                                }
                                else if(success.equals("")){
                                    Toast.makeText(context, "Thêm bài hát vào playlist thất bại", Toast.LENGTH_SHORT).show();
                                }else {
                                    dialog.dismiss();
                                }
                            }

                        }
                        @Override
                        public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                            Log.d("TAG tao playlist", t.getMessage());
                        }
                    });
                }
                /*SharedPreferences.Editor editor = load.edit();
                dialog.dismiss();*/
            }
        });

        dialog.show();
    }
}
