package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Dinesh on 11/22/2016.
 */
public class DataBase extends SQLiteOpenHelper{

    private static final String name = "test";
    private static final int version = 1;
    private static final String table_name = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_GENDER = "gender";
    private ArrayList<User> users = new ArrayList<User>();


    public DataBase(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + table_name + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_GENDER + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void add_user(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_NAME,user.getName());
        value.put(KEY_ADDRESS,user.getAddress());
        value.put(KEY_EMAIL,user.getEmail());
        value.put(KEY_GENDER,user.getGender());

        db.insert(table_name,null,value);
        db.close();
    }
    public User get_user(){

        return null;
    }
    public void delete_user(){

    }
    public ArrayList<User> get_users(){
        users.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + table_name,null);

        if (cursor.moveToFirst()) {
            do {
                User contact = new User();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setGender(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setAddress(cursor.getString(4));

                // Adding contact to list
                users.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

}
