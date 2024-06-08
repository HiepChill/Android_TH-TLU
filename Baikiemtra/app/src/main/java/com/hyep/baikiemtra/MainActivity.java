package com.hyep.baikiemtra;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hyep.baikiemtra.Adapters.EmployeeAdapter;
import com.hyep.baikiemtra.Helpers.EDatabseHelper;
import com.hyep.baikiemtra.Listeners.OnERowClick;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EDatabseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> employeeList;
    private ArrayList<Integer> employeeIDs;
    private FloatingActionButton btnAdd;
    private RecyclerView rvEmployee;
    private EmployeeAdapter employeeAdapter;

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

        btnAdd = findViewById(R.id.btnAddEmployee);
        dbHelper = new EDatabseHelper(this);
        employeeList = new ArrayList<>();
        employeeIDs = new ArrayList<>();
        rvEmployee = findViewById(R.id.rvEmployee);

        rvEmployee.setHasFixedSize(true);


//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
//        lvEmployee.setAdapter(adapter);

        loadData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AddEmployee.class);
                startActivity(myIntent);
            }
        });

//        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int employeeID = employeeIDs.get(i);
//                Intent myIntent2 = new Intent(MainActivity.this, DetailEmployee.class);
//                myIntent2.putExtra("EMPLOYEE_ID", employeeID);
//                startActivity(myIntent2);
//            }
//        });
    }
    public void loadData() {
        employeeAdapter = new EmployeeAdapter(this, dbHelper.getAllData());
        rvEmployee.setAdapter(employeeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

}