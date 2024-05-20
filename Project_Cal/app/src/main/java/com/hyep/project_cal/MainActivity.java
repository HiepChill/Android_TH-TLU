package com.hyep.project_cal;

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

    EditText edt1, edt2, edt3;
    Button btncong, btntru, btnnhan, btnchia, btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //đặt giao diện của một Activity bằng cách sử dụng một tệp nguồn XML
        setContentView(R.layout.activity_main);

        //Tham chiếu biến tới phần tử giao diện
        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtc);
        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        btnchia = findViewById(R.id.btnchia);
        btnnhan = findViewById(R.id.btnnhan);
        btnCheck = findViewById(R.id.buttonCheck);
//        btncong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //tạo biến và gán giá trị cho biến bằng cách ép kiểu
//                int a = Integer.parseInt("0"+edt1.getText());
//                int b = Integer.parseInt("0"+edt2.getText());
//                // TODO Auto-generated method stub
//                edt3.setText("a + b =" +(a+b));
//            }
//        });

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                // TODO Auto-generated method stub
                edt3.setText("a - b =" +(a-b));

            }
        });
        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                // TODO Auto-generated method stub
                edt3.setText("a * b =" +(a*b));
            }
        });
        btnchia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                //xử lý logic
                if (b == 0)
                {
                    edt3.setText("B phai khac 0");
                }
                else
                {
                    edt3.setText("a / b =" +(a/b));
                }
            }
        });
    }
}