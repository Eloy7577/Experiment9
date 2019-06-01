package com.example.shenchen.experiment9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shenchen on 2019/5/27.
 */

public class ContactsbaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACTS = "create table contacts("
            + "phone text primary key,"
            + "name text,"
            + "sexual text)";

    private Context mContext;

    public ContactsbaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
