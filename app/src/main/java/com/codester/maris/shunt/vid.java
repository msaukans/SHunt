package com.codester.maris.shunt;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class vid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vid);

        VideoView videoView =(VideoView)findViewById(R.id.vw);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        String ur = "android.resource://com.sample.maris.shunt/"+R.raw.videoplayback;
        Uri uri= Uri.parse(ur);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}
