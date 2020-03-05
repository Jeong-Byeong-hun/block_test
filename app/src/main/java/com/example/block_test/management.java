package com.example.block_test;

import android.content.Intent;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class management extends baseActivity {

    AutoCompleteTextView top_auto,bot_auto;
    TextView storeId;
    EditText top_consumer,top_sell,top_size,top_memo;
    EditText bot_consumer,bot_sell,bot_size,bot_memo;
    Button top_search,bot_search;

    @Override
    protected void init() {
        setContentView(R.layout.activity_management);

        storeId = findViewById(R.id.store_num);

        top_auto = findViewById(R.id.pro_auto);
        bot_auto = findViewById(R.id.pro_auto2);

        top_consumer = findViewById(R.id.top_consumer);
        top_sell = findViewById(R.id.top_sell);
        top_size = findViewById(R.id.top_size);
        top_memo = findViewById(R.id.top_memo);

        bot_consumer = findViewById(R.id.bot_consumer);
        bot_sell = findViewById(R.id.bot_sell);
        bot_size = findViewById(R.id.bot_size);
        bot_memo = findViewById(R.id.bot_memo);

        top_search = findViewById(R.id.top_search);
        bot_search = findViewById(R.id.bot_search);


        Intent intent = getIntent();

        String store = intent.getStringExtra("storeNum");

        storeId.setText(store);

    }


}
