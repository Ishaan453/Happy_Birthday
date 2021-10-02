package com.example.justopenit;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private int comInt;
    private String[] comments = {"YUMMY", "DELICIOUS", "MOUTH WATERING", "LUSCIOUS", "TASTY", "SWEET", "FLAVOURSOME", "APPETIZING", "DIVINE", "TEMPTING"};
    private int currentImage = 0;
    private MediaPlayer eatSound;
    private MediaPlayer eatSound2;
    private ImageView imageView;
    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    private int randEat;
    private MediaPlayer song;
    private Button songbtn;
    private TextView textView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.imageView = (ImageView) findViewById(R.id.imageView4);
        this.button = (Button) findViewById(R.id.button3);
        this.textView = (TextView) findViewById(R.id.textView);
        this.songbtn = (Button) findViewById(R.id.button);
        this.eatSound = MediaPlayer.create(this, R.raw.eat);
        this.song = MediaPlayer.create(this, R.raw.song);
        this.eatSound2 = MediaPlayer.create(this, R.raw.eat2);
        this.song.start();
        song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                song.release();
                song = null;
            }
        });
    }

    public void changeImg(View v) {
        int i = this.currentImage + 1;
        this.currentImage = i;
        if (i == 5) {
            this.button.setText("CLICK ME TO EAT THE CAKE!");
            this.currentImage = 0;
            int[] iArr = this.images;
            int length = 0 % iArr.length;
            this.currentImage = length;
            this.imageView.setImageResource(iArr[length]);
            return;
        }
        this.randEat = (int) (Math.random() * 2.0d);
        this.imageView.setImageResource(this.images[this.currentImage]);
        int i2 = this.randEat;
        if (i2 == 0) {
            this.eatSound.start();
        } else if (i2 == 1) {
            this.eatSound2.start();
        }
        if (this.currentImage == 4) {
            this.button.setText("BRING ME MORE!");
        }
        int random = (int) (Math.random() * 10.0d);
        this.comInt = random;
        this.textView.setText(this.comments[random]);
    }

    public void songctrlStart(View v) {
        if (this.song == null) {
            song = MediaPlayer.create(this, R.raw.song);
            song.start();
            song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    song.release();
                    song = null;
                }
            });
        }
    }

    public void songctrlStop(View v) {
        MediaPlayer mediaPlayer = this.song;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.song = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.eatSound.release();
        this.song.release();
        this.eatSound2.release();
    }
}
