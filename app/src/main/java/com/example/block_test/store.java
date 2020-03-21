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

import java.text.DecimalFormat;

import static com.example.block_test.firstPage.isFirst;

public class store extends baseActivity {

    TextView top_price, sale_price, top_mileage, top_delivery, top_nprice, top_sprice,top_name,top_color,top_size;
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
        top_name = findViewById(R.id.top_name);
        top_color = findViewById(R.id.top_color);
        top_size = findViewById(R.id.top_size);

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
        top_nprice.setPaintFlags(top_nprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        DecimalFormat format = new DecimalFormat("###,###");

        SharedPreferences infoPreferences = getSharedPreferences("info",MODE_PRIVATE);
        top_name.setText(infoPreferences.getString("kName",""));
        top_price.setText(format.format(Integer.parseInt(infoPreferences.getString("kConsumer",""))));
        top_color.setText(infoPreferences.getString("kColor",""));
        top_size.setText(infoPreferences.getString("kSize",""));
        sale_price.setText(format.format(Integer.parseInt(infoPreferences.getString("kSell",""))));

        top_nprice.setText("정상가 : " + format.format(Integer.parseInt(infoPreferences.getString("kConsumer",""))) );
        top_sprice.setText("판매가 : " + format.format(Integer.parseInt(infoPreferences.getString("kSell",""))) );


//        editor.putString("kName",str_kName);
//        editor.putString("kConsumer",str_kConsumer);
//        editor.putString("kSell",str_kSell);
//        editor.putString("kSize",str_kSize);
//        editor.putString("kColor",str_kColor);
//        editor.putString("kMemo",str_kMemo);


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
