package com.hyep.contentprovider.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EDatabseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_EMPLOYEES = "employee";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_POS = "position";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ID_DEPARTMENT = "idDepartment";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_POS + " TEXT," +
                    COLUMN_IMAGE + " TEXT," +
                    COLUMN_PHONE + " TEXT," +
                    COLUMN_ID_DEPARTMENT + " TEXT," +
                    "UNIQUE (" + COLUMN_ID + ")" + ");";

    public EDatabseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(sqLiteDatabase);
    }
}
