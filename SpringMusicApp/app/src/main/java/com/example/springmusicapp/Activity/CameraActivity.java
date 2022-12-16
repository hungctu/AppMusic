package com.example.springmusicapp.Activity;

import static com.example.springmusicapp.EmotionCode.facialExpressionRecognition.emo;
import static com.example.springmusicapp.EmotionCode.facialExpressionRecognition.emoid;
import static com.example.springmusicapp.EmotionCode.facialExpressionRecognition.kt;
import static com.example.springmusicapp.EmotionCode.facialExpressionRecognition.songuoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.springmusicapp.EmotionCode.facialExpressionRecognition;
import com.example.springmusicapp.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.IOException;

public class CameraActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "MainActivity";
    private Mat mRgba;
    private Mat mGrey;
    private  CameraBridgeViewBase mOpencvCameraView;
    int MY_PERMISSION_REQUEST_CAMERA = 0;

    JavaCameraView javaCameraView;
    ImageButton camera,switchcamera;
    private  int mCameraId = 0;

    private facialExpressionRecognition facialExpressionRecognition;

    private BaseLoaderCallback mloadcallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch(status){
                case LoaderCallbackInterface.SUCCESS:{
                    Log.i(TAG,"Opencv is loaded");
                    mOpencvCameraView.enableView();
                }
                default:{
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    public  CameraActivity(){
        Log.i(TAG,"Instantiated new "+this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(CameraActivity.this,new String[]{Manifest.permission.CAMERA},MY_PERMISSION_REQUEST_CAMERA);
        }

        setContentView(R.layout.activity_camera);

        anhxa();

        try{
            int inputSize=48;
            facialExpressionRecognition = new facialExpressionRecognition(getAssets(),CameraActivity.this,
                    "model300.tflite",inputSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        eventclick();
    }

    private void eventclick() {
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(songuoi>0&&songuoi<2){
                    Intent intent = new Intent(CameraActivity.this,EmotionSearchActivity.class);
                    intent.putExtra("emotion",emo);
                    intent.putExtra("emotionid",emoid);
                    startActivity(intent);
                    Toast.makeText(CameraActivity.this, emo, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CameraActivity.this, "Chỉ chụp 1 người trong khung khình", Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapCamera();
            }
        });
    }

    private void swapCamera() {
        mCameraId = mCameraId^1;
        if(mCameraId == 1){
            kt = true;
        }
        else {
            kt = false;
        }
        mOpencvCameraView.disableView();
        mOpencvCameraView.setCameraIndex(mCameraId);
        mOpencvCameraView.enableView();
    }

    private void anhxa() {
        camera = findViewById(R.id.laycamxuc);
        switchcamera = findViewById(R.id.switch_camera);

        mOpencvCameraView=(CameraBridgeViewBase) findViewById(R.id.cameraview);
        mOpencvCameraView.setVisibility(SurfaceView.VISIBLE);
        //mOpencvCameraView.setCameraIndex(0);
        mOpencvCameraView.setCameraPermissionGranted();
        mOpencvCameraView.setCvCameraViewListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, mloadcallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mloadcallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mOpencvCameraView!=null){
            mOpencvCameraView.disableView();
        }
    }

    public void onDestroy(){
        super.onDestroy();
        if(mOpencvCameraView !=null){
            mOpencvCameraView.disableView();
        }
    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height,width, CvType.CV_8UC4);
        mGrey = new Mat(height,width,CvType.CV_8UC1);
    }

    public void onCameraViewStopped(){
        mRgba.release();
    }


    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame){
        mRgba = inputFrame.rgba();
        mGrey = inputFrame.gray();

        mRgba = facialExpressionRecognition.recognizeImage(mRgba);

        return mRgba;
    }

}