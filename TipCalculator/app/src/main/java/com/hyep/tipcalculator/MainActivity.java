package com.hyep.tipcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtBill;
    TextView percent, tipMoney, totalMoney;
    Button buttonPlus, buttonMinus;
    int tipPercent = 15;
    Double total = 0.0;
    Double tip = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtBill = findViewById(R.id.txtBill);
        percent = findViewById(R.id.percent);
        tipMoney = findViewById(R.id.tipMoney);
        totalMoney = findViewById(R.id.totalMoney);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);


        //increase tip percent button
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipPercent++;
                percent.setText(tipPercent + "%");
                update();
            }
        });

        //decrease tip percent button
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipPercent > 0) {
                    tipPercent--;
                    percent.setText(tipPercent + "%");
                    update();
                }
            }
        });


        txtBill.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();
            }
        });
    }
    public void update() {
        if (!txtBill.getText().toString().isEmpty()) {
            Double billAmount = Double.parseDouble(txtBill.getText().toString());
            tip = billAmount * tipPercent / 100;
            total = billAmount + tip;
            tipMoney.setText("$" + tip);
            totalMoney.setText("$" + total);
        }
    }
}