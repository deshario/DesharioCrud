package com.deshario.deshariocrud.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.deshario.deshariocrud.Models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deshario on 9/5/2017.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static String DB_NAME = "students_db";
    private static int DB_VERSION = 1;

    private String TABLE_NAME = "students";
    private String ID = "id";
    private String NAME = "name";
    private String EMAIL = "email";

    public DBHandler(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, user.getNickname());
        values.put(EMAIL, user.getEmail());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<User> getAllUsers(){
        List<User> contactList = new ArrayList<User>();
        String sql = "SELECT  * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setNickname(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                contactList.add(user);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public User getSingleUser(String nickname){
        User user = new User();
        String sql = "SELECT  * FROM " +TABLE_NAME+ " WHERE "+NAME+" = "+"'"+nickname+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setNickname(cursor.getString(1));
                user.setEmail(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return user;
    }


    public void deleteUser(User user){
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+ID+" = "+user.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }


    public void updateUser(int mainid, String u_nickname, String u_email) {
        String sql = "UPDATE "+TABLE_NAME+" SET "+NAME+" = '"+u_nickname+"', "+EMAIL+" = '"+u_email+"'"+" WHERE "+ID+" ='"+mainid+"'";
        System.out.println("sql :: "+sql);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

}
