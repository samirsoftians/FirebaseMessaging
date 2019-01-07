package com.example.twtech.firebasemessaging.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.util.HashMap;

public class SqliteDatabase extends SQLiteOpenHelper {

    String databaseName = Environment.getExternalStorageDirectory().getPath() + "/sell.db";


    public static final String DB_NAME = "sell";
    public static final Integer DB_VERSION = 3;
    Context context;

    public static final String TABLE_NAME = "tab_login";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_PASSWORD = "password";
    public static final String UID = "userid";
    public static final String COL_IMEI = "imei";

    private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_USER_NAME+" VARCHAR(255) ,"+ COL_PASSWORD+" VARCHAR(225),"+  COL_IMEI+" VARCHAR(225));";
    private static final String DROP_TABLE2 ="DROP TABLE IF EXISTS "+TABLE_NAME;


    private HashMap hp;

    public SqliteDatabase(Context context) {

        super(context, "/mnt/sdcard/sell5.db", null, DB_VERSION);

        Log.e("IN THE CONSTRUCTOR","Constructor called");

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.e("ONCREATE","TABLE IS CREATED ");

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE2);
        } catch (Exception e) {
           // Message.message(context,""+e);
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE2);
        onCreate(sqLiteDatabase);
    }


    /*public boolean insertContact (String username, String password, String imei) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_NAME, username);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_IMEI, imei);

        db.insert(TABLE_NAME, null, contentValues);
        Log.e("DataInserted", "True");
        return true;
    }*/

}
