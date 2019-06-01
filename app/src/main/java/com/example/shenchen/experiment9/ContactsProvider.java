package com.example.shenchen.experiment9;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ContactsProvider extends ContentProvider {

    public static final int CONTACTS_DIR = 0;
    public static final int CONTACTS_ITEM = 1;
    public static final String AUTHORITY = "com.example.shenchen.experiment9.provider";
    private static UriMatcher uriMatcher;
    private ContactsbaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contact", CONTACTS_DIR);
        uriMatcher.addURI(AUTHORITY, "contact/#", CONTACTS_ITEM);
    }

    public ContactsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.shenchen.experiment9.provider.contacts";
            case CONTACTS_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.shenchen.experiment9.provider.contacts";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        dbHelper = new ContactsbaseHelper(getContext(), "Contacts.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                cursor = db.query("contacts", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CONTACTS_ITEM:
                String phoneId = uri.getPathSegments().get(1);
                cursor = db.query("contacts", projection, "phone=?", new String[]{ phoneId }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
