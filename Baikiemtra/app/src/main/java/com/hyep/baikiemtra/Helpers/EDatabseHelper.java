package com.hyep.baikiemtra.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hyep.baikiemtra.Models.Employee;

import java.util.ArrayList;

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

    public long insertEmployee(String image,String name,String phone,String email, String position, String idDepartment){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        // id will save automatically as we write query
        contentValues.put(COLUMN_IMAGE, image);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_POS, position);
        contentValues.put(COLUMN_ID_DEPARTMENT, idDepartment);

        //insert data in row, It will return id of record
        long id = db.insert(TABLE_EMPLOYEES,null,contentValues);

        // close db
        db.close();

        //return id
        return id;

    }

    // Update Function to update data in database
    public void updateEmployee(String id,String image,String name,String phone,String email, String position, String idDepartment){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_IMAGE,image);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_PHONE,phone);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_POS,position);
        contentValues.put(COLUMN_ID_DEPARTMENT,idDepartment);


        //update data in row, It will return id of record
        db.update(TABLE_EMPLOYEES,contentValues,COLUMN_ID+" =? ",new String[]{id} );

        // close db
        db.close();

    }

    // delete data by id
    public void deleteEmployee(String id){
        //get writable database
        SQLiteDatabase db =  getWritableDatabase();

        //delete query
        db.delete(TABLE_EMPLOYEES,"WHERE"+" =? ",new String[]{id});

        db.close();
    }

    public ArrayList<Employee> getAllData() {
        ArrayList<Employee> employeeList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEES;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                Employee employee = new Employee(
                        // only id is integer type
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POS)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID_DEPARTMENT))
                );
                employeeList.add(employee);
            }while (cursor.moveToNext());
        }
        db.close();
        return employeeList;
    }

}
