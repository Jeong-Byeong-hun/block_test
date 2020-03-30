package com.example.block_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends baseActivity {

    Button top_kor, top_eng, top_jap, top_chi;
    Button bot_kor, bot_eng, bot_jap, bot_chi;
    LinearLayout linearLayout;
    View.OnClickListener clickListener;
    static ImageView top_image, bot_image;
    boolean firstExcute;
    SharedPreferences preferences, infoPreferences, infoPreferences2;

    String prefId, prefPw, prefMac, prefDeviceName, topShelf, botShelf, goodsId;

    APIInterface apiInterface;

    String str_kName, str_kConsumer, str_kSell, str_kSize, str_kColor, str_kMemo;
    String str_eName, str_eConsumer, str_eSell, str_eSize, str_eColor, str_eMemo;
    String str_jName, str_jConsumer, str_jSell, str_jSize, str_jColor, str_jMemo;
    String str_cName, str_cConsumer, str_cSell, str_cSize, str_cColor, str_cMemo;

//    String str_kName2, str_kConsumer2, str_kSell2, str_kSize2, str_kColor2, str_kMemo2;
//    String str_eName2, str_eConsumer2, str_eSell2, str_eSize2, str_eColor2, str_eMemo2;
//    String str_jName2, str_jConsumer2, str_jSell2, str_jSize2, str_jColor2, str_jMemo2;
//    String str_cName2, str_cConsumer2, str_cSell2, str_cSize2, str_cColor2, str_cMemo2;


    File korFile, engFile, japFile, chiFile, korFile2, engFile2, japFile2, chiFile2;

    String videoURL = null;
    String videoURL2 = null;
    SharedPreferences getURL;

    protected void init() {
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.main_layout);

        top_kor = findViewById(R.id.top_btn_kor);
        top_eng = findViewById(R.id.top_btn_eng);
        top_jap = findViewById(R.id.top_btn_jap);
        top_chi = findViewById(R.id.top_btn_chi);

        bot_kor = findViewById(R.id.bottom_btn_kor);
        bot_eng = findViewById(R.id.bottom_btn_eng);
        bot_jap = findViewById(R.id.bottom_btn_jap);
        bot_chi = findViewById(R.id.bottom_btn_chi);

        top_image = findViewById(R.id.top_imageView);
        bot_image = findViewById(R.id.imageView2);

        preferences = getSharedPreferences("auth", MODE_PRIVATE);
        prefId = preferences.getString("storeNumber", "def");
        prefPw = preferences.getString("password", "0000");
        prefMac = preferences.getString("macAddress", "맥주소없음");
        prefDeviceName = preferences.getString("deviceName", "이름등록안됨");
        topShelf = preferences.getString("topnick", "이름없음");
        botShelf = preferences.getString("botnick", "이름없음");

        infoPreferences = getSharedPreferences("info", MODE_PRIVATE);
        infoPreferences2 = getSharedPreferences("info2", MODE_PRIVATE);


        //버튼 비활성화
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath();

        getURL = getSharedPreferences("video", MODE_PRIVATE);
        videoURL = getURL.getString("topVideo", "");
        videoURL2 = getURL.getString("botVideo", "");


        korFile = new File(path + "/Download/" + videoURL + "_kor.mp4");
        engFile = new File(path + "/Download/" + videoURL + "_eng.mp4");
        japFile = new File(path + "/Download/" + videoURL + "_jap.mp4");
        chiFile = new File(path + "/Download/" + videoURL + "_chi.mp4");

        korFile2 = new File(path + "/Download/" + videoURL2 + "_kor.mp4");
        engFile2 = new File(path + "/Download/" + videoURL2 + "_eng.mp4");
        japFile2 = new File(path + "/Download/" + videoURL2 + "_jap.mp4");
        chiFile2 = new File(path + "/Download/" + videoURL2 + "_chi.mp4");

        if (!korFile.exists())
            top_kor.setEnabled(false);
        if (!engFile.exists())
            top_eng.setEnabled(false);
        if (!japFile.exists())
            top_jap.setEnabled(false);
        if (!chiFile.exists())
            top_chi.setEnabled(false);
        if (!korFile2.exists())
            bot_kor.setEnabled(false);
        if (!engFile2.exists())
            bot_eng.setEnabled(false);
        if (!japFile2.exists())
            bot_jap.setEnabled(false);
        if (!chiFile2.exists())
            bot_chi.setEnabled(false);


        //사진 고정하는 부분

        firstExcute = preferences.getBoolean("isFrist", false);

        if (!firstExcute) {
            SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
            String image = pref.getString("imageStrings", "");
            String image2 = pref.getString("imageStrings2", "");
            Bitmap bitmap = StringToBitMap(image);
            Bitmap bitmap2 = StringToBitMap(image2);
            top_image.setImageBitmap(bitmap);
            bot_image.setImageBitmap(bitmap2);
        }


        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(), password.class);
                startActivity(intent);
                return true;
            }
        });

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), store.class);
                apiInterface = APIClient.getClient().create(APIInterface.class);

                switch (v.getId()) {
                    case R.id.top_btn_kor:

                        goodsId = infoPreferences.getString("topGoodsId", "미입력");
                        str_kName = infoPreferences.getString("kName", "미입력");
                        str_kConsumer = infoPreferences.getString("kConsumer", "미입력");
                        str_kSell = infoPreferences.getString("kSell", "미입력");
                        str_kSize = infoPreferences.getString("kSize", "미입력");
                        str_kColor = infoPreferences.getString("kColor", "미입력");
                        str_kMemo = infoPreferences.getString("kMemo", "미입력");


                        Call<ResponseLog> call = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, topShelf, str_kSell, str_kConsumer, "한국어", str_kSize, str_kColor, str_kMemo);

                        call.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {

                            }
                        });

                        intent.putExtra("location","top");
                        intent.putExtra("videoForm", "_kor.mp4");
                        intent.putExtra("form", "kor");
                        startActivity(intent);
                        break;

                    case R.id.top_btn_eng:

                        goodsId = infoPreferences.getString("topGoodsId", "미입력");
                        str_eName = infoPreferences.getString("eName", "미입력");
                        str_eConsumer = infoPreferences.getString("eConsumer", "미입력");
                        str_eSell = infoPreferences.getString("eSell", "미입력");
                        str_eSize = infoPreferences.getString("eSize", "미입력");
                        str_eColor = infoPreferences.getString("eColor", "미입력");
                        str_eMemo = infoPreferences.getString("eMemo", "미입력");


                        Call<ResponseLog> call2 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, topShelf, str_kSell, str_kConsumer, "영어", str_kSize, str_kColor, str_kMemo);

                        call2.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {

                            }
                        });

                        intent.putExtra("location","top");
                        intent.putExtra("videoForm", "_eng.mp4");
                        intent.putExtra("form", "eng");
                        startActivity(intent);
                        break;

                    case R.id.top_btn_jap:

                        goodsId = infoPreferences.getString("topGoodsId", "미입력");
                        str_jName = infoPreferences.getString("jName", "미입력");
                        str_jConsumer = infoPreferences.getString("jConsumer", "미입력");
                        str_jSell = infoPreferences.getString("jSell", "미입력");
                        str_jSize = infoPreferences.getString("jSize", "미입력");
                        str_jColor = infoPreferences.getString("jColor", "미입력");
                        str_jMemo = infoPreferences.getString("jMemo", "미입력");

                        Call<ResponseLog> call3 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, topShelf, str_kSell, str_kConsumer, "일본어", str_kSize, str_kColor, str_kMemo);

                        call3.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {

                            }
                        });
                        intent.putExtra("location","top");
                        intent.putExtra("videoForm", "_jap.mp4");
                        intent.putExtra("form", "jap");
                        startActivity(intent);
                        break;

                    case R.id.top_btn_chi:

                        goodsId = infoPreferences.getString("topGoodsId", "미입력");
                        str_cName = infoPreferences.getString("cName", "미입력");
                        str_cConsumer = infoPreferences.getString("cConsumer", "미입력");
                        str_cSell = infoPreferences.getString("cSell", "미입력");
                        str_cSize = infoPreferences.getString("cSize", "미입력");
                        str_cColor = infoPreferences.getString("cColor", "미입력");
                        str_cMemo = infoPreferences.getString("cMemo", "미입력");


                        Call<ResponseLog> call4 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, topShelf, str_kSell, str_kConsumer, "중국어", str_kSize, str_kColor, str_kMemo);

                        call4.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                        intent.putExtra("location","top");
                        intent.putExtra("videoForm", "_chi.mp4");
                        intent.putExtra("form", "chi");
                        startActivity(intent);

                        break;

                    case R.id.bottom_btn_kor:

                        goodsId = infoPreferences2.getString("botGoodsId", "미입력");
                        str_kName = infoPreferences2.getString("kName2", "미입력");
                        str_kConsumer = infoPreferences2.getString("kConsumer2", "미입력");
                        str_kSell = infoPreferences2.getString("kSell2", "미입력");
                        str_kSize = infoPreferences2.getString("kSize2", "미입력");
                        str_kColor = infoPreferences2.getString("kColor2", "미입력");
                        str_kMemo = infoPreferences2.getString("kMemo2", "미입력");


                        Call<ResponseLog> call5 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, botShelf, str_kSell, str_kConsumer, "한국어", str_kSize, str_kColor, str_kMemo);

                        call5.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {

                            }
                        });
                        intent.putExtra("location","bottom");
                        intent.putExtra("videoForm", "_kor.mp4");
                        intent.putExtra("form", "kor2");
                        startActivity(intent);
                        break;

                    case R.id.bottom_btn_eng:

                        goodsId = infoPreferences2.getString("botGoodsId", "미입력");
                        str_eName = infoPreferences2.getString("eName2", "미입력");
                        str_eConsumer = infoPreferences2.getString("eConsumer2", "미입력");
                        str_eSell = infoPreferences2.getString("eSell2", "미입력");
                        str_eSize = infoPreferences2.getString("eSize2", "미입력");
                        str_eColor = infoPreferences2.getString("eColor2", "미입력");
                        str_eMemo = infoPreferences2.getString("eMemo2", "미입력");


                        Call<ResponseLog> call6 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, botShelf, str_kSell, str_kConsumer, "영어", str_kSize, str_kColor, str_kMemo);

                        call6.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {

                            }
                        });
                        intent.putExtra("location","bottom");
                        intent.putExtra("videoForm", "_eng.mp4");
                        intent.putExtra("form", "eng2");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_jap:

                        goodsId = infoPreferences2.getString("botGoodsId", "미입력");
                        str_jName = infoPreferences2.getString("jName2", "미입력");
                        str_jConsumer = infoPreferences2.getString("jConsumer2", "미입력");
                        str_jSell = infoPreferences2.getString("jSell2", "미입력");
                        str_jSize = infoPreferences2.getString("jSize2", "미입력");
                        str_jColor = infoPreferences2.getString("jColor2", "미입력");
                        str_jMemo = infoPreferences2.getString("jMemo2", "미입력");

                        Call<ResponseLog> call7 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, botShelf, str_kSell, str_kConsumer, "일본어", str_kSize, str_kColor, str_kMemo);

                        call7.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {

                            }
                        });

                        intent.putExtra("location","bottom");
                        intent.putExtra("videoForm", "_jap.mp4");
                        intent.putExtra("form", "jap2");
                        startActivity(intent);
                        break;
                    case R.id.bottom_btn_chi:

                        goodsId = infoPreferences2.getString("botGoodsId", "미입력");
                        str_cName = infoPreferences2.getString("cName2", "미입력");
                        str_cConsumer = infoPreferences2.getString("cConsumer2", "미입력");
                        str_cSell = infoPreferences2.getString("cSell2", "미입력");
                        str_cSize = infoPreferences2.getString("cSize2", "미입력");
                        str_cColor = infoPreferences2.getString("cColor2", "미입력");
                        str_cMemo = infoPreferences2.getString("cMemo2", "미입력");


                        Call<ResponseLog> call8 = apiInterface.postLog(prefId, prefPw, prefMac, goodsId, prefId, prefDeviceName, botShelf, str_kSell, str_kConsumer, "중국어", str_kSize, str_kColor, str_kMemo);

                        call8.enqueue(new Callback<ResponseLog>() {
                            @Override
                            public void onResponse(Call<ResponseLog> call, Response<ResponseLog> response) {
                                try {
                                    if (response.code() == 200) {
                                        Toast.makeText(getApplicationContext(), "로그 등록 성공", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 300) {
                                        Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_LONG).show();
                                    } else if (response.code() == 403) {
                                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLog> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                        intent.putExtra("location","bottom");
                        intent.putExtra("videoForm", "_chi.mp4");
                        intent.putExtra("form", "chi2");
                        startActivity(intent);
                        break;
                }
            }
        };

        top_kor.setOnClickListener(clickListener);
        top_eng.setOnClickListener(clickListener);
        top_jap.setOnClickListener(clickListener);
        top_chi.setOnClickListener(clickListener);

        bot_kor.setOnClickListener(clickListener);
        bot_eng.setOnClickListener(clickListener);
        bot_jap.setOnClickListener(clickListener);
        bot_chi.setOnClickListener(clickListener);


    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
