package com.example.block_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends baseActivity {

    Button top_kor, top_eng, top_jap, top_chi;
    Button bot_kor, bot_eng, bot_jap, bot_chi;
    LinearLayout linearLayout;
    View.OnClickListener clickListener;

    String storeId;

    protected void init() {
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.main_layout);

        top_kor = findViewById(R.id.top_btn_kor);
        top_eng = findViewById(R.id.top_btn_eng);
        top_jap = findViewById(R.id.top_btn_jap);
        top_chi = findViewById(R.id.top_btn_chi);

        bot_kor = findViewById(R.id.bottom_btn_kor);
        bot_eng = findViewById(R.id.bottom_btn_eng);
        bot_jap = findViewById(R.id.bottom_btn_jap);
        bot_chi = findViewById(R.id.bottom_btn_chi);


        Intent intent = getIntent();

        storeId = intent.getStringExtra("storeNum");



        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(),management.class);
                intent.putExtra("storeNum",storeId);
                startActivity(intent);
                return true;
            }
        });

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),video.class);
                switch (v.getId()) {
                    case R.id.top_btn_kor :
                        intent.putExtra("top_kor","한국어");
                        startActivity(intent);
                        break;
                    case R.id.top_btn_eng :
                        intent.putExtra("top_eng","English");
                        startActivity(intent);
                        break;
                    case R.id.top_btn_jap :
                        intent.putExtra("top_jap","일본어페이지");
                        startActivity(intent);
                        break;
                    case R.id.top_btn_chi :
                        intent.putExtra("top_chi","중국어페이지");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_kor :
                        intent.putExtra("bot_kor","한국어 페이지");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_eng :
                        intent.putExtra("bot_eng","영어 페이지");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_jap :
                        intent.putExtra("bot_jap","일본어 페이지");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_chi :
                        intent.putExtra("bot_chi","중국어 페이지");
                        startActivity(intent);
                        break;
                }
            }
        };

        top_kor.setOnClickListener(clickListener);
        top_eng.setOnClickListener(clickListener);
        top_jap.setOnClickListener(clickListener);
        top_chi.setOnClickListener(clickListener);

        bot_kor.setOnClickListener(clickListener);
        bot_eng.setOnClickListener(clickListener);
        bot_jap.setOnClickListener(clickListener);
        bot_chi.setOnClickListener(clickListener);


    }


}
