package com.wilson.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wilson.handerdemo.R;

public class MainActivity extends AppCompatActivity {




    //在主线程创建一个Handler对象。
    //重写Handler的handleMessage方法，这个就是接收并处理消息的方法。
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //msg就是子线程发送过来的消息。
            Bundle data = msg.getData();
            String wilson = data.getString("wilson");
            Log.d("wilson",wilson);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //创建子线程发送消息到主线程
    public void SendMsgToMain(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程发送一个消息。
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("wilson","value");
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();

    }


    // 发送消息到子线程
    public void sendMsgToThread(View view) {
    }


    Handler mHandler;
    public void statThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建当前线程的Looper
                Looper.prepare();
                mHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };

                Looper.loop();
            }
        }).start();
    }
}
