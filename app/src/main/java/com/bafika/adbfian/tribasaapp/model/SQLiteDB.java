package com.bafika.adbfian.tribasaapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by adbfian on 24/09/2016.
 */
public class SQLiteDB extends SQLiteOpenHelper {
    String DB_PATH = null;
    private static String DB_NAME = "tribasa.db";
    private SQLiteDatabase sqLiteDatabase;
    private final Context ctx;


    public SQLiteDB(Context context) {
        super(context, DB_NAME, null, 1);
        this.ctx = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = ctx.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public Cursor Get_Data() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "ngoko_kata",
                "ngoko_suara",
                "kromo_kata",
                "kromo_suara",
                "indo_kata",
                "indo_suara"
        };
        String SORT = "ngoko_kata ASC";
        Cursor c = db.query("kamus",
                projection, null, null, null, null, SORT);

        return c;
    }

    public Cursor Get_DataKromo() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "ngoko_kata",
                "ngoko_suara",
                "kromo_kata",
                "kromo_suara",
                "indo_kata",
                "indo_suara"
        };
        String SORT = "kromo_kata ASC";
        Cursor c = db.query("kamus",
                projection, null, null, null, null, SORT);

        return c;
    }

    public Cursor Get_DataIndo() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "id",
                "ngoko_kata",
                "ngoko_suara",
                "kromo_kata",
                "kromo_suara",
                "indo_kata",
                "indo_suara"
        };
        String SORT = "indo_kata ASC";
        Cursor c = db.query("kamus",
                projection, null, null, null, null, SORT);

        return c;
    }

    public Cursor getWordMatches(String query, String[] columns) {
        String selection = "indo_kata MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables("kamus");

        Cursor cursor = builder.query(getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
}
