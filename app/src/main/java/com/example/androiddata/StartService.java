package com.example.androiddata;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class StartService extends Service {
    private MediaPlayer mediaPlayer=null;
    public StartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //매번 start될때마다.
        Log.v("test","on start command");
        mediaPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("test", "onStart()");
        super.onStart(intent, startId);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
    }





    @Override
    public void onCreate(){
        Log.v("test","create test");
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.setLooping(false);
        }catch (Exception e){
            Log.v("test",e+"의 오류");
        }
        //처음 service가 create 된 경우ㅡ.;
    }
    @Override
    public void onDestroy(){
        mediaPlayer.stop();
    }

}
