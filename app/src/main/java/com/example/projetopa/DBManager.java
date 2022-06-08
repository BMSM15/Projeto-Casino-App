package com.example.projetopa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {

    private static final String dbname="Casino.db";
    public DBManager( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table tb1_jogador (id integer primary key autoincrement, name text, dinheiro integer)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb1_jogador");
        onCreate(db);
    }

    public String addRecord(String p1, Integer p2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",p1);
        cv.put("dinheiro",p2);

        long res = db.insert("tb1_jogador", null, cv);

        if (res==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }
}
