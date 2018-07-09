package com.thezs.fabianzachs.tapattack.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fabianzachs on 09/07/18.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "storeItems.db";
    public static final String TABLE_STOREITEMS = "storeItems";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RESOURCEID = "resourceid";
    public static final String COLUMN_PRICEPOINTS = "pricepoints";
    public static final String COLUMN_PRICEMONEY = "pricemoney";
    public static final String COLUMN_UNLOCKED = "unlocked";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_STOREITEMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_RESOURCEID + " INTEGER, " +
                COLUMN_PRICEPOINTS+ " INTEGER, " +
                COLUMN_PRICEMONEY + " INTEGER, " +
                COLUMN_UNLOCKED + " BIT " +
                ");";

        sqLiteDatabase.execSQL(query);
    
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addStoreItem(StoreItem item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY,item.get_category());
        values.put(COLUMN_NAME,item.get_name());
        values.put(COLUMN_RESOURCEID, item.get_drawableId());
        values.put(COLUMN_PRICEPOINTS, item.get_pricePoints());
        values.put(COLUMN_PRICEMONEY, item.get_priceMoney());
        values.put(COLUMN_UNLOCKED, item.is_unlocked());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STOREITEMS, null, values);
        db.close();
    }

    public void deleteStoreItem(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STOREITEMS + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STOREITEMS + " WHERE 1";

        Cursor pointer = db.rawQuery(query, null);
        pointer.moveToFirst();

        while(!pointer.isAfterLast()) {
            if (pointer.getString(pointer.getColumnIndex(COLUMN_NAME)) != null) {
                dbString += pointer.getString(pointer.getColumnIndex(COLUMN_NAME));
                dbString += pointer.getString(pointer.getColumnIndex(COLUMN_UNLOCKED));
                dbString += "\n";
            }
            pointer.moveToNext();
        }
        db.close();
        return dbString;

    }

    public void removeAllRows() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ TABLE_STOREITEMS);
    }

}
