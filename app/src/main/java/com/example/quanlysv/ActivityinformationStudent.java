package com.example.quanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityinformationStudent extends AppCompatActivity {


    TextView txtName, txtSex, txtCode, txtBirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityinformation_student);

        txtBirthday = findViewById(R.id.txtStudentName);
        txtCode = findViewById(R.id.txStudentCode);
        txtSex = findViewById(R.id.txtStudentSex);
        txtBirthday = findViewById(R.id.txtStudentdatebirth);


        // Nhận dữ liệu
        Intent intent = getIntent();


        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");


        // Gắn lên TextView tương ứng
        txtName.setText(name);
        txtSex.setText(sex);
        txtCode.setText(code);
        txtBirthday.setText(birthday);
    }
}