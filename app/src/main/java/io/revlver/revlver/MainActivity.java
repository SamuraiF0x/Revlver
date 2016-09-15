package io.revlver.revlver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();



        //-----------------FONT-----------------
        TextView textViewCustom = (TextView) findViewById(R.id.library_text);
        Typeface Library = Typeface.createFromAsset(getAssets(), "fonts/akrobat/Akrobat-Thin.otf");
        textViewCustom.setTypeface(Library);

        TextView textViewCustom2 = (TextView) findViewById(R.id.playlist_text);
        Typeface Playlist = Typeface.createFromAsset(getAssets(), "fonts/akrobat/Akrobat-Thin.otf");
        textViewCustom2.setTypeface(Playlist);

        TextView textViewCustom3 = (TextView) findViewById(R.id.most_played);
        Typeface MostPlayed = Typeface.createFromAsset(getAssets(), "fonts/akrobat/Akrobat-Thin.otf");
        textViewCustom3.setTypeface(MostPlayed);
    }

    //------------LIBRARY BUTTON----------------------
    public void addListenerOnButton() {
        final Context context = this;
        ImageButton go_to_library = (ImageButton) findViewById(R.id.library_button);
        go_to_library.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Library.class);
                startActivity(intent);

            }

        });

        final Context context2 = this;
        ImageButton go_to_player = (ImageButton) findViewById(R.id.player_bar_mini);
        go_to_player.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context2, Player.class);
                startActivity(intent);

            }

        });

    }

    //-----------------HEART-----------------
    public void heart(View v) {
        ImageButton heart = (ImageButton) findViewById(R.id.player_heart_mini);
        heart.setVisibility(View.INVISIBLE);
        ImageButton hearted = (ImageButton) findViewById(R.id.player_hearted_mini);
        hearted.setVisibility(View.VISIBLE);
    }

    public void hearted(View v) {
        ImageButton heart = (ImageButton) findViewById(R.id.player_heart_mini);
        heart.setVisibility(View.VISIBLE);
        ImageButton hearted = (ImageButton) findViewById(R.id.player_hearted_mini);
        hearted.setVisibility(View.INVISIBLE);
    }

}