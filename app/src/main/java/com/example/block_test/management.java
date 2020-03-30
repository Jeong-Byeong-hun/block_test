package com.example.block_test;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.ActionProvider;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.block_test.MainActivity.bot_image;
import static com.example.block_test.MainActivity.top_image;


public class management extends baseActivity {
    static String videoName, videoName2;
    static List<ResponseInfo.data> infoList = null;
    static List<ResponseInfo.data> infoList2 = null;
    static List<String> goodsList = new ArrayList<>();
    static List<String> goodsList2 = new ArrayList<>();
    Bitmap mSaveBm, sharedPic;
    AutoCompleteTextView top_auto, bot_auto;
    TextView storeId;
    Button top_search, bot_search, video_down, info_save, video_down2, info_save2;
    APIInterface apiInterface2, apiInterface;
    ScrollView scrollView;
    LinearLayout linearLayout;
    View.OnClickListener cl;
    String lang = null;
    String[] videoUrl;
    String imageUrl = null;
    String goodsIdVideo = null;
    String[] videoUrl2;
    String imageUrl2 = null;
    SharedPreferences video;
    SharedPreferences.Editor videoedit;
    SharedPreferences pref;
    String id, password, mac;
    private ProgressDialog progressBar;

    static final int PERMISSION_REQUEST_CODE = 1;
    String[] PERMISSIONS = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};
    private File outputFile; //파일명까지 포함한 경로
    private File path;//디렉토리경로

    private boolean hasPermissions(String[] permissions) {
        int res = 0;
        //스트링 배열에 있는 퍼미션들의 허가 상태 여부 확인
        for (String perms : permissions) {
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                //퍼미션 허가 안된 경우
                return false;
            }

        }
        //퍼미션이 허가된 경우
        return true;
    }

    private void requestNecessaryPermissions(String[] permissions) {
        //마시멜로( API 23 )이상에서 런타임 퍼미션(Runtime Permission) 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    protected void init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_manage);

        info_save = findViewById(R.id.goodsInfobtn);
        video_down = findViewById(R.id.video_down);

        //우측 레이아웃 버튼
        info_save2 = findViewById(R.id.goodsInfobtn2);
        video_down2 = findViewById(R.id.video_down2);


        scrollView = findViewById(R.id.scrView);
        linearLayout = findViewById(R.id.manageLine);
        storeId = findViewById(R.id.store_num);


        top_auto = findViewById(R.id.pro_auto);
        bot_auto = findViewById(R.id.pro_auto2);


        top_search = findViewById(R.id.top_search);
        bot_search = findViewById(R.id.bot_search);

        storeId.setText(firstPage.storeNumber);


        pref = getSharedPreferences("auth", MODE_PRIVATE);

        id = pref.getString("storeNumber", "");
        password = pref.getString("password", "");
        mac = pref.getString("macAddress", "02:00:00:00:00:00");

        top_auto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(top_auto.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        bot_auto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(bot_auto.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        if (id == null || id.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "아이디가 없습니다.", Toast.LENGTH_LONG).show();
        } else if (password == null || password.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "비밀번호가 없습니다.", Toast.LENGTH_LONG).show();
        } else if (mac == null || mac.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "맥주소가 없습니다.", Toast.LENGTH_LONG).show();
        } else {
            apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<ResponseData> dataCall = apiInterface.checkAuth(id, password, mac);

            dataCall.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    try {
                        ResponseData data1 = response.body();
                        List<ResponseData.goods> dataList = data1.data;
                        if (response.code() == 403) {
                            Toast.makeText(getApplicationContext(), "권한없음", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 300) {
                            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                        } else {
                            for (ResponseData.goods goods : dataList) {
                                goodsList.add(goods.goodsId);
                                goodsList2.add(goods.goodsId);
                            }
                        }
                    } catch (Exception e) {
                        Log.e("responseError", e.toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "접속 실패", Toast.LENGTH_SHORT).show();
                }
            });

        }


        top_auto.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, goodsList));

        bot_auto.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, goodsList2));

        //자동완성 어댑터


        video = getSharedPreferences("video", MODE_PRIVATE);
        videoedit = video.edit();

        videoName = video.getString("topVideo", "");
        videoName2 = video.getString("botVideo", "");

        if (!hasPermissions(PERMISSIONS)) { //퍼미션 허가를 했었는지 여부를 확인
            requestNecessaryPermissions(PERMISSIONS);//퍼미션 허가안되어 있다면 사용자에게 요청
        } else {
            //이미 사용자에게 퍼미션 허가를 받음.
        }


        top_search.setOnClickListener((View v) -> {

            apiInterface2 = APIClient.getClient().create(APIInterface.class);

            Call<ResponseInfo> call2 = apiInterface2.downInfo(id, password, mac, top_auto.getText().toString().trim());
            call2.enqueue(new Callback<ResponseInfo>() {
                @Override
                public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                    ResponseInfo responseInfo = response.body();
                    infoList = responseInfo.data;
                    if (infoList.size() == 0) {
                        Toast.makeText(getApplicationContext(), "제품 번호가 잘못되었습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "검색되었습니다. 영상을 다운로드 해주세요.", Toast.LENGTH_LONG).show();

                        imageUrl = infoList.get(0).imgPaths.toString();
                        videoUrl = new String[infoList.size()];
                        goodsIdVideo = infoList.get(0).goodId;


                        for (int i = 0; i < infoList.size(); i++) {
                            String urlTemp;
//                        videoUrl[i] = infoList.get(i).mp4Paths.toString();
//                                .add(infoList.get(i).mp4Paths.toString());
                            urlTemp = infoList.get(i).mp4Paths.toString();
//                        urlTemp.replaceFirst("http","https");

//                        videoUrl[i].replace("http","https");
                            videoUrl[i] = urlTemp;
                            Log.d("url 찍기", videoUrl[i]);
                        }

                        BitmapFactory.Options bmOptions;
                        bmOptions = new BitmapFactory.Options();
                        bmOptions.inSampleSize = 1;
                        imageUrl = imageUrl.substring(1, imageUrl.length() - 1);

                        OpenHttpConnection openHttpConnection = new OpenHttpConnection();
                        openHttpConnection.execute(top_image, imageUrl);


                    }
                }

                @Override
                public void onFailure(Call<ResponseInfo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "접속 실패", Toast.LENGTH_LONG).show();

                }
            });

        });

        bot_search.setOnClickListener((View v) -> {

            apiInterface2 = APIClient.getClient().create(APIInterface.class);

            Call<ResponseInfo> call2 = apiInterface2.downInfo(id, password, mac, bot_auto.getText().toString().trim());
            call2.enqueue(new Callback<ResponseInfo>() {
                @Override
                public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                    ResponseInfo responseInfo = response.body();
                    infoList2 = responseInfo.data;

                    if (infoList2.size() == 0) {
                        Toast.makeText(getApplicationContext(), "제품 번호가 잘못되었습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "검색되었습니다. 영상을 다운로드 해주세요.", Toast.LENGTH_LONG).show();
                        imageUrl2 = infoList2.get(0).imgPaths.toString();
                        videoUrl2 = new String[infoList2.size()];
                        goodsIdVideo = infoList2.get(0).goodId;

                        for (int i = 0; i < infoList2.size(); i++) {
                            String urlTemp;
                            urlTemp = infoList2.get(i).mp4Paths.toString();
                            videoUrl2[i] = urlTemp;
                            Log.d("url 찍기", videoUrl2[i]);
                        }

                    }


                    BitmapFactory.Options bmOptions;
                    bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
                    imageUrl2 = imageUrl2.substring(1, imageUrl2.length() - 1);

                    OpenHttpConnection openHttpConnection = new OpenHttpConnection();
                    openHttpConnection.execute(bot_image, imageUrl2);
                }


                @Override
                public void onFailure(Call<ResponseInfo> call, Throwable t) {

                }
            });

        });


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.video_down:

                        if (TextUtils.isEmpty(top_auto.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "검색을 먼저 해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            progressBar = new ProgressDialog(management.this);
                            progressBar.setMessage("다운로드중");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setIndeterminate(true);
                            progressBar.setCancelable(true);
                            URL[] fileURL = new URL[4];
                            //url 넣기
                            //배열 상태로 들어오기 때문에 첫글자와 마지막 글자를 삭제해줘야한다 . [ ]
                            for (int i = 0; i < videoUrl.length; i++) {
                                try {
                                    String test = videoUrl[i].substring(1, videoUrl[i].length() - 1).trim();
                                    fileURL[i] = new URL(test);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }

                            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                            String childName = infoList.get(0).goodId;

                            //Ex) childName = 07088859876-bed001
                            outputFile = new File(path, childName);
                            videoName = childName;
                            videoedit.putString("topVideo", videoName);
                            videoedit.commit();


                            if (outputFile.exists()) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(management.this);
                                builder.setTitle("파일 다운로드");
                                builder.setMessage("이미 SD 카드에 존재합니다. 다시 다운로드 받을까요 ?");
                                builder.setNegativeButton("아니오",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getApplicationContext(), "다운로드를 하지 않았습니다.", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        outputFile.delete();

                                        final DownloadFilesTask downloadFilesTask = new DownloadFilesTask(management.this);
                                        downloadFilesTask.execute(fileURL);
//                                        downloadFilesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,fileURL);
                                        progressBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialog) {
                                                downloadFilesTask.cancel(true);
                                            }
                                        });
                                    }
                                });
                                builder.show();
                            } else {
                                final DownloadFilesTask downloadTask = new DownloadFilesTask(management.this);
                                downloadTask.execute(fileURL);
//                                downloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,fileURL);

                                progressBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        downloadTask.cancel(true);

                                    }
                                });

                            }

                            OutputStream outputStream = null;
                            String dirPath = Environment.getExternalStorageDirectory().toString();
                            File file = new File(dirPath, infoList.get(0).goodId + ".jpg");

                            try {

                                outputStream = new FileOutputStream(file);
                                mSaveBm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                outputStream.flush();
                                outputStream.close();

                                //뒤에 /test.jpg 변수명 + jpg로 변경
                                sharedPic = BitmapFactory.decodeFile(dirPath + "/" + infoList.get(0).goodId + ".jpg");

                                String image = BitMapToString(sharedPic);
                                SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("imageStrings", image);

                                editor.commit();

//                                top_image.setImageBitmap(mSaveBm);


                                Toast.makeText(getApplicationContext(), "이미지를 저장하였습니다.", Toast.LENGTH_LONG).show();

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        break;

                    case R.id.goodsInfobtn:
                        if (infoList.size() == 0)
                            Toast.makeText(getApplicationContext(), "검색 된 데이터가 없습니다.", Toast.LENGTH_LONG).show();
                        else {
                            Intent intent = new Intent(getApplicationContext(), goodsInfo.class);
                            startActivity(intent);
                        }
                        break;


                    case R.id.video_down2:

                        progressBar = new ProgressDialog(management.this);
                        progressBar.setMessage("다운로드중");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setIndeterminate(true);
                        progressBar.setCancelable(true);
                        URL[] fileURL2 = new URL[4];

                        //url 넣기
                        //배열 상태로 들어오기 때문에 첫글자와 마지막 글자를 삭제해줘야한다 . [ ]

                        for (int i = 0; i < videoUrl2.length; i++) {
                            try {
                                String test = videoUrl2[i].substring(1, videoUrl2[i].length() - 1).trim();
                                Log.i("testVideo", test);
                                fileURL2[i] = new URL(test);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }

                        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                        String childName2 = infoList2.get(0).goodId;

                        //Ex) childName = 07088859876-bed001
                        outputFile = new File(path, childName2);
                        videoName2 = childName2;
                        videoedit.putString("botVideo", videoName2);
                        videoedit.commit();


                        if (outputFile.exists()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(management.this);
                            builder.setTitle("파일 다운로드");
                            builder.setMessage("이미 SD 카드에 존재합니다. 다시 다운로드 받을까요 ?");
                            builder.setNegativeButton("아니오",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext(), "다운로드를 하지 않았습니다.", Toast.LENGTH_LONG).show();
                                        }
                                    });
                            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    outputFile.delete();

                                    final DownloadFilesTask downloadFilesTask2 = new DownloadFilesTask(management.this);
                                    downloadFilesTask2.execute(fileURL2);
//                                        downloadFilesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,fileURL);
                                    progressBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                            downloadFilesTask2.cancel(true);
                                        }
                                    });
                                }
                            });
                            builder.show();
                        } else {
                            final DownloadFilesTask downloadTask2 = new DownloadFilesTask(management.this);
                            downloadTask2.execute(fileURL2);
//                                downloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,fileURL);

                            progressBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    downloadTask2.cancel(true);

                                }
                            });

                        }

                        OutputStream outputStream2 = null;
                        String dirPath2 = Environment.getExternalStorageDirectory().toString();
                        File file2 = new File(dirPath2, infoList2.get(0).goodId + ".jpg");

                        try {

                            outputStream2 = new FileOutputStream(file2);
                            mSaveBm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream2);
                            outputStream2.flush();
                            outputStream2.close();

                            //뒤에 /test.jpg 변수명 + jpg로 변경
                            sharedPic = BitmapFactory.decodeFile(dirPath2 + "/" + infoList2.get(0).goodId + ".jpg");

                            String image = BitMapToString(sharedPic);
                            SharedPreferences pref = getSharedPreferences("image", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("imageStrings2", image);

                            editor.commit();

//                                    bot_image.setImageBitmap(mSaveBm);


                            Toast.makeText(getApplicationContext(), "이미지를 저장하였습니다.", Toast.LENGTH_LONG).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.goodsInfobtn2:
                        if (infoList2.size() == 0)
                            Toast.makeText(getApplicationContext(), "검색 된 데이터가 없습니다.", Toast.LENGTH_LONG).show();
                        else {
                            Intent intent = new Intent(getApplicationContext(), goodsInfo2.class);
                            startActivity(intent);
                        }
                        break;
                }
            }
        };


        video_down.setOnClickListener(cl);
        info_save.setOnClickListener(cl);
        video_down2.setOnClickListener(cl);
        info_save2.setOnClickListener(cl);

    }


    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    private class DownloadFilesTask extends AsyncTask<URL, String, Long> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadFilesTask(Context context) {
            this.context = context;
        }


        //파일 다운로드를 시작하기 전에 프로그레스바를 화면에 보여줍니다.
        @Override
        protected void onPreExecute() { //2
            super.onPreExecute();

            //사용자가 다운로드 중 파워 버튼을 누르더라도 CPU가 잠들지 않도록 해서
            //다시 파워버튼 누르면 그동안 다운로드가 진행되고 있게 됩니다.
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mWakeLock.acquire();

            progressBar.show();
        }


        //파일 다운로드를 진행합니다.
        @Override
        protected Long doInBackground(URL... urls) { //3
            int count;
            long FileSize = -1;
            InputStream input = null;
            OutputStream output = null;
            URLConnection connection = null;

            try {
                int arr_size = urls.length;
                for (int i = 0; i < arr_size; i++) {
                    String childName = goodsIdVideo;
//                    URL url = new URL(string_url.);

                    if (infoList == null || !infoList2.isEmpty()) {
                        if (infoList2.get(i).lang.equalsIgnoreCase("한국어")) {
                            childName = childName + "_kor.mp4";
                            lang = "kor";
                        } else if (infoList2.get(i).lang.equalsIgnoreCase("영어")) {
                            childName = childName + "_eng.mp4";
                            lang = "eng";
                        } else if (infoList2.get(i).lang.equalsIgnoreCase("일본어")) {
                            childName = childName + "_jap.mp4";
                            lang = "jap";
                        } else if (infoList2.get(i).lang.equalsIgnoreCase("중국어")) {
                            childName = childName + "_chi.mp4";
                            lang = "chi";
                        }

                    } else if (infoList2 == null || !infoList.isEmpty()) {
                        if (infoList.get(i).lang.equalsIgnoreCase("한국어")) {
                            childName = childName + "_kor.mp4";
                            lang = "kor";
                        } else if (infoList.get(i).lang.equalsIgnoreCase("영어")) {
                            childName = childName + "_eng.mp4";
                            lang = "eng";
                        } else if (infoList.get(i).lang.equalsIgnoreCase("일본어")) {
                            childName = childName + "_jap.mp4";
                            lang = "jap";
                        } else if (infoList.get(i).lang.equalsIgnoreCase("중국어")) {
                            childName = childName + "_chi.mp4";
                            lang = "chi";
                        }
                    }

                    connection = urls[i].openConnection();
                    connection.connect();


                    //파일 크기를 가져옴
                    FileSize = connection.getContentLength();

                    //URL 주소로부터 파일다운로드하기 위한 input stream
                    input = new BufferedInputStream(urls[i].openStream(), 8192);
//                Context c = getApplication();
//                path = c.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    outputFile = new File(path, childName); //파일명까지 포함함 경로의 File 객체 생성

                    // SD카드에 저장하기 위한 Output stream
                    output = new FileOutputStream(outputFile);


                    byte data[] = new byte[1024];
                    long downloadedSize = 0;
                    while ((count = input.read(data)) != -1) {
                        //사용자가 BACK 버튼 누르면 취소가능
                        if (isCancelled()) {
                            input.close();
                            return Long.valueOf(-1);
                        }

                        downloadedSize += count;

                        if (FileSize > 0) {
                            float per = ((float) downloadedSize / FileSize) * 100;
                            String str = "Downloaded " + downloadedSize + "KB / " + FileSize + "KB (" + (int) per + "%)";
                            publishProgress("" + (int) ((downloadedSize * 100) / FileSize), str);

                        }
                        //파일에 데이터를 기록합니다.
                        output.write(data, 0, count);

                    }

                    // Flush output
                    output.flush();

                    // Close streams
                    output.close();
                    input.close();
                }

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                mWakeLock.release();

            }
            return FileSize;
        }


        //다운로드 중 프로그레스바 업데이트
        @Override
        protected void onProgressUpdate(String... progress) { //4
            super.onProgressUpdate(progress);

            // if we get here, length is known, now set indeterminate to false
            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
            progressBar.setProgress(Integer.parseInt(progress[0]));
            progressBar.setMessage(progress[1]);
        }

        //파일 다운로드 완료 후
        @Override
        protected void onPostExecute(Long size) { //5
            super.onPostExecute(size);

            progressBar.dismiss();

            if (size > 0) {
                Toast.makeText(getApplicationContext(), "다운로드 완료되었습니다. 파일 크기=" + size.toString(), Toast.LENGTH_LONG).show();

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(Uri.fromFile(outputFile));
                sendBroadcast(mediaScanIntent);


//                playVideo(outputFile.getPath());

            } else
                Toast.makeText(getApplicationContext(), "다운로드 에러", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions,
                                           int[] grantResults) {
        switch (permsRequestCode) {

            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean readAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if (!readAccepted || !writeAccepted) {
                            showDialogforPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
                            return;
                        }
                    }
                }
                break;
        }
    }

    private void showDialogforPermission(String msg) {

        final AlertDialog.Builder myDialog = new AlertDialog.Builder(management.this);
        myDialog.setTitle("알림");
        myDialog.setMessage(msg);
        myDialog.setCancelable(false);
        myDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);
                }

            }
        });
        myDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        myDialog.show();
    }

    private void playVideo(String path) {
//        Uri videoUri = Uri.fromFile(new File(path));
        Uri videoUri = FileProvider.getUriForFile(getBaseContext(), "com.example.videodowntest.provider", new File(path));

        Intent videoIntent = new Intent(getApplicationContext(), video.class);
        videoIntent.putExtra("videoUri", videoUri);
        startActivity(videoIntent);
//        if (videoIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(Intent.createChooser(videoIntent, null));
//        }
    }


    private class OpenHttpConnection extends AsyncTask<Object, Void, Bitmap> {
        private ImageView bImageView;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap mbitmap = null;
            bImageView = (ImageView) objects[0];
            String url = (String) objects[1];
            InputStream in = null;
            try {
                in = new java.net.URL(url).openStream();
                mbitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return mbitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mSaveBm = bitmap;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}

