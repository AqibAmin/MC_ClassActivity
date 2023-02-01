package com.example.classactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "namazrecord.db";
    private static final String TABLE_NAME = "namaz";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NAME = "name";
    private static final String Rakat_Count = "rakat_count";
    private static final String With_Jamat = "with_jamat";
    private static final String COLUMN_Date = "date";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + Rakat_Count + " TEXT,"
                + COLUMN_Date + " TEXT,"
                + With_Jamat + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(Namaz namaz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, namaz.getNamazType());
        values.put(COLUMN_NAME, namaz.getNamazName());
        values.put(Rakat_Count, namaz.getRakatCount());
        values.put(With_Jamat, namaz.isWithJamat());
        values.put(COLUMN_Date, namaz.getDate());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Namaz> selectAllRecords() {
        List<Namaz> namaz = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                namaz.add(new Namaz(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3), (cursor.getInt(4) == 1 ? true : false),
                        cursor.getString(5)));
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return namaz;
    }
}
