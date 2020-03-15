package com.example.block_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class nickname extends baseActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText top_nick, bot_nick;
    Button next;
    static String pr_nick1,pr_nick2;

    public void init() {
        setContentView(R.layout.activity_nickname);
        final String PREFERENCE = getPackageName();

        top_nick = findViewById(R.id.nickname1);
        bot_nick = findViewById(R.id.nickname2);
        next = findViewById(R.id.next_main);

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        pr_nick1 = pref.getString("topnick","이름없음");
        pr_nick2 = pref.getString("botnick","이름없음");
        Log.d("debug",pr_nick1 + "       " + pr_nick2);

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
                    editor.putString("botnick",pr_nick2);
                    editor.commit();
                    Log.d("debug",pr_nick1 + "       " + pr_nick2);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
