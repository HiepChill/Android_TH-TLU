package com.hyep.qlsv;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtClassID, edtClassName, edtNumberStudent;
    Button btnInsert, btnUpdate, btnDelete, btnQuery;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtClassID = findViewById(R.id.edtClassId);
        edtClassName = findViewById(R.id.edtClassName);
        edtNumberStudent =findViewById(R.id.edtNumberStudent);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnQuery = findViewById(R.id.btnQuery);
        btnUpdate = findViewById(R.id.btnUpdate);
        listView = findViewById(R.id.lvList);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        database = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);
        try {
            String sql = "CREATE TABLE tblop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            database.execSQL(sql);
        } catch (Exception e) {
            Toast.makeText(this, "Table đã tồn tại!", Toast.LENGTH_SHORT).show();
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String malop = edtClassID.getText().toString();
                String tenlop = edtClassName.getText().toString();
                int siso = Integer.parseInt(edtNumberStudent.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put("malop", malop);
                contentValues.put("tenlop", tenlop);
                contentValues.put("siso", siso);
                String msg = "";
                if (database.insert("tblop", null, contentValues) == -1) {
                    msg = "Fail to INSERT!";
                }
                else {
                    msg = "INSERT Success";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                refreshDB();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String malop = edtClassID.getText().toString();
                int siso = Integer.parseInt(edtNumberStudent.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put("siso", siso);
                String msg = "";
                if (database.update("tblop", contentValues, "malop = ?", new String[] {malop}) == 0) {
                    msg = "NO RECORD UPDATE";
                }
                else {
                    msg = "UPDATE Success";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                refreshDB();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String malop = edtClassID.getText().toString();
                String msg = "";
                if (database.delete("tblop", "malop = ?", new String[] {malop}) == 0) {
                    msg = "NO RECORD DELETE";
                }
                else {
                    msg = "DELETE Success";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                refreshDB();
            }
        });

    }

    private void refreshDB() {
        list.clear();
        Cursor cursor = database.query("tblop", null, null, null, null, null, null);
        cursor.moveToNext();
        String data = "";
        while (cursor.isAfterLast() == false) {
            data = cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2);
            cursor.moveToNext();
            list.add(data);
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

}