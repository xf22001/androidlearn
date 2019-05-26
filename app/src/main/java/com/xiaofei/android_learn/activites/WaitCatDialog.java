package com.xiaofei.android_learn.activites;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiaofei.android_learn.widget.CatLoadingView;

public class WaitCatDialog  extends AppCompatActivity {
    private CatLoadingView catLoadingView;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startWaitDialog();
    }

    private void startWaitDialog() {
        catLoadingView = new CatLoadingView();
        catLoadingView.setCancelable(true);
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        catLoadingView.show(getSupportFragmentManager(), "catLoadingView");
                        break;
                    case 1:
                        if(catLoadingView != null) {
                            if (catLoadingView.isResumed()) {
                                catLoadingView.dismiss();
                            };
                            catLoadingView = null;
                            finish();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        });
        thread.start();
    }
}
