package com.hyep.bth3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv1);
        lv = findViewById(R.id.lv1);

        final String arr[] = {"Iphone 7", "SamSung Galaxy S7",
                "Nokia Lumia 730", "Sony Xperia XZ","HTC One E9"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_item, arr);

        lv.setAdapter(adapter);
    }
}