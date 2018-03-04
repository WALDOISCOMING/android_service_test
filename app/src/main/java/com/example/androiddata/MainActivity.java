package com.example.androiddata;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.app.Service.START_FLAG_RETRY;

public class MainActivity extends AppCompatActivity {

    Service_Bound1 bs; // 서비스 객체
    boolean isService = false; // 서비스 중인 확인용

    ServiceConnection conn = new ServiceConnection() {
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
// 서비스와 연결되었을 때 호출되는 메서드
// 서비스 객체를 전역변수로 저장
            Service_Bound1.MyBinder mb = (Service_Bound1.MyBinder) service;
            bs = mb.getService(); // 서비스가 제공하는 메소드 호출하여
// 서비스쪽 객체를 전달받을수 있슴
            isService = true;
        }
        public void onServiceDisconnected(ComponentName name) {
// 서비스와 연결이 끊겼을 때 호출되는 메서드
            isService = false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.start_button);
        Button b2 = (Button) findViewById(R.id.stop_button);


        Button bind1 = (Button) findViewById(R.id.bind_start_button);
        Button bind2 = (Button) findViewById(R.id.bind_stop_button);
        Button bind3 = (Button) findViewById(R.id.bind_data_button);

        bind1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // 서비스 시작
                Intent intent = new Intent(
                        MainActivity.this, // 현재 화면
                        Service_Bound1.class); // 다음넘어갈 컴퍼넌트

                bindService(intent, // intent 객체
                        conn, // 서비스와 연결에 대한 정의
                        Context.BIND_AUTO_CREATE);
            }
        });
        bind2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // 서비스 종료

                unbindService(conn); // 서비스 종료
            }
        });
        bind3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//서비스데이터확인
                if (!isService) {
                    Toast.makeText(getApplicationContext(),
                            "서비스중이 아닙니다, 데이터받을수 없음",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                int num = bs.getRan();//서비스쪽 메소드로 값 전달 받아 호출
                Toast.makeText(getApplicationContext(),
                        "받아온 데이터 : " + num,
                        Toast.LENGTH_LONG).show();
            }
        });









        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 서비스 시작하기
                Log.d("test", "액티비티-서비스 시작버튼클릭");
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MyService.class); // 이동할 컴포넌트
                startService(intent); // 서비스 시작
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 서비스 종료하기
                Log.d("test", "액티비티-서비스 종료버튼클릭");
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MyService.class); // 이동할 컴포넌트
                stopService(intent); // 서비스 종료
            }
        });

    }
}
