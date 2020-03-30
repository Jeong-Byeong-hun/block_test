package com.example.block_test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.block_test.management.goodsList;
import static com.example.block_test.management.goodsList2;
import static com.example.block_test.management.infoList;
import static com.example.block_test.management.infoList2;

public class goodsInfo2 extends baseActivity implements View.OnKeyListener {

    EditText kor_name2, kor_consumer2, kor_sell2, kor_size2, kor_color2, kor_memo2;
    EditText eng_name2, eng_consumer2, eng_sell2, eng_size2, eng_color2, eng_memo2;
    EditText jap_name2, jap_consumer2, jap_sell2, jap_size2, jap_color2, jap_memo2;
    EditText chi_name2, chi_consumer2, chi_sell2, chi_size2, chi_color2, chi_memo2;
    Button save_Info;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    InputMethodManager imm;

    String goodsId;
    String str_kName,str_kConsumer,str_kSell,str_kSize,str_kColor,str_kMemo;
    String str_eName,str_eConsumer,str_eSell,str_eSize,str_eColor,str_eMemo;
    String str_jName,str_jConsumer,str_jSell,str_jSize,str_jColor,str_jMemo;
    String str_cName,str_cConsumer,str_cSell,str_cSize,str_cColor,str_cMemo;


    @Override
    protected void init() {
        setContentView(R.layout.activity_goodsinfo2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);


        kor_name2 = findViewById(R.id.kor_name2);
        kor_consumer2 = findViewById(R.id.kor_consumer2);
        kor_sell2 = findViewById(R.id.kor_sell2);
        kor_size2= findViewById(R.id.kor_size2);
        kor_color2 = findViewById(R.id.kor_color2);
        kor_memo2 = findViewById(R.id.kor_memo2);

        eng_name2 = findViewById(R.id.eng_name2);
        eng_consumer2 = findViewById(R.id.eng_consumer2);
        eng_sell2 = findViewById(R.id.eng_sell2);
        eng_size2 = findViewById(R.id.eng_size2);
        eng_color2 = findViewById(R.id.eng_color2);
        eng_memo2 = findViewById(R.id.eng_memo2);

        jap_name2 = findViewById(R.id.jap_name2);
        jap_consumer2 = findViewById(R.id.jap_consumer2);
        jap_sell2 = findViewById(R.id.jap_sell2);
        jap_size2 = findViewById(R.id.jap_size2);
        jap_color2 = findViewById(R.id.jap_color2);
        jap_memo2 = findViewById(R.id.jap_memo2);

        chi_name2 = findViewById(R.id.chi_name2);
        chi_consumer2 = findViewById(R.id.chi_consumer2);
        chi_sell2 = findViewById(R.id.chi_sell2);
        chi_size2 = findViewById(R.id.chi_size2);
        chi_color2 = findViewById(R.id.chi_color2);
        chi_memo2 = findViewById(R.id.chi_memo2);

        save_Info = findViewById(R.id.goodsInfoSave);

        pref = getSharedPreferences("info2", MODE_PRIVATE);
        editor = pref.edit();




        goodsId = pref.getString("botGoodsId","");

        str_kName = pref.getString("kName2","");
        str_kConsumer = pref.getString("kConsumer2","");
        str_kSell = pref.getString("kSell2","");
        str_kSize = pref.getString("kSize2","");
        str_kColor = pref.getString("kColor2","");
        str_kMemo = pref.getString("kMemo2","");

        str_eName = pref.getString("eName2","");
        str_eConsumer = pref.getString("eConsumer2","");
        str_eSell = pref.getString("eSell2","");
        str_eSize = pref.getString("eSize2","");
        str_eColor = pref.getString("eColor2","");
        str_eMemo = pref.getString("eMemo2","");

        str_jName = pref.getString("jName2","");
        str_jConsumer = pref.getString("jConsumer2","");
        str_jSell = pref.getString("jSell2","");
        str_jSize = pref.getString("jSize2","");
        str_jColor = pref.getString("jColor2","");
        str_jMemo = pref.getString("jMemo2","");

        str_cName = pref.getString("cName2","");
        str_cConsumer = pref.getString("cConsumer2","");
        str_cSell = pref.getString("cSell2","");
        str_cSize = pref.getString("cSize2","");
        str_cColor = pref.getString("cColor2","");
        str_cMemo = pref.getString("cMemo2","");





        save_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsId = infoList2.get(0).goodId.trim();

                str_kName = kor_name2.getText().toString();
                str_kConsumer = kor_consumer2.getText().toString();
                str_kSell = kor_sell2.getText().toString();
                str_kSize = kor_size2.getText().toString();
                str_kColor = kor_color2.getText().toString();
                str_kMemo = kor_memo2.getText().toString();

                str_eName = eng_name2.getText().toString();
                str_eConsumer = eng_consumer2.getText().toString();
                str_eSell = eng_sell2.getText().toString();
                str_eSize = eng_size2.getText().toString();
                str_eColor = eng_color2.getText().toString();
                str_eMemo = eng_memo2.getText().toString();

                str_jName = jap_name2.getText().toString();
                str_jConsumer = jap_consumer2.getText().toString();
                str_jSell = jap_sell2.getText().toString();
                str_jSize = jap_size2.getText().toString();
                str_jColor = jap_color2.getText().toString();
                str_jMemo = jap_memo2.getText().toString();

                str_cName = chi_name2.getText().toString();
                str_cConsumer = chi_consumer2.getText().toString();
                str_cSell = chi_sell2.getText().toString();
                str_cSize = chi_size2.getText().toString();
                str_cColor = chi_color2.getText().toString();
                str_cMemo = chi_memo2.getText().toString();


                editor.putString("botGoodsId",goodsId);

                editor.putString("kName2",str_kName);
                editor.putString("kConsumer2",str_kConsumer);
                editor.putString("kSell2",str_kSell);
                editor.putString("kSize2",str_kSize);
                editor.putString("kColor2",str_kColor);
                editor.putString("kMemo2",str_kMemo);

                editor.putString("eName2",str_eName);
                editor.putString("eConsumer2",str_eConsumer);
                editor.putString("eSell2",str_eSell);
                editor.putString("eSize2",str_eSize);
                editor.putString("eColor2",str_eColor);
                editor.putString("eMemo2",str_eMemo);

                editor.putString("jName2",str_jName);
                editor.putString("jConsumer2",str_jConsumer);
                editor.putString("jSell2",str_jSell);
                editor.putString("jSize2",str_jSize);
                editor.putString("jColor2",str_jColor);
                editor.putString("jMemo2",str_jMemo);

                editor.putString("cName2",str_cName);
                editor.putString("cConsumer2",str_cConsumer);
                editor.putString("cSell2",str_cSell);
                editor.putString("cSize2",str_cSize);
                editor.putString("cColor2",str_cColor);
                editor.putString("cMemo2",str_cMemo);

                Log.d("testMemo",str_kMemo);
                Log.d("testMemo",str_eMemo);
                Log.d("testMemo",str_jMemo);
                Log.d("testMemo",str_cMemo);

                editor.commit();

                Toast.makeText(getApplicationContext(),"저장되었습니다.",Toast.LENGTH_LONG).show();


            }
        });


        for (int i = 0; i < infoList2.size(); i++) {
            if (infoList2.get(i).lang.equalsIgnoreCase("한국어")) {
                kor_name2.setText(infoList2.get(i).goodTitle);
                kor_consumer2.setText(infoList2.get(i).price + "");
                kor_size2.setText(infoList2.get(i).size);
                kor_color2.setText(infoList2.get(i).color);
            } else if (infoList2.get(i).lang.equalsIgnoreCase("영어")) {
                eng_name2.setText(infoList2.get(i).goodTitle);
                eng_consumer2.setText(infoList2.get(i).price + "");
                eng_size2.setText(infoList2.get(i).size);
                eng_color2.setText(infoList2.get(i).color);
            } else if (infoList2.get(i).lang.equalsIgnoreCase("일본어")) {
                jap_name2.setText(infoList2.get(i).goodTitle);
                jap_consumer2.setText(infoList2.get(i).price + "");
                jap_size2.setText(infoList2.get(i).size);
                jap_color2.setText(infoList2.get(i).color);

            } else if ((infoList2.get(i).lang.equalsIgnoreCase("중국어"))) {
                chi_name2.setText(infoList2.get(i).goodTitle);
                chi_consumer2.setText(infoList2.get(i).price + "");
                chi_size2.setText(infoList2.get(i).size);
                chi_color2.setText(infoList2.get(i).color);

            } else {
                Toast.makeText(getApplicationContext(), "지원하지 않는 언어입니다.", Toast.LENGTH_LONG).show();
            }
        }

        kor_name2.setOnKeyListener(this);
        kor_consumer2.setOnKeyListener(this);
        kor_sell2.setOnKeyListener(this);
        kor_size2.setOnKeyListener(this);
        kor_color2.setOnKeyListener(this);
        kor_memo2.setOnKeyListener(this);

        eng_name2.setOnKeyListener(this);
        eng_consumer2.setOnKeyListener(this);
        eng_sell2.setOnKeyListener(this);
        eng_size2.setOnKeyListener(this);
        eng_color2.setOnKeyListener(this);
        eng_memo2.setOnKeyListener(this);

        jap_name2.setOnKeyListener(this);
        jap_consumer2.setOnKeyListener(this);
        jap_sell2.setOnKeyListener(this);
        jap_size2.setOnKeyListener(this);
        jap_color2.setOnKeyListener(this);
        jap_memo2.setOnKeyListener(this);

        chi_name2.setOnKeyListener(this);
        chi_consumer2.setOnKeyListener(this);
        chi_sell2.setOnKeyListener(this);
        chi_size2.setOnKeyListener(this);
        chi_color2.setOnKeyListener(this);
        chi_memo2.setOnKeyListener(this);


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
        imm.hideSoftInputFromWindow(kor_name2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_consumer2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_sell2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_size2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_color2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(kor_memo2.getWindowToken(),0);

        imm.hideSoftInputFromWindow(eng_name2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_consumer2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_sell2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_size2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_color2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(eng_memo2.getWindowToken(),0);

        imm.hideSoftInputFromWindow(jap_name2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_consumer2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_sell2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_size2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_color2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(jap_memo2.getWindowToken(),0);

        imm.hideSoftInputFromWindow(chi_name2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_consumer2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_sell2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_size2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_color2.getWindowToken(),0);
        imm.hideSoftInputFromWindow(chi_memo2.getWindowToken(),0);


    }
}
