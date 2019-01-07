package com.example.twtech.firebasemessaging.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.example.twtech.firebasemessaging.sqlite.SqliteDatabase.COL_IMEI;
import static com.example.twtech.firebasemessaging.sqlite.SqliteDatabase.COL_PASSWORD;
import static com.example.twtech.firebasemessaging.sqlite.SqliteDatabase.COL_USER_NAME;
import static com.example.twtech.firebasemessaging.sqlite.SqliteDatabase.TABLE_NAME;


public class SqliteOperations {

    SqliteDatabase dbHelper;
    Context context;


    public SqliteOperations(Context context)
    {
        dbHelper=new SqliteDatabase(context);
    }


    public boolean insertContact (String username, String password, String imei) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_NAME, username);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_IMEI, imei);

        db.insert(TABLE_NAME, null, contentValues);
        Log.e("DataInserted", "True");
        return true;
    }


    public int updateName(String oldName , String newName)
    {

        int count=1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_NAME,newName);
        String[] whereArgs= {oldName};
        String updat="UPDATE tab_login SET imei='Hi samir' WHERE user_name='Samir' and imei ='samir'";
        db.execSQL(updat);

        //int count =db.update(dbHelper.TABLE_NAME,contentValues, dbHelper.COL_USER_NAME+" = ?",whereArgs );
        return count;
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(TABLE_NAME , COL_USER_NAME+" = ?",whereArgs);
        return  count;
    }

    public String getData()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.UID, COL_USER_NAME, COL_PASSWORD, COL_IMEI};
        Cursor cursor =db.query(TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(dbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(COL_USER_NAME));
            String  password =cursor.getString(cursor.getColumnIndex(COL_PASSWORD));
            String  imei =cursor.getString(cursor.getColumnIndex(COL_IMEI));
            buffer.append(cid+ "   " + name + "   " + password +" "+ imei +" \n");
        }
        return buffer.toString();
    }

}
