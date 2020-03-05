package com.example.block_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class firstPage extends baseActivity {

    Button next;

    EditText top_edit, bot_edit, store;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean isFirst;
    String storeNumber;

    @Override
    protected void init() {
        setContentView(R.layout.activity_first);

        final String PREFERENCE = getPackageName();

        next = findViewById(R.id.next_btn);
        store = findViewById(R.id.storeId);
        top_edit = findViewById(R.id.top_edit);
        bot_edit = findViewById(R.id.bot_edit);


        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        isFirst = pref.getBoolean("isFirst", true);
        storeNumber = pref.getString("storeNumber","def");


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFirst)  {
                    Log.d("첫 실행시 실행", isFirst + "");
                    editor = pref.edit();
                    editor.putBoolean("isFirst", false);


                    next.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.putString("storeNumber",store.getText().toString());
                            editor.commit();

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("storeNum",store.getText().toString());

                            startActivity(i);
                        }
                    });

                }else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("storeNum",storeNumber);

                    startActivity(i);

                    finish();
                }
            }
        }).start();
    }
}
