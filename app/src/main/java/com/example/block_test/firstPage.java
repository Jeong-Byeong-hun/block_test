package com.example.block_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class firstPage extends baseActivity {


    Button next;

    static List<String> goodsList = new ArrayList<>();

    EditText  store,pw;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static Boolean isFirst;
    static String storeNumber;  // 매장 ID
    static String password;     // 비밀번호
    APIInterface apiInterface;

    @Override
    protected void init() {
        setContentView(R.layout.activity_first);

        final String PREFERENCE = getPackageName();

        next = findViewById(R.id.next_btn);
        store = findViewById(R.id.storeId);
        pw = findViewById(R.id.pw);

        pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        isFirst = pref.getBoolean("isFirst", true);
        storeNumber = pref.getString("storeNumber", "def");
        password = pref.getString("password","0000");

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFirst) {
                    Log.d("첫 실행시 실행", isFirst + "");
                    editor = pref.edit();

                    next.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            storeNumber= store.getText().toString().trim();
                            password = pw.getText().toString().trim();
                            editor.putString("storeNumber", storeNumber);
                            editor.putString("password",password);
                            editor.putBoolean("isFirst", false);
                            editor.commit();
                            callBack(store.getText().toString(),getApplication());

                        }
                    });
                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("storeNum",storeNumber);
                    startActivity(i);
                    finish();
                }
            }
        }).start();


    }

    public void callBack(String storeNum, Context c) {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (storeNum == null) {
            Toast.makeText(c,"번호를 입력하세요 ",Toast.LENGTH_LONG).show();
        } else {

            Call<ResponseData> call = apiInterface.checkAuth(storeNum);
            call.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    try {

                        ResponseData data1 = response.body();
                        List<ResponseData.goods> dataList = data1.data;
                        if (response.code() == 403) {
                            Toast.makeText(c, "권한없음", Toast.LENGTH_SHORT).show();

                        } else {
                            for (ResponseData.goods goods : dataList) {
//                                goodsList.add(goods.goodsTitle);
                                goodsList.add(goods.goodsId);
                            }
                            Intent intent = new Intent(getApplicationContext(), nickname.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    Toast.makeText(c, "접속 실패", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
