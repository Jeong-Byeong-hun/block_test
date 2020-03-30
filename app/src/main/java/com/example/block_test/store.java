package com.example.block_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class store extends baseActivity {

    TextView top_nprice, top_sprice, top_name, top_color, top_size, top_memo;
    TextView tv_color, tv_size, tv_memo;

    ImageView top_img;
    String video_form = null, form, location;
    View.OnClickListener cl;
    Handler handler;

    Bitmap bitmap, bitmap2;

    protected void init() {
        setContentView(R.layout.activity_store);


        top_nprice = findViewById(R.id.nprice);
        top_sprice = findViewById(R.id.sprice);
        top_name = findViewById(R.id.top_name);
        top_color = findViewById(R.id.top_color);
        top_size = findViewById(R.id.top_size);
        top_memo = findViewById(R.id.top_memo);


        top_img = findViewById(R.id.top_imageView);

        tv_color = findViewById(R.id.tv_color);
        tv_size = findViewById(R.id.tv_size);
        tv_memo = findViewById(R.id.tv_memo);


        Intent intent = getIntent();
        video_form = intent.getStringExtra("videoForm");
        form = intent.getStringExtra("form");
        location = intent.getStringExtra("location");

        SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
        String image = pref.getString("imageStrings", "");
        String image2 = pref.getString("imageStrings2", "");

        if (location.equalsIgnoreCase("top")) {
            bitmap = StringToBitMap(image);
            top_img.setImageBitmap(bitmap);
        }
        else if (location.equalsIgnoreCase("bottom")) {
            bitmap2 = StringToBitMap(image2);
            top_img.setImageBitmap(bitmap2);
        }


        //취소선
        top_nprice.setPaintFlags(top_nprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        DecimalFormat format = new DecimalFormat("###,###");

        SharedPreferences infoPreferences = getSharedPreferences("info", MODE_PRIVATE);
        SharedPreferences infoPreferences2 = getSharedPreferences("info2", MODE_PRIVATE);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 10000);


        if (form.equalsIgnoreCase("kor")) {

            top_name.setText(infoPreferences.getString("kName", ""));
            top_color.setText(infoPreferences.getString("kColor", ""));
            top_size.setText(infoPreferences.getString("kSize", ""));
            top_memo.setText(infoPreferences.getString("kMemo", ""));

            top_nprice.setText("정상가 : " + format.format(Integer.parseInt(infoPreferences.getString("kConsumer", "0"))));
            top_sprice.setText("판매가 : " + format.format(Integer.parseInt(infoPreferences.getString("kSell", "0"))));

//            if (location.equalsIgnoreCase("top")) {
//                top_name.setText(infoPreferences.getString("kName", ""));
//                top_color.setText(infoPreferences.getString("kColor", ""));
//                top_size.setText(infoPreferences.getString("kSize", ""));
//                top_memo.setText(infoPreferences.getString("kMemo", ""));
//
//                top_nprice.setText("정상가 : " + format.format(Integer.parseInt(infoPreferences.getString("kConsumer", "0"))));
//                top_sprice.setText("판매가 : " + format.format(Integer.parseInt(infoPreferences.getString("kSell", "0"))));
//            } else if (location.equalsIgnoreCase("bottom")) {
//                top_name.setText(infoPreferences2.getString("kName2", "") + "");
//                top_color.setText(infoPreferences2.getString("kColor2", "") + "");
//                top_size.setText(infoPreferences2.getString("kSize2", "") + "");
//                top_memo.setText(infoPreferences2.getString("kMemo2", "") + "");
//
//                top_nprice.setText("정상가 : " + format.format(Integer.parseInt(infoPreferences2.getString("kConsumer2", "0"))));
//                top_sprice.setText("판매가 : " + format.format(Integer.parseInt(infoPreferences2.getString("kSell2", "0"))));
//            }

        } else if (form.equalsIgnoreCase("eng")) {

            tv_color.setText("Color");
            tv_size.setText("Size");
            tv_memo.setText("Memo");

            top_name.setText(infoPreferences.getString("eName", "") + "");
            top_color.setText(infoPreferences.getString("eColor", "") + "");
            top_size.setText(infoPreferences.getString("eSize", "") + "");
            top_memo.setText(infoPreferences.getString("eMemo", "") + "");

            top_nprice.setText("Tag Price : " + format.format(Integer.parseInt(infoPreferences.getString("eConsumer", "0"))));
            top_sprice.setText("On Sale : " + format.format(Integer.parseInt(infoPreferences.getString("eSell", "0"))));
//            if (location.equalsIgnoreCase("top")) {
//                top_name.setText(infoPreferences.getString("eName", "") + "");
//                top_color.setText(infoPreferences.getString("eColor", "") + "");
//                top_size.setText(infoPreferences.getString("eSize", "") + "");
//                top_memo.setText(infoPreferences.getString("eMemo", "") + "");
//
//                top_nprice.setText("Tag Price : " + format.format(Integer.parseInt(infoPreferences.getString("eConsumer", "0"))));
//                top_sprice.setText("On Sale : " + format.format(Integer.parseInt(infoPreferences.getString("eSell", "0"))));
//            } else if (location.equalsIgnoreCase("bottom")) {
//                top_name.setText(infoPreferences2.getString("eName2", "") + "");
//                top_color.setText(infoPreferences2.getString("eColor2", "") + "");
//                top_size.setText(infoPreferences2.getString("eSize2", "") + "");
//                top_memo.setText(infoPreferences2.getString("eMemo2", "") + "");
//
//                top_nprice.setText("Tag Price : " + format.format(Integer.parseInt(infoPreferences2.getString("eConsumer2", "0"))));
//                top_sprice.setText("On Sale : " + format.format(Integer.parseInt(infoPreferences2.getString("eSell2", "0"))));
//
//            }
        } else if (form.equalsIgnoreCase("jap")) {


            tv_color.setText("色");
            tv_size.setText("サイズ");
            tv_memo.setText("メモ");

            top_name.setText(infoPreferences.getString("jName", "") + "");
            top_color.setText(infoPreferences.getString("jColor", "") + "");
            top_size.setText(infoPreferences.getString("jSize", "") + "");
            top_memo.setText(infoPreferences.getString("jMemo", "") + "");

            top_nprice.setText("価格 : " + format.format(Integer.parseInt(infoPreferences.getString("jConsumer", "0"))));
            top_sprice.setText("セール価格 : " + format.format(Integer.parseInt(infoPreferences.getString("jSell", "0"))));

//            if (location.equalsIgnoreCase("top")) {
//                top_name.setText(infoPreferences.getString("jName", "") + "");
//                top_color.setText(infoPreferences.getString("jColor", "") + "");
//                top_size.setText(infoPreferences.getString("jSize", "") + "");
//                top_memo.setText(infoPreferences.getString("jMemo", "") + "");
//
//                top_nprice.setText("価格 : " + format.format(Integer.parseInt(infoPreferences.getString("jConsumer", "0"))));
//                top_sprice.setText("セール価格 : " + format.format(Integer.parseInt(infoPreferences.getString("jSell", "0"))));
//            } else if (location.equalsIgnoreCase("bottom")) {
//                top_name.setText(infoPreferences2.getString("jName2", "") + "");
//                top_color.setText(infoPreferences2.getString("jColor2", "") + "");
//                top_size.setText(infoPreferences2.getString("jSize2", "") + "");
//                top_memo.setText(infoPreferences2.getString("jMemo2", "") + "");
//
//                top_nprice.setText("価格 : " + format.format(Integer.parseInt(infoPreferences2.getString("jConsumer2", "0"))));
//                top_sprice.setText("セール価格 : " + format.format(Integer.parseInt(infoPreferences2.getString("jSell2", "0"))));
//            }

        } else if (form.equalsIgnoreCase("chi")) {


            tv_color.setText("颜色");
            tv_size.setText("尺寸");
            tv_memo.setText("备忘录");


            top_name.setText(infoPreferences.getString("cName", "") + "");
            top_color.setText(infoPreferences.getString("cColor", "") + "");
            top_size.setText(infoPreferences.getString("cSize", "") + "");
            top_memo.setText(infoPreferences.getString("cMemo", "") + "");


            top_nprice.setText("价钱 : " + format.format(Integer.parseInt(infoPreferences.getString("cConsumer", "0"))));
            top_sprice.setText("特价中 : " + format.format(Integer.parseInt(infoPreferences.getString("cSell", "0"))));


//            if (location.equalsIgnoreCase("top")) {
//                top_name.setText(infoPreferences.getString("cName", "") + "");
//                top_color.setText(infoPreferences.getString("cColor", "") + "");
//                top_size.setText(infoPreferences.getString("cSize", "") + "");
//                top_memo.setText(infoPreferences.getString("cMemo", "") + "");
//
//
//                top_nprice.setText("价钱 : " + format.format(Integer.parseInt(infoPreferences.getString("cConsumer", "0"))));
//                top_sprice.setText("特价中 : " + format.format(Integer.parseInt(infoPreferences.getString("cSell", "0"))));
//            } else if (location.equalsIgnoreCase("bottom")) {
//                top_name.setText(infoPreferences2.getString("cName2", "") + "");
//                top_color.setText(infoPreferences2.getString("cColor2", "") + "");
//                top_size.setText(infoPreferences2.getString("cSize2", "") + "");
//                top_memo.setText(infoPreferences2.getString("cMemo2", "") + "");
//
//                top_nprice.setText("价钱 : " + format.format(Integer.parseInt(infoPreferences2.getString("cConsumer2", "0"))));
//                top_sprice.setText("特价中 : " + format.format(Integer.parseInt(infoPreferences2.getString("cSell2", "0"))));
//
//            }
        } else if (form.equalsIgnoreCase("kor2")) {

            top_name.setText(infoPreferences2.getString("kName2", "") + "");
            top_color.setText(infoPreferences2.getString("kColor2", "") + "");
            top_size.setText(infoPreferences2.getString("kSize2", "") + "");
            top_memo.setText(infoPreferences2.getString("kMemo2", "") + "");

            top_nprice.setText("정상가 : " + format.format(Integer.parseInt(infoPreferences2.getString("kConsumer2", "0"))));
            top_sprice.setText("판매가 : " + format.format(Integer.parseInt(infoPreferences2.getString("kSell2", "0"))));

        } else if (form.equalsIgnoreCase("eng2")) {


            tv_color.setText("Color");
            tv_size.setText("Size");
            tv_memo.setText("Memo");


            top_name.setText(infoPreferences2.getString("eName2", "") + "");
            top_color.setText(infoPreferences2.getString("eColor2", "") + "");
            top_size.setText(infoPreferences2.getString("eSize2", "") + "");
            top_memo.setText(infoPreferences2.getString("eMemo2", "") + "");

            top_nprice.setText("Tag Price : " + format.format(Integer.parseInt(infoPreferences2.getString("eConsumer2", "0"))));
            top_sprice.setText("On Sale : " + format.format(Integer.parseInt(infoPreferences2.getString("eSell2", "0"))));


        } else if (form.equalsIgnoreCase("jap2")) {

            tv_color.setText("色");
            tv_size.setText("サイズ");
            tv_memo.setText("メモ");



            top_name.setText(infoPreferences2.getString("jName2", "") + "");
            top_color.setText(infoPreferences2.getString("jColor2", "") + "");
            top_size.setText(infoPreferences2.getString("jSize2", "") + "");
            top_memo.setText(infoPreferences2.getString("jMemo2", "") + "");

            top_nprice.setText("価格 : " + format.format(Integer.parseInt(infoPreferences2.getString("jConsumer2", "0"))));
            top_sprice.setText("セール価格 : " + format.format(Integer.parseInt(infoPreferences2.getString("jSell2", "0"))));


        } else if (form.equalsIgnoreCase("chi2")) {

            tv_color.setText("颜色");
            tv_size.setText("尺寸");
            tv_memo.setText("备忘录");

            top_name.setText(infoPreferences2.getString("cName2", "") + "");
            top_color.setText(infoPreferences2.getString("cColor2", "") + "");
            top_size.setText(infoPreferences2.getString("cSize2", "") + "");
            top_memo.setText(infoPreferences2.getString("cMemo2", "") + "");


            top_nprice.setText("价钱 : " + format.format(Integer.parseInt(infoPreferences2.getString("cConsumer2", "0"))));
            top_sprice.setText("特价中 : " + format.format(Integer.parseInt(infoPreferences2.getString("cSell2", "0"))));
        }


//        if (!isFirst) {
//            SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
//            String image = pref.getString("imageStrings", "");
//            Bitmap bitmap = StringToBitMap(image);
//
//            top_img.setImageBitmap(bitmap);
//
//
//            String image2 = pref.getString("imageStrings2", "");
//            Bitmap bitmap2 = StringToBitMap(image2);
//
//            bot_img.setImageBitmap(bitmap2);
//        }

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), video.class);

                switch (v.getId()) {
                    case R.id.top_imageView:
                        handler.removeCallbacksAndMessages(null);
                        //intent.putExtra("각 영상의 경로를 담아서 준다, ex) 중국어 버튼으로 들어왔을 시 중국어 영상의 경로를 넘겨줌 아니면 video 클래스에서 각자의 고유str를 넘겨서 video 클래스에서 if문으로 처리");
                        intent.putExtra("videoForm", video_form);
                        intent.putExtra("location", location);
                        intent.putExtra("form", form);
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "잘못된 클릭", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };


        top_img.setOnClickListener(cl);

    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        finish();

    }
}
