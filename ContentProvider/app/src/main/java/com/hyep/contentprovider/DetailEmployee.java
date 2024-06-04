package com.hyep.contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hyep.contentprovider.Helpers.EDatabseHelper;

public class DetailEmployee extends AppCompatActivity {
    EditText edtName, edtEmail, edtPos, edtImg, edtPhone, edtDepID;
    EDatabseHelper dbHelper;
    int employeeID;
    Button btnEdit, btnDelete;

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
        btnEdit = findViewById(R.id.btnUpdateEmployee);
        btnDelete = findViewById(R.id.btnDeleteEmployee);
        dbHelper = new EDatabseHelper(this);
        Intent intent = getIntent();
        employeeID = intent.getIntExtra("BOOK_ID", -1);

        loadE();

    }

    private void loadE() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(EDatabseHelper.TABLE_EMPLOYEES, null, EDatabseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(employeeID)}, null, null, null);

        if (cursor.moveToFirst()) {
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