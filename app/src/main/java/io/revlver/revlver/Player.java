package io.revlver.revlver;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Context;

public class Player extends AppCompatActivity {

    ImageButton imageButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        addListenerOnButton();


        //-----------------FONT-----------------
        TextView textViewCustom = (TextView) findViewById(R.id.song_title);
        Typeface SongTitleFont = Typeface.createFromAsset(getAssets(), "fonts/GoldenSans.otf");
        textViewCustom.setTypeface(SongTitleFont);

        TextView textViewCustom2 = (TextView) findViewById(R.id.song_artist);
        Typeface ArtistFont = Typeface.createFromAsset(getAssets(), "fonts/GoldenSans.otf");
        textViewCustom2.setTypeface(ArtistFont);

        TextView textViewCustom3 = (TextView) findViewById(R.id.song_start);
        Typeface SongStartFont = Typeface.createFromAsset(getAssets(), "fonts/GoldenSans.otf");
        textViewCustom3.setTypeface(SongStartFont);

        TextView textViewCustom4 = (TextView) findViewById(R.id.song_end);
        Typeface SongEndFont = Typeface.createFromAsset(getAssets(), "fonts/GoldenSans.otf");
        textViewCustom4.setTypeface(SongEndFont);

        //------------PLAY BUTTON----------------------
        ImageButton play = (ImageButton)this.findViewById(R.id.play);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.goldenslumbers);
        play.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {



                if(mp.isPlaying())  // If the music is playing
                    mp.pause(); // Pause the music player

                else    // If it's not playing
                    mp.start(); // Resume the music player
            }
        });

    }

    //------------LIBRARY BUTTON----------------------
    public void addListenerOnButton() {
        final Context context = this;
        ImageButton back_2_lib = (ImageButton) findViewById(R.id.back_2_lib);
        back_2_lib.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }

        });

    }


    //-----------------SHUFFLE-----------------
    public void shuffle_off_button(View v) {
        ImageButton Shuffle_off = (ImageButton) findViewById(R.id.shuffle_off);
        Shuffle_off.setVisibility(View.INVISIBLE);
        ImageButton Shuffle_on = (ImageButton) findViewById(R.id.shuffle_on);
        Shuffle_on.setVisibility(View.VISIBLE);
    }

    public void shuffle_on_button(View v) {
        ImageButton Shuffle_off = (ImageButton) findViewById(R.id.shuffle_off);
        Shuffle_off.setVisibility(View.VISIBLE);
        ImageButton Shuffle_on = (ImageButton) findViewById(R.id.shuffle_on);
        Shuffle_on.setVisibility(View.INVISIBLE);
    }

    //-----------------HEART-----------------
    public void heart(View v) {
        ImageButton heart = (ImageButton) findViewById(R.id.heart);
        heart.setVisibility(View.INVISIBLE);
        ImageButton hearted = (ImageButton) findViewById(R.id.hearted);
        hearted.setVisibility(View.VISIBLE);
    }

    public void hearted(View v) {
        ImageButton heart = (ImageButton) findViewById(R.id.heart);
        heart.setVisibility(View.VISIBLE);
        ImageButton hearted = (ImageButton) findViewById(R.id.hearted);
        hearted.setVisibility(View.INVISIBLE);
    }

    //-----------------QUEUE-----------------
    public void queue(View v) {
        ImageButton queue = (ImageButton) findViewById(R.id.queue);
        queue.setVisibility(View.INVISIBLE);
        ImageButton cancel_queue = (ImageButton) findViewById(R.id.cancel_queue);
        cancel_queue.setVisibility(View.VISIBLE);
    }

    public void cancel_queue(View v) {
        ImageButton queue = (ImageButton) findViewById(R.id.queue);
        queue.setVisibility(View.VISIBLE);
        ImageButton cancel_queue = (ImageButton) findViewById(R.id.cancel_queue);
        cancel_queue.setVisibility(View.INVISIBLE);
    }

    //-----------------REPEAT-----------------
    public void repeat_off(View v) {
        ImageButton repeat_off = (ImageButton) findViewById(R.id.repeat_off);
        repeat_off.setVisibility(View.INVISIBLE);
        ImageButton repeat_one = (ImageButton) findViewById(R.id.repeat_one);
        repeat_one.setVisibility(View.VISIBLE);
        ImageButton repeat_all = (ImageButton) findViewById(R.id.repeat_all);
        repeat_all.setVisibility(View.INVISIBLE);
    }

    public void repeat_one(View v) {
        ImageButton repeat_off = (ImageButton) findViewById(R.id.repeat_off);
        repeat_off.setVisibility(View.INVISIBLE);
        ImageButton repeat_one = (ImageButton) findViewById(R.id.repeat_one);
        repeat_one.setVisibility(View.INVISIBLE);
        ImageButton repeat_all = (ImageButton) findViewById(R.id.repeat_all);
        repeat_all.setVisibility(View.VISIBLE);
    }

    public void repeat_all(View v) {
        ImageButton repeat_off = (ImageButton) findViewById(R.id.repeat_off);
        repeat_off.setVisibility(View.VISIBLE);
        ImageButton repeat_one = (ImageButton) findViewById(R.id.repeat_one);
        repeat_one.setVisibility(View.INVISIBLE);
        ImageButton repeat_all = (ImageButton) findViewById(R.id.repeat_all);
        repeat_all.setVisibility(View.INVISIBLE);
    }

    //-----------------EQUALIZER-----------------
    public void EQ(View v) {
        Intent intent = new Intent();
        intent.setAction("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
        if((intent.resolveActivity(getPackageManager()) != null)) {
            startActivityForResult(intent, RESULT_FIRST_USER); // REQUEST_EQ is an int of your choosing
        } else {
            // No equalizer found :(
        }
    }

}

