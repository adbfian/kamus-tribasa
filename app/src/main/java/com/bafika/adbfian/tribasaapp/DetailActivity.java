package com.bafika.adbfian.tribasaapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bafika.adbfian.tribasaapp.model.DataKata;
import com.bafika.adbfian.tribasaapp.model.SQLiteDB;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    private TextView detilNgoko, detilKromo, detilIndo;
    private SQLiteDB sqLiteDB;
    private DataKata dataKata;
    private Button voiceNgoko, voiceKromo, voiceIndo;
    private MediaPlayer mediaPlayer;

    public static void start(Context context){
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, DataKata dataKata) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.class.getSimpleName(), dataKata);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar)findViewById(R.id.material_toolbar);
        toolbar.setTitle("Detail Kata");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detilIndo = (TextView) findViewById(R.id.detilIndo);
        detilKromo = (TextView) findViewById(R.id.detilKromo);
        detilNgoko = (TextView) findViewById(R.id.detilNgoko);
        voiceIndo = (Button) findViewById(R.id.voiceIndo);
        voiceKromo = (Button) findViewById(R.id.voiceKromo);
        voiceNgoko = (Button) findViewById(R.id.voiceNgoko);

        dataKata = getIntent().getParcelableExtra(DetailActivity.class.getSimpleName());
        if (dataKata != null) {
            detilNgoko.setText(dataKata.getNgoko_kata());
            detilKromo.setText(dataKata.getKromo_kata());
            detilIndo.setText(dataKata.getIndo_kata());
            voiceNgoko.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer = new MediaPlayer();
                    String Ngoko_PATH = "/data/data/" + getPackageName() + "/voice/ngoko/" + dataKata.getNgoko_suara();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Uri uri = Uri.parse(Ngoko_PATH);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), uri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(DetailActivity.this, "Suara " + dataKata.getNgoko_kata() + " (Bunyi)" , Toast.LENGTH_SHORT).show();
                        voiceNgoko.setEnabled(false);
                        voiceKromo.setEnabled(false);
                        voiceIndo.setEnabled(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            voiceNgoko.setEnabled(true);
                            voiceKromo.setEnabled(true);
                            voiceIndo.setEnabled(true);
                            mediaPlayer.release();
                            mediaPlayer = null;
                            Toast.makeText(DetailActivity.this, "Suara " + dataKata.getNgoko_kata() + " (Selesai)" , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            voiceKromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer = new MediaPlayer();
                    String Ngoko_PATH = "/data/data/" + getPackageName() + "/voice/kromo/" + dataKata.getKromo_suara();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Uri uri = Uri.parse(Ngoko_PATH);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), uri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(DetailActivity.this, "Suara " + dataKata.getKromo_kata() + " (Bunyi)" , Toast.LENGTH_SHORT).show();
                        voiceNgoko.setEnabled(false);
                        voiceKromo.setEnabled(false);
                        voiceIndo.setEnabled(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            voiceNgoko.setEnabled(true);
                            voiceKromo.setEnabled(true);
                            voiceIndo.setEnabled(true);
                            mediaPlayer.release();
                            mediaPlayer = null;
                            Toast.makeText(DetailActivity.this, "Suara " + dataKata.getKromo_kata() + " (Selesai)" , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            voiceIndo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer = new MediaPlayer();
                    String Ngoko_PATH = "/data/data/" + getPackageName() + "/voice/indo/" + dataKata.getIndo_suara();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Uri uri = Uri.parse(Ngoko_PATH);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), uri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Toast.makeText(DetailActivity.this, "Suara " + dataKata.getIndo_kata() + " (Bunyi)" , Toast.LENGTH_SHORT).show();
                        voiceNgoko.setEnabled(false);
                        voiceKromo.setEnabled(false);
                        voiceIndo.setEnabled(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            voiceNgoko.setEnabled(true);
                            voiceKromo.setEnabled(true);
                            voiceIndo.setEnabled(true);
                            mediaPlayer.release();
                            mediaPlayer = null;
                            Toast.makeText(DetailActivity.this, "Suara " + dataKata.getIndo_kata() + " (Selesai)" , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else {
            detilIndo.setText(null);
            detilNgoko.setText(null);
            detilKromo.setText(null);
        }
        sqLiteDB = new SQLiteDB(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home ) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
