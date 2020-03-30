package com.example.block_test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.block_test.firstPage.isFirst;

public class nickname extends baseActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText top_nick, bot_nick;
    Button next;
    static String pr_nick1, pr_nick2;

    public void init() {
        setContentView(R.layout.activity_nickname);

        top_nick = findViewById(R.id.nickname1);
        bot_nick = findViewById(R.id.nickname2);
        next = findViewById(R.id.next_main);

        pref = getSharedPreferences("auth", MODE_PRIVATE);

        pr_nick1 = pref.getString("topnick", "이름없음");
        pr_nick2 = pref.getString("botnick", "이름없음");
        Log.d("debug", pr_nick1 + "       " + pr_nick2);


        top_nick.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(top_nick.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        bot_nick.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(bot_nick.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(top_nick.getText().toString().trim()) || TextUtils.isEmpty(bot_nick.getText().toString().trim()))
                    Toast.makeText(getApplicationContext(), "이름을 설정하시지 않았습니다. 확인 후 다시 시도해 주십시오.", Toast.LENGTH_LONG).show();
                else {
                    editor = pref.edit();

                    pr_nick1 = top_nick.getText().toString().trim();
                    pr_nick2 = bot_nick.getText().toString().trim();
                    editor.putString("topnick", pr_nick1);
                    editor.putString("botnick", pr_nick2);
                    editor.commit();
                    Log.d("debug", pr_nick1 + "       " + pr_nick2);
                    if (!isFirst) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {

                        Intent intent = new Intent(getApplicationContext(),management.class);
                        startActivity(intent);
                    }

                    finish();
                }
            }
        });

    }
}
