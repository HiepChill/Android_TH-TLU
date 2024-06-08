package com.hyep.contentprovider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hyep.contentprovider.Helpers.EDatabseHelper;

public class DetailEmployee extends AppCompatActivity {
    EditText edtName, edtEmail, edtPos, edtImg, edtPhone, edtDepID;
    EDatabseHelper dbHelper;
    int employeeID;
    ImageButton btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edtEName);
        edtEmail = findViewById(R.id.edtEEmail);
        edtPos = findViewById(R.id.edtEPos);
        edtPhone = findViewById(R.id.edtEPhone);
        edtDepID = findViewById(R.id.edtEDepartID);
        //btnEdit = findViewById(R.id.btnUpdateEmployee);
        btnDelete = findViewById(R.id.btnDeleteEmployee);
        dbHelper = new EDatabseHelper(this);
        Intent intent = getIntent();
        employeeID = intent.getIntExtra("EMPLOYEE_ID", -1);

        loadE();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DetailEmployee.this).setTitle("Delete Contact").setMessage("Are you sure you want to delete this person?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete(EDatabseHelper.TABLE_EMPLOYEES, EDatabseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(employeeID)});
                        db.close();
                        Intent myIntent = new Intent(DetailEmployee.this, EmployeePage.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(myIntent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();
            }
        });

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = edtName.getText().toString();
//                String email = edtEmail.getText().toString();
//                String pos = edtPos.getText().toString();
//                String img = edtImg.getText().toString();
//                String phone = edtPhone.getText().toString();
//                String depID = edtDepID.getText().toString();
//
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put(EDatabseHelper.COLUMN_NAME, name);
//                values.put(EDatabseHelper.COLUMN_EMAIL, email);
//                values.put(EDatabseHelper.COLUMN_POS, pos);
//                values.put(EDatabseHelper.COLUMN_IMAGE, img);
//                values.put(EDatabseHelper.COLUMN_PHONE, phone);
//                values.put(EDatabseHelper.COLUMN_ID_DEPARTMENT, depID);
//
//                db.update(EDatabseHelper.TABLE_EMPLOYEES, values, EDatabseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(employeeID)});
//                db.close();
//                Intent myIntent = new Intent(DetailEmployee.this, EmployeePage.class);
//                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(myIntent);
//                finish();
//            }
//        });

    }

    private void loadE() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(EDatabseHelper.TABLE_EMPLOYEES, null, EDatabseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(employeeID)}, null, null, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_EMAIL));
            String pos = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_POS));
            String img = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_IMAGE));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_PHONE));
            String depID = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_ID_DEPARTMENT));

            edtName.setText(name);
            edtEmail.setText(email);
            edtPos.setText(pos);
            edtPhone.setText(phone);
            edtDepID.setText(depID);
        }

        cursor.close();
        db.close();
    }
}