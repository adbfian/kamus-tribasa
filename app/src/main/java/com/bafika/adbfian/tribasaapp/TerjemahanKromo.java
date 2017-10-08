package com.bafika.adbfian.tribasaapp;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bafika.adbfian.tribasaapp.model.SQLiteDB;

public class TerjemahanKromo extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    private AutoCompleteTextView terKromo;
    private TextView terNgoko, terIndo;
    private Button btnPilihBhs;
    private SQLiteDB sqLiteDB = null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terjemahan_kromo);

        toolbar = (Toolbar)findViewById(R.id.material_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sqLiteDB = new SQLiteDB(this);
        db = sqLiteDB.getReadableDatabase();

        terKromo = (AutoCompleteTextView) findViewById(R.id.terKromo);
        terNgoko = (TextView) findViewById(R.id.terNgoko);
        terIndo = (TextView) findViewById(R.id.terIndo);
        btnPilihBhs = (Button) findViewById(R.id.btnPilihBhs);
        String[] kromo = getResources().getStringArray(R.array.kromo);
        ArrayAdapter<String> adater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kromo);
        terKromo.setAdapter(adater);
    }

    public void getBahasa(View v) {
        btnPilihBhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(TerjemahanKromo.this, btnPilihBhs);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pilih_bhs, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.PilihNgoko) {
                            Intent tambah = new Intent(TerjemahanKromo.this, Terjemahan.class);
                            tambah.putExtra("pesan", "bahasa Ngoko");
                            startActivity(tambah);
                            return true;
                        }
                        if (id == R.id.PilihKromo) {
                            Intent tambah = new Intent(TerjemahanKromo.this, TerjemahanKromo.class);
                            tambah.putExtra("pesan", "bahasa Kromo");
                            startActivity(tambah);
                            return true;
                        }
                        if (id == R.id.PilihIndo) {
                            Intent tambah = new Intent(TerjemahanKromo.this, TerjemahanIndo.class);
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
        String indo = "";
        String kromo = terKromo.getText().toString();

        cursor = db.rawQuery("SELECT * FROM kamus WHERE kromo_kata='" + kromo + "' ORDER BY kromo_kata", null);
        if (cursor.moveToFirst()) {
            for (;!cursor.isAfterLast();
                 cursor.moveToNext()) {
                ngoko = cursor.getString(1);
                indo = cursor.getString(5);
            }
        } else {
            Toast.makeText(TerjemahanKromo.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
        }
        terNgoko.setText(ngoko);
        terIndo.setText(indo);
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
            Intent tambah = new Intent(TerjemahanKromo.this, MenuUTama.class);
            tambah.putExtra("pesan", "home");
            startActivity(tambah);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
