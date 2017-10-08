package com.bafika.adbfian.tribasaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bafika.adbfian.tribasaapp.model.SQLiteDB;

public class TerjemahanIndo extends AppCompatActivity {
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private AutoCompleteTextView terIndo;
    private TextView terKromo, terNgoko;
    private Button btnPilihBhs;
    private SQLiteDB sqLiteDB = null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terjemahan_indo);

        toolbar = (Toolbar)findViewById(R.id.material_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sqLiteDB = new SQLiteDB(this);
        db = sqLiteDB.getReadableDatabase();

        terIndo = (AutoCompleteTextView) findViewById(R.id.terIndo);
        terKromo = (TextView) findViewById(R.id.terKromo);
        terNgoko = (TextView) findViewById(R.id.terNgoko);
        btnPilihBhs = (Button) findViewById(R.id.btnPilihBhs);
        String[] indo = getResources().getStringArray(R.array.indo);
        ArrayAdapter<String> adater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, indo);
        terIndo.setAdapter(adater);
    }

    public void getBahasa(View v) {
        btnPilihBhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(TerjemahanIndo.this, btnPilihBhs);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pilih_bhs, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.PilihNgoko) {
                            Intent tambah = new Intent(TerjemahanIndo.this, Terjemahan.class);
                            tambah.putExtra("pesan", "bahasa Ngoko");
                            startActivity(tambah);
                            return true;
                        }
                        if (id == R.id.PilihKromo) {
                            Intent tambah = new Intent(TerjemahanIndo.this, TerjemahanKromo.class);
                            tambah.putExtra("pesan", "bahasa Kromo");
                            startActivity(tambah);
                            return true;
                        }
                        if (id == R.id.PilihIndo) {
                            Intent tambah = new Intent(TerjemahanIndo.this, TerjemahanIndo.class);
                            tambah.putExtra("pesan", "bahasa Indonesia");
                            startActivity(tambah);
                            return true;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    public void getArti(View v) {
        String ngoko = "";
        String kromo = "";
        String indo = terIndo.getText().toString();

        cursor = db.rawQuery("SELECT * FROM kamus WHERE indo_kata='" + indo + "' ORDER BY indo_kata", null);
        if (cursor.moveToFirst()) {
            for (;!cursor.isAfterLast();
                 cursor.moveToNext()) {
                ngoko = cursor.getString(1);
                kromo = cursor.getString(3);
            }
        } else {
            Toast.makeText(TerjemahanIndo.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
        }
        terNgoko.setText(ngoko);
        terKromo.setText(kromo);
    }

    public void ClearChar(View v) {
        terNgoko.setText(null);
        terKromo.setText(null);
        terIndo.setText(null);
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            cursor.close();
            db.close();
        } catch (Exception e) {

        }
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
            Intent tambah = new Intent(TerjemahanIndo.this, MenuUTama.class);
            tambah.putExtra("pesan", "home");
            startActivity(tambah);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
