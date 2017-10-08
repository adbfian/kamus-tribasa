package com.bafika.adbfian.tribasaapp;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bafika.adbfian.tribasaapp.Tab.FragmentAdapter;
import com.bafika.adbfian.tribasaapp.Tab.SlidingTabLayout;
import com.bafika.adbfian.tribasaapp.model.AdapterNgoko;
import com.bafika.adbfian.tribasaapp.model.DataKata;
import com.bafika.adbfian.tribasaapp.model.RecyclerItemClickListener;
import com.bafika.adbfian.tribasaapp.model.SQLiteDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;
    RecyclerView tampildata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.material_toolbar);
        toolbar.setTitle(R.string.daftarKata);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.konten_tab);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), this));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.style_tab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        slidingTabLayout.setCustomTabView(R.layout.pager_tab, R.id.tab_view);
        slidingTabLayout.setViewPager(viewPager);
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

}
