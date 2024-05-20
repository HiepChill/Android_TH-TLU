package com.hyep.bth2bosung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtC, edtF;
    Button btnCF, btnFC, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtC = (EditText) findViewById(R.id.edtC);
        edtF = (EditText) findViewById(R.id.edtF);
        btnCF = (Button) findViewById(R.id.btn_convertToF);
        btnFC = (Button) findViewById(R.id.btn_convertToC);
        btnClear = (Button) findViewById(R.id.btn_Clear);
    }

    public void handleFC(View view) {
        if (!edtF.getText().toString().isEmpty()) {
            double temF = Double.parseDouble(edtF.getText().toString());
            double temC = (temF - 32.0) * (5 / 9);
            edtC.setText(String.valueOf(temC).toString());

            Toast.makeText(getApplicationContext(), String.valueOf(temC).toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void handleCF(View view) {
        if (!edtC.getText().toString().isEmpty()) {
            double temC = Double.parseDouble(edtC.getText().toString());
            double temF = temC * (9 / 5) + 32;
            edtF.setText(String.valueOf(temF));
        }
    }

    public void handleClear(View view) {
        edtC.setText("");
        edtF.setText("");
    }
}