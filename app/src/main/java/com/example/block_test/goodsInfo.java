package com.example.block_test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.example.block_test.management.goodsList;
import static com.example.block_test.management.infoList;

public class goodsInfo extends baseActivity implements View.OnKeyListener {

    EditText kor_name, kor_consumer, kor_sell, kor_size, kor_color, kor_memo;
    EditText eng_name, eng_consumer, eng_sell, eng_size, eng_color, eng_memo;
    EditText jap_name, jap_consumer, jap_sell, jap_size, jap_color, jap_memo;
    EditText chi_name, chi_consumer, chi_sell, chi_size, chi_color, chi_memo;
    Button save_Info;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    InputMethodManager imm;

    String goodsId;
    String str_kName,str_kConsumer,str_kSell,str_kSize,str_kColor,str_kMemo;
    String str_eName,str_eConsumer,str_eSell,str_eSize,str_eColor,str_eMemo;
    String str_jName,str_jConsumer,str_jSell,str_jSize,str_jColor,str_jMemo;
    String str_cName,str_cConsumer,str_cSell,str_cSize,str_cColor,str_cMemo;

    int width;
    @Override
    protected void init() {
        setContentView(R.layout.activity_goodsinfo);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);


        kor_name = findViewById(R.id.kor_name);
        kor_consumer = findViewById(R.id.kor_consumer);
        kor_sell = findViewById(R.id.kor_sell);
        kor_size = findViewById(R.id.kor_size);
        kor_color = findViewById(R.id.kor_color);
        kor_memo = findViewById(R.id.kor_memo);

        eng_name = findViewById(R.id.eng_name);
        eng_consumer = findViewById(R.id.eng_consumer);
        eng_sell = findViewById(R.id.eng_sell);
        eng_size = findViewById(R.id.eng_size);
        eng_color = findViewById(R.id.eng_color);
        eng_memo = findViewById(R.id.eng_memo);

        jap_name = findViewById(R.id.jap_name);
        jap_consumer = findViewById(R.id.jap_consumer);
        jap_sell = findViewById(R.id.jap_sell);
        jap_size = findViewById(R.id.jap_size);
        jap_color = findViewById(R.id.jap_color);
        jap_memo = findViewById(R.id.jap_memo);

        chi_name = findViewById(R.id.chi_name);
        chi_consumer = findViewById(R.id.chi_consumer);
        chi_sell = findViewById(R.id.chi_sell);
        chi_size = findViewById(R.id.chi_size);
        chi_color = findViewById(R.id.chi_color);
        chi_memo = findViewById(R.id.chi_memo);

        save_Info = findViewById(R.id.goodsInfoSave);


        pref = getSharedPreferences("info", MODE_PRIVATE);
        editor = pref.edit();




        goodsId = pref.getString("topGoodsId","");

        str_kName = pref.getString("kName","");
        str_kConsumer = pref.getString("kConsumer","");
        str_kSell = pref.getString("kSell","");
        str_kSize = pref.getString("kSize","");
        str_kColor = pref.getString("kColor","");
        str_kMemo = pref.getString("kMemo","");

        str_eName = pref.getString("eName","");
        str_eConsumer = pref.getString("eConsumer","");
        str_eSell = pref.getString("eSell","");
        str_eSize = pref.getString("eSize","");
        str_eColor = pref.getString("eColor","");
        str_eMemo = pref.getString("eMemo","");

        str_jName = pref.getString("jName","");
        str_jConsumer = pref.getString("jConsumer","");
        str_jSell = pref.getString("jSell","");
        str_jSize = pref.getString("jSize","");
        str_jColor = pref.getString("jColor","");
        str_jMemo = pref.getString("jMemo","");

        str_cName = pref.getString("cName","");
        str_cConsumer = pref.getString("cConsumer","");
        str_cSell = pref.getString("cSell","");
        str_cSize = pref.getString("cSize","");
        str_cColor = pref.getString("cColor","");
        str_cMemo = pref.getString("cMemo","");





        save_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsId = infoList.get(0).goodId.trim();

                str_kName = kor_name.getText().toString();
                str_kConsumer = kor_consumer.getText().toString();
                str_kSell = kor_sell.getText().toString();
                str_kSize = kor_size.getText().toString();
                str_kColor = kor_color.getText().toString();
                str_kMemo = kor_memo.getText().toString();

                str_eName = eng_name.getText().toString();
                str_eConsumer = eng_consumer.getText().toString();
                str_eSell = eng_sell.getText().toString();
                str_eSize = eng_size.getText().toString();
                str_eColor = eng_color.getText().toString();
                str_eMemo = eng_memo.getText().toString();

                str_jName = jap_name.getText().toString();
                str_jConsumer = jap_consumer.getText().toString();
                str_jSell = jap_sell.getText().toString();
                str_jSize = jap_size.getText().toString();
                str_jColor = jap_color.getText().toString();
                str_jMemo = jap_memo.getText().toString();

                str_cName = chi_name.getText().toString();
                str_cConsumer = chi_consumer.getText().toString();
                str_cSell = chi_sell.getText().toString();
                str_cSize = chi_size.getText().toString();
                str_cColor = chi_color.getText().toString();
                str_cMemo = chi_memo.getText().toString();


                editor.putString("topGoodsId",goodsId);

                editor.putString("kName",str_kName);
                editor.putString("kConsumer",str_kConsumer);
                editor.putString("kSell",str_kSell);
                editor.putString("kSize",str_kSize);
                editor.putString("kColor",str_kColor);
                editor.putString("kMemo",str_kMemo);

                editor.putString("eName",str_eName);
                editor.putString("eConsumer",str_eConsumer);
                editor.putString("eSell",str_eSell);
                editor.putString("eSize",str_eSize);
                editor.putString("eColor",str_eColor);
                editor.putString("eMemo",str_eMemo);

                editor.putString("jName",str_jName);
                editor.putString("jConsumer",str_jConsumer);
                editor.putString("jSell",str_jSell);
                editor.putString("jSize",str_jSize);
                editor.putString("jColor",str_jColor);
                editor.putString("jMemo",str_jMemo);

                editor.putString("cName",str_cName);
                editor.putString("cConsumer",str_cConsumer);
                editor.putString("cSell",str_cSell);
                editor.putString("cSize",str_cSize);
                editor.putString("cColor",str_cColor);
                editor.putString("cMemo",str_cMemo);

                Log.d("testMemo",str_kMemo);
                Log.d("testMemo",str_eMemo);
                Log.d("testMemo",str_jMemo);
                Log.d("testMemo",str_cMemo);

                editor.commit();

                Toast.makeText(getApplicationContext(),"저장되었습니다.",Toast.LENGTH_LONG).show();


            }
        });


        for (int i = 0; i < infoList.size(); i++) {
            if (infoList.get(i).lang.equalsIgnoreCase("한국어")) {
                kor_name.setText(infoList.get(i).goodTitle);
                kor_consumer.setText(infoList.get(i).price + "");
                kor_size.setText(infoList.get(i).size);
                kor_color.setText(infoList.get(i).color);
            } else if (infoList.get(i).lang.equalsIgnoreCase("영어")) {
                eng_name.setText(infoList.get(i).goodTitle);
                eng_consumer.setText(infoList.get(i).price + "");
                eng_size.setText(infoList.get(i).size);
                eng_color.setText(infoList.get(i).color);
            } else if (infoList.get(i).lang.equalsIgnoreCase("일본어")) {
                jap_name.setText(infoList.get(i).goodTitle);
                jap_consumer.setText(infoList.get(i).price + "");
                jap_size.setText(infoList.get(i).size);
                jap_color.setText(infoList.get(i).color);

            } else if ((infoList.get(i).lang.equalsIgnoreCase("중국어"))) {
                chi_name.setText(infoList.get(i).goodTitle);
                chi_consumer.setText(infoList.get(i).price + "");
                chi_size.setText(infoList.get(i).size);
                chi_color.setText(infoList.get(i).color);

            } else {
                Toast.makeText(getApplicationContext(), "지원하지 않는 언어입니다.", Toast.LENGTH_LONG).show();
            }
        }

        kor_name.setOnKeyListener(this);
        kor_consumer.setOnKeyListener(this);
        kor_sell.setOnKeyListener(this);
        kor_size.setOnKeyListener(this);
        kor_color.setOnKeyListener(this);
        kor_memo.setOnKeyListener(this);

        eng_name.setOnKeyListener(this);
        eng_consumer.setOnKeyListener(this);
        eng_sell.setOnKeyListener(this);
        eng_size.setOnKeyListener(this);
        eng_color.setOnKeyListener(this);
        eng_memo.setOnKeyListener(this);

        jap_name.setOnKeyListener(this);
        jap_consumer.setOnKeyListener(this);
        jap_sell.setOnKeyListener(this);
        jap_size.setOnKeyListener(this);
        jap_color.setOnKeyListener(this);
        jap_memo.setOnKeyListener(this);

        chi_name.setOnKeyListener(this);
        chi_consumer.setOnKeyListener(this);
        chi_sell.setOnKeyListener(this);
        chi_size.setOnKeyListener(this);
        chi_color.setOnKeyListener(this);
        chi_memo.setOnKeyListener(this);


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            hideKeyBoard();
            return true;
        }
        return false;
    }

    public void hideKeyBoard() {
        imm.hideSoftInputFromWindow(kor_name.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_consumer.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_sell.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_size.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_color.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_memo.getWindowToken(),0);

        imm.hideSoftInputFromWindow(eng_name.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_consumer.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_sell.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_size.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_color.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_memo.getWindowToken(),0);

        imm.hideSoftInputFromWindow(jap_name.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_consumer.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_sell.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_size.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_color.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_memo.getWindowToken(),0);

        imm.hideSoftInputFromWindow(chi_name.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_consumer.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_sell.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_size.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_color.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_memo.getWindowToken(),0);


    }
}
