package io.revlver.revlver;

/**
 * Created by Samurai Fox on 09-09-16.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;
import io.revlver.revlver.MusicService.MusicBinder;

import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

public class Library_FolderTab extends Activity implements MediaPlayerControl {

    private ArrayList<Song> songList;
    private ListView songView;

    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;
    private MediaController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_folder_tab);
        addListenerOnButton();


        songView = (ListView)findViewById(R.id.song_list);
        songList = new ArrayList<Song>();
        getSongDataList();

        Collections.sort(songList, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        SongAdapter songAdt = new SongAdapter(this, songList);
        songView.setAdapter(songAdt);
    }

    //connect to the service
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBinder binder = (MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            musicSrv.setList(songList);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    public void getSongDataList() {
        //retrieve song info

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int albumColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ALBUM);

            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisAlbum = musicCursor.getString(albumColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist, thisAlbum));
            }
            while (musicCursor.moveToNext());
        }
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
    //-----------------HEART-----------------

    //------------LIBRARY BUTTON-------------
    public void addListenerOnButton() {
        final Context context = this;
        ImageButton player_bar_mini = (ImageButton) findViewById(R.id.player_bar_mini);
        player_bar_mini.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Player.class);
                startActivity(intent);
            }
        });

        ImageButton back_2_lib = (ImageButton) findViewById(R.id.back_2_lib);
        back_2_lib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton folder = (ImageButton) findViewById(R.id.folder);
        folder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Library_FolderTab.class);
                startActivity(intent);
            }
        });

        ImageButton artists = (ImageButton) findViewById(R.id.artists);
        artists.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ArtistsTab.class);
                startActivity(intent);
            }
        });

        ImageButton albums = (ImageButton) findViewById(R.id.albums);
        albums.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, AlbumsTab.class);
                startActivity(intent);
            }
        });

        ImageButton songs = (ImageButton) findViewById(R.id.songs);
        songs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SongsTab.class);
                startActivity(intent);
            }
        });
    }
    //------------LIBRARY BUTTON-------------



    public void songPicked(View view){
        musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
        musicSrv.playSong();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.player_heart_mini:
                //shuffle
                break;
            case R.id.player_pause_mini:
                stopService(playIntent);
                musicSrv=null;
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }


    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int i) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
