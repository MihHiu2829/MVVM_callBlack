package com.example.mvvm_callblack.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.mvvm_callblack.R;
import com.example.mvvm_callblack.ViewModel.m001VM;
import com.example.mvvm_callblack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
        protected ActivityMainBinding binding ;
        private m001VM m001VM ;
        private Handler mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                binding.progressId.setProgress(msg.arg1);
                        binding.tvCountDown.setText(String.format("%s",msg.arg1));
                return false;
            }
        });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); 
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        m001VM = new ViewModelProvider(this).get(com.example.mvvm_callblack.ViewModel.m001VM.class);
        m001VM.setCallBack(new m001VM.CountDownCallBack() {
            @Override
            public void upDateUI(int i) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.progressId.setProgress(i);
//                        binding.tvCountDown.setText(String.format("%s",i));
//                    }
//                });
                Message msg = new Message() ;
                msg.arg1 = i  ;
                msg.setTarget(mHandler);
                msg.sendToTarget();
            }
        });
        binding.progressId.setMax(10);
        binding.btStart.setOnClickListener(v -> startCounting());
    }

    private void startCounting() {
        m001VM.startCountDown();
    }
}