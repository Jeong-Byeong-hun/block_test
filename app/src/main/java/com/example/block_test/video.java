package com.example.block_test;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import static com.example.block_test.management.videoName;

public class video extends baseActivity {

    public void init() {
        setContentView(R.layout.activity_video);


        final VideoView videoView = (VideoView) findViewById(R.id.video);

        Intent intent = getIntent();

        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath();


        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);


        //sd카드 부분으로 교체
//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/raw/abcd01066643050");

        videoView.setMediaController(mediaController);

        videoView.setVideoPath(path+"/Download/"+videoName);
//        videoView.setVideoURI(video);

        videoView.requestFocus();

        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()  {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(getApplicationContext(),store.class);
                startActivity(intent);
            }
        });

    }
}
