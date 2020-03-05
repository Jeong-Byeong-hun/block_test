package com.example.block_test;

import android.content.Intent;
import android.graphics.Paint;
import android.widget.TextView;

public class store extends baseActivity {

    TextView top_price,sale_price,top_mileage,top_delivery,top_nprice,top_sprice;
    TextView bot_price,bot_sale_price,bot_mileage,bot_delivery,bot_nprice,bot_sprice;



    protected void init(){
        setContentView(R.layout.activity_store);

        Intent intent = getIntent();

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

        //취소선

        top_price.setPaintFlags(top_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        bot_price.setPaintFlags(bot_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

    }
}
