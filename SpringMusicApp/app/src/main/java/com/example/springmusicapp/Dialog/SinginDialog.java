package com.example.springmusicapp.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class SinginDialog {
    DataService dataService = APIService.getdataservice();
    public void SinginDialog(int gravity,Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.singin_dialog);
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

        EditText fullname = dialog.findViewById(R.id.fullnamesignin);
        EditText usernamesign = dialog.findViewById(R.id.usernamesignin);
        EditText passwordsign = dialog.findViewById(R.id.passsingin);
        EditText confirm = dialog.findViewById(R.id.repass);
        Button btnsigin = dialog.findViewById(R.id.btnlogin);
        Button btnhuysign = dialog.findViewById(R.id.btnhuylogin);

        btnsigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = fullname.getText().toString();
                String usnsignin = usernamesign.getText().toString().trim();
                String passssignin = passwordsign.getText().toString().trim();
                String conf = confirm.getText().toString().trim();
                if(fname.equals("")|| usnsignin.equals("")|| passssignin.equals("")||conf.equals("")){
                    Toast.makeText(context, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!passssignin.equals(conf)){
                        Toast.makeText(context, "Password và Confirm phải giống nhau", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Call<List<Ketqua>> userModelCall = dataService.Signin(fname,usnsignin,passssignin);
                        userModelCall.enqueue(new Callback<List<Ketqua>>() {
                            @Override
                            public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                                ArrayList<Ketqua> ketqua = (ArrayList<Ketqua>) response.body();
                                /*if(ketqua.isEmpty()){
                                    Toast.makeText(context, "123", Toast.LENGTH_SHORT).show();
                                }*/
                                String success = ketqua.get(0).getSuccess();
                                if(success.equals("DaTonTai")){
                                    Toast.makeText(context, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                }else if(success.equals("")){
                                    Toast.makeText(context, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    intent(context);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Ketqua>> call, Throwable t) {
                                Log.d("TAG", "777");
                            }
                        });
                    }
                }
            }
        });

        btnhuysign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(context);
            }
        });

        dialog.show();
    }
    private void intent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
