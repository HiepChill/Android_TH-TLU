package com.hyep.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtHeight, edtWeight, edtBMI, edtResult;
    Button btnBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtResult = (EditText) findViewById(R.id.edtName);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtBMI = (EditText) findViewById(R.id.edtBMI);
        edtResult = (EditText) findViewById(R.id.edtResult);
        btnBMI = (Button) findViewById(R.id.btnBMI);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result(Double.parseDouble(edtHeight.getText().toString()), Double.parseDouble(edtWeight.getText().toString()));
            }
        });

    }

    public void result(double h, double w) {
        double bmi = w / (h * h);
        edtBMI.setText(String.valueOf(bmi));
        if (bmi < 18) {
            edtResult.setText("người gầy");
        } else if (bmi < 24.9) {
            edtResult.setText("người bình thường");
        } else if (bmi < 29.9) {
            edtResult.setText("người béo phì độ I");
        } else if (bmi < 34.9) {
            edtResult.setText("người béo phì độ II");
        } else {
            edtResult.setText("người béo phì độ III");
        }
    }
}

