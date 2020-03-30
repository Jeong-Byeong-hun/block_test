package com.example.block_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class firstPage extends baseActivity {


    Button next;
    EditText store, pw, devName;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static Boolean isFirst;
    static String storeNumber;  // 매장 ID
    static String password;     // 비밀번호
    static String deviceName;     // 기기 이름
    APIInterface apiInterface;
    String mac;






    @Override
    protected void init() {
        setContentView(R.layout.activity_first);


        next = findViewById(R.id.next_btn);
        store = findViewById(R.id.storeId);
        pw = findViewById(R.id.pw);
        devName = findViewById(R.id.devName);
        pref = getSharedPreferences("auth", MODE_PRIVATE);

        isFirst = pref.getBoolean("isFirst", true);
        storeNumber = pref.getString("storeNumber", "def");
        password = pref.getString("password", "0000");
        deviceName = pref.getString("deviceName", "이름등록안됨");
        mac = pref.getString("macAddress", "맥주소없음");

        store.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(store.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        pw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(pw.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        devName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(devName.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFirst) {
                    Log.d("첫 실행시 실행", isFirst + "");
                    editor = pref.edit();

                    next.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            storeNumber = store.getText().toString().trim();
                            password = pw.getText().toString().trim();

                            //맥주소
                            mac = getMacAddr();

                            deviceName = devName.getText().toString().trim();
                            editor.putString("storeNumber", storeNumber);
                            editor.putString("password", password);
                            editor.putBoolean("isFirst", false);
                            editor.putString("macAddress", mac);
                            editor.putString("deviceName", deviceName);
                            editor.commit();
                            callBack(store.getText().toString(), password, mac, deviceName, getApplication());


                        }
                    });
                } else {
//                    settingList(store.getText().toString(), password, mac,  getApplication());
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("storeNum", storeNumber);
                    startActivity(i);
                    finish();
                }
            }
        }).start();


    }

    public void callBack(String storeNum, String password, String mac, String deviceName, Context c) {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        if (storeNum == null) {
            Toast.makeText(c, "아이디를 입력하세요 ", Toast.LENGTH_LONG).show();
        } else if (password == null) {
            Toast.makeText(c, "비밀번호를 입력하세요 ", Toast.LENGTH_LONG).show();
        } else if (deviceName == null) {
            Toast.makeText(c, "기기 이름을 입력하세요 ", Toast.LENGTH_LONG).show();
        } else {


            Call<ResponseLog> call = apiInterface.register(storeNum, password, mac, deviceName);

            call.enqueue(new Callback<ResponseLog>() {
                @Override
                public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                    try {
                        if (response.code() == 403) {
                            Toast.makeText(c, "CODE : 403 로그인 실패", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 300) {
                            Toast.makeText(c, "CODE : 300 ERROR", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 200) {
                            Toast.makeText(c, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), nickname.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseLog> call, Throwable t) {
                    Toast.makeText(c, "접속 실패", Toast.LENGTH_SHORT).show();
                }
            });



            //예전 로그인 코드

//            call.enqueue(new Callback<ResponseData>() {
//                @Override
//                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
//                    try {
//
//                        ResponseData data1 = response.body();
//                        List<ResponseData.goods> dataList = data1.data;
//                        if (response.code() == 403) {
//                            Toast.makeText(c, "권한없음", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            for (ResponseData.goods goods : dataList) {
////                                goodsList.add(goods.goodsTitle);
//                                goodsList.add(goods.goodsId);
//                            }
//                            Intent intent = new Intent(getApplicationContext(), nickname.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ResponseData> call, Throwable t) {
//                    Toast.makeText(c, "접속 실패", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

}
