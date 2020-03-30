package com.example.block_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class video extends baseActivity {

    String videoURL = null;
    SharedPreferences getURL;

    public void init() {
        setContentView(R.layout.activity_video);


        final VideoView videoView = (VideoView) findViewById(R.id.video);

        Intent intent = getIntent();
        String videoForm = intent.getStringExtra("videoForm");
        String location = intent.getStringExtra("location");
        String form = intent.getStringExtra("form");
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath();

        getURL = getSharedPreferences("video", MODE_PRIVATE);

        if (location.equalsIgnoreCase("top"))
            videoURL = getURL.getString("topVideo", "");
        else if (location.equalsIgnoreCase("bottom"))
            videoURL = getURL.getString("botVideo", "");


        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);


        //sd카드 부분으로 교체
//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/raw/abcd01066643050");

        videoView.setMediaController(mediaController);

        videoView.setVideoPath(path + "/Download/" + videoURL + videoForm);
//        videoView.setVideoURI(video);

        videoView.requestFocus();

        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent1 = new Intent(getApplicationContext(), store.class);
                intent1.putExtra("location",location);
                intent1.putExtra("form", form);
                intent1.putExtra("videoForm", videoForm);
                startActivity(intent1);
                finish();
            }
        });

    }
}
