package com.example.androiddata;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class Service_Bound1 extends Service {
    public Service_Bound1() {

    }

    IBinder mBinder = new MyBinder();

    class MyBinder extends Binder {
        Service_Bound1 getService() { // 서비스 객체를 리턴
            return Service_Bound1.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 액티비티에서 bindService() 를 실행하면 호출됨
        // 리턴한 IBinder 객체는 서비스와 클라이언트 사이의 인터페이스 정의한다
        return mBinder; // 서비스 객체를 리턴
    }

    int getRan() { // 임의 랜덤값을 리턴하는 메서드
        return new Random().nextInt();
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
