package com.example.block_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.block_test.firstPage.isFirst;

public class store extends baseActivity {

    TextView top_price, sale_price, top_mileage, top_delivery, top_nprice, top_sprice;
    TextView bot_price, bot_sale_price, bot_mileage, bot_delivery, bot_nprice, bot_sprice;

    ImageView top_img, bot_img;
    String video_form = null;
    View.OnClickListener cl;

    protected void init() {
        setContentView(R.layout.activity_store);

        top_price = findViewById(R.id.top_Price);
        sale_price = findViewById(R.id.sale_Price);
        top_mileage = findViewById(R.id.mileage);
        top_delivery = findViewById(R.id.delivery);
        top_nprice = findViewById(R.id.nprice);
        top_sprice = findViewById(R.id.sprice);

        bot_price = findViewById(R.id.bot_Price);
        bot_sale_price = findViewById(R.id.bot_sale_Price);
        bot_mileage = findViewById(R.id.mileage);
        bot_delivery = findViewById(R.id.bot_delivery);
        bot_nprice = findViewById(R.id.bot_nprice);
        bot_sprice = findViewById(R.id.bot_sprice);

        top_img = findViewById(R.id.top_imageView);
        bot_img = findViewById(R.id.bot_imageView);

        video_form = getIntent().getStringExtra("videoForm");
        //취소선

        top_price.setPaintFlags(top_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        bot_price.setPaintFlags(bot_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        if (!isFirst) {
            SharedPreferences pref = getSharedPreferences("image",MODE_PRIVATE);
            String image = pref.getString("imageStrings","");
            Bitmap bitmap = StringToBitMap(image);

            top_img.setImageBitmap(bitmap);
        }

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), video.class);

                switch (v.getId()) {
                    case R.id.top_imageView:
                        //                intent.putExtra("각 영상의 경로를 담아서 준다, ex) 중국어 버튼으로 들어왔을 시 중국어 영상의 경로를 넘겨줌 아니면 video 클래스에서 각자의 고유str를 넘겨서 video 클래스에서 if문으로 처리");
                        intent.putExtra("videoForm",video_form);
                        startActivity(intent);
                        break;
                    case R.id.bot_imageView:
                        intent.putExtra("videoForm",video_form);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"잘못된 클릭",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };


        top_img.setOnClickListener(cl);

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
