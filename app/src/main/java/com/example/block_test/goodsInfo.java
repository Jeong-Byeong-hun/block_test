package com.example.block_test;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.block_test.management.infoList;

public class goodsInfo extends baseActivity implements View.OnKeyListener {

    EditText kor_name, kor_consumer, kor_sell, kor_size, kor_color, kor_memo;
    EditText eng_name, eng_consumer, eng_sell, eng_size, eng_color, eng_memo;
    EditText jap_name, jap_consumer, jap_sell, jap_size, jap_color, jap_memo;
    EditText chi_name, chi_consumer, chi_sell, chi_size, chi_color, chi_memo;
    InputMethodManager imm;

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
