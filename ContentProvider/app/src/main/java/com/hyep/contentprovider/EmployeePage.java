package com.hyep.contentprovider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hyep.contentprovider.Helpers.EDatabseHelper;

import java.util.ArrayList;

public class EmployeePage extends AppCompatActivity {

    ImageButton back;
    private ListView lvEmployee;
    private EDatabseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> employeeList;
    private ArrayList<Integer> employeeIDs;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.btnBackToMain);
        lvEmployee = findViewById(R.id.lvEmployee);
        btnAdd = findViewById(R.id.btnAddEmployee);
        dbHelper = new EDatabseHelper(this);
        employeeList = new ArrayList<>();
        employeeIDs = new ArrayList<>();


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        lvEmployee.setAdapter(adapter);

        loadEmployee();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EmployeePage.this, AddEmployee.class);
                startActivity(myIntent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int employeeID = employeeIDs.get(i);
                Intent myIntent2 = new Intent(EmployeePage.this, DetailEmployee.class);
                myIntent2.putExtra("EMPLOYEE_ID", employeeID);
                startActivity(myIntent2);
            }
        });
    }
    public void loadEmployee() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(EDatabseHelper.TABLE_EMPLOYEES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_EMAIL));
                String pos = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_POS));
                String img = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_IMAGE));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_PHONE));
                String depID = cursor.getString(cursor.getColumnIndexOrThrow(EDatabseHelper.COLUMN_ID_DEPARTMENT));
                employeeList.add(name + " - " + email + " - " + pos + " - " + img + " - " + phone + " - " + depID);
                employeeIDs.add(id);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }
}