package com.example.springmusicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;

import com.example.springmusicapp.Dialog.LoginDialog;
import com.example.springmusicapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.LoginDialog(Gravity.CENTER,this);
    }
}