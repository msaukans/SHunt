package com.codester.maris.shunt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final ImageView iv = (ImageView) findViewById(R.id.img1);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation){

            }

            @Override
            public void onAnimationEnd(Animation animation){
                iv.startAnimation(an2);
                finish();
                Intent i = new Intent(welcome.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat (Animation animation){

            }
        });//end animation listener
        //openPlay(); //call audio method
    }//end onCreate

    public void openPlay(){
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.sound);
        sound.start();
    }

}//end class
