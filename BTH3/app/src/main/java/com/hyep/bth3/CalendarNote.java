package com.hyep.bth3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarNote extends AppCompatActivity {

    EditText edtWork, edtHour, edtMinute;
    TextView tvDate;
    ListView lv;
    Button btnAdd;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar_note);

        edtWork = findViewById(R.id.edtWork);
        edtHour = findViewById(R.id.edtHour);
        edtMinute = findViewById(R.id.edtMinute);
        tvDate = findViewById(R.id.tvDate);
        lv = findViewById(R.id.lv);
        btnAdd = findViewById(R.id.btnAdd);

        Date currentDate = Calendar.getInstance().getTime();
        String dateFormat = "dd/MM/yyyy";
        tvDate.setText("Hôm nay: " + DateFormat.format(dateFormat, currentDate.getTime()));

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.single_item, arrayList);
        lv.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtWork.getText().toString().equals("")||
                        edtHour.getText().toString().equals("")
                        ||edtMinute.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalendarNote.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
                else {
                    String str = edtWork.getText().toString() + " - " +
                            edtHour.getText().toString() + ":" + edtMinute.getText().toString();
                    arrayList.add(str);

                    adapter.notifyDataSetChanged();
                    edtHour.setText("");
                    edtMinute.setText("");
                    edtWork.setText("");
                }
            }
        });

    }
}