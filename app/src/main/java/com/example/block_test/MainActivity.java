package com.example.block_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.example.block_test.firstPage.isFirst;

public class MainActivity extends baseActivity {

    Button top_kor, top_eng, top_jap, top_chi;
    Button bot_kor, bot_eng, bot_jap, bot_chi;
    LinearLayout linearLayout;
    View.OnClickListener clickListener;
    static ImageView top_image;


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

        top_image = findViewById(R.id.top_imageView);

        firstPage first = new firstPage();

        first.callBack(firstPage.storeNumber,getApplication());

        //사진 고정하는 부분
        if (!isFirst) {
            SharedPreferences pref = getSharedPreferences("image",MODE_PRIVATE);
            String image = pref.getString("imageStrings","");
            Bitmap bitmap = StringToBitMap(image);

            top_image.setImageBitmap(bitmap);
        }


        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(),password.class);
                startActivity(intent);
                return true;
            }
        });

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),store.class);
                switch (v.getId()) {
                    case R.id.top_btn_kor :
                        intent.putExtra("videoForm","_kor.mp4");
                        startActivity(intent);
                        break;
                    case R.id.top_btn_eng :
                        intent.putExtra("videoForm","_eng.mp4");
                        startActivity(intent);
                        break;
                    case R.id.top_btn_jap :
                        intent.putExtra("videoForm","_jap.mp4");
                        startActivity(intent);
                        break;
                    case R.id.top_btn_chi :
                        intent.putExtra("videoForm","_chi.mp4");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_kor :
                        intent.putExtra("videoForm","_kor.mp4");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_eng :
                        intent.putExtra("videoForm","_eng.mp4");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_jap :
                        intent.putExtra("videoForm","_eng.mp4");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_chi :
                        intent.putExtra("videoForm","_chi.mp4");
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

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte[] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0 , encodeByte.length);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
