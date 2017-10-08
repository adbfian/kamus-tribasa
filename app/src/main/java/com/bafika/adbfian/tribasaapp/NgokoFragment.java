package com.bafika.adbfian.tribasaapp;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bafika.adbfian.tribasaapp.model.AdapterNgoko;
import com.bafika.adbfian.tribasaapp.model.DataKata;
import com.bafika.adbfian.tribasaapp.model.RecyclerItemClickListener;
import com.bafika.adbfian.tribasaapp.model.SQLiteDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NgokoFragment extends Fragment implements RecyclerItemClickListener {

    private SQLiteDB sqLiteDB;
    private AdapterNgoko adapterNgoko;
    private List<DataKata> daftarKata;
    private RecyclerView tampilkata;
    private LinearLayoutManager linearLayoutManager;
    EditText searchText;

    public NgokoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngoko, container, false);
        tampilkata = (RecyclerView)view.findViewById(R.id.tampilkata);
//        searchText = (EditText) view.findViewById(R.id.searchBox);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapterNgoko = new AdapterNgoko(getActivity());
        adapterNgoko.setOnItemClickListener(this);

        tampilkata.setLayoutManager(linearLayoutManager);
        tampilkata.setAdapter(adapterNgoko);

        return view;
    }

    public void onStart() {
        super.onStart();
        loadData();
    }

    void loadData() {
        sqLiteDB = new SQLiteDB(getActivity());

        try {
            sqLiteDB.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        sqLiteDB.openDataBase();

        List<DataKata> daftarKata = new ArrayList<>();
        Cursor cursor = sqLiteDB.Get_Data();
        DataKata dataKata;

        if (cursor.moveToFirst()) {
            do {
                dataKata = new DataKata();

                dataKata.setId(cursor.getInt(0));
                dataKata.setNgoko_kata(cursor.getString(1));
                dataKata.setNgoko_suara(cursor.getString(2));
                dataKata.setKromo_kata(cursor.getString(3));
                dataKata.setKromo_suara(cursor.getString(4));
                dataKata.setIndo_kata(cursor.getString(5));
                dataKata.setIndo_suara(cursor.getString(6));

                daftarKata.add(dataKata);

            } while (cursor.moveToNext());
        }
        adapterNgoko.clear();
        adapterNgoko.addAll(daftarKata);
    }

    @Override
    public void onItemClick(int position, View view) {
        DetailActivity.start(getActivity(), adapterNgoko.getItem(position));
    }

}
