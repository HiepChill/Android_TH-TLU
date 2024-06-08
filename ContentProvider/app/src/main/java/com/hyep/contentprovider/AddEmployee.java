package com.hyep.contentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hyep.contentprovider.Helpers.EDatabseHelper;

public class AddEmployee extends AppCompatActivity {
    EditText edtEName, edtEEmail, edtEPos, edtEImage, edtEPhone, edtEDepID;
    ImageButton btnAddEmployee, btnBack;
    EDatabseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEName = findViewById(R.id.edtEName);
        edtEEmail = findViewById(R.id.edtEEmail);
        edtEPos = findViewById(R.id.edtEPos);
        edtEPhone = findViewById(R.id.edtEPhone);
        edtEDepID = findViewById(R.id.edtEDepartID);
        edtEImage = findViewById(R.id.edtEImage);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new EDatabseHelper(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtEName.getText().toString();
                String email = edtEEmail.getText().toString();
                String pos = edtEPos.getText().toString();
                String img = edtEImage.getText().toString();
                String phone = edtEPhone.getText().toString();
                String depID = edtEDepID.getText().toString();


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(EDatabseHelper.COLUMN_NAME, name);
                values.put(EDatabseHelper.COLUMN_EMAIL, email);
                values.put(EDatabseHelper.COLUMN_POS, pos);
                values.put(EDatabseHelper.COLUMN_IMAGE, img);
                values.put(EDatabseHelper.COLUMN_PHONE, phone);
                values.put(EDatabseHelper.COLUMN_ID_DEPARTMENT, depID);

                db.insert(EDatabseHelper.TABLE_EMPLOYEES, null, values);
                db.close();

                Intent myIntent = new Intent(AddEmployee.this, EmployeePage.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);
                finish();
            }
        });

    }
}