package com.example.springmusicapp.Dialog;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.springmusicapp.Activity.MainActivity;
import com.example.springmusicapp.Model.User;
import com.example.springmusicapp.R;
import com.example.springmusicapp.Service.APIService;
import com.example.springmusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDialog {
    DataService dataService = APIService.getdataservice();

    public void LoginDialog(int gravity, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_dialog);
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

        EditText username = dialog.findViewById(R.id.edittextusername);
        EditText password = dialog.findViewById(R.id.edittextpass);
        TextView signin = dialog.findViewById(R.id.linksignin);
        Button btnlogin = dialog.findViewById(R.id.btnlogin);
        Button btnhuylogin = dialog.findViewById(R.id.btnhuylogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(usn.equals("") || pass.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    Call<List<User>> login = dataService.Login(usn,pass);
                    login.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            ArrayList<User> users = (ArrayList<User>) response.body();

                            if(users.isEmpty()){
                                //Toast.makeText(context, "123 empty", Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, "Tên đăng nhập hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                int id = users.get(0).getUserId();
                                String fullname = users.get(0).getFullname();
                                Log.d("TAG Login ",id+"-"+fullname);
                                SharedPreferences save = context.getSharedPreferences("luuthongtindangnhap",context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = save.edit();
                                editor.putInt("userid",id);
                                editor.putString("fullname",fullname);
                                editor.commit();
                                intent(context);
                            }

                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Log.d("TAG Login fail",t.getMessage());
                        }
                    });
                }
            }
        });

        btnhuylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                intent(context);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SinginDialog singinDialog = new SinginDialog();
                singinDialog.SinginDialog(gravity,context);
            }
        });

        dialog.show();
    }
    private void intent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
