package com.example.quanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySubjectinformation extends AppCompatActivity {

    TextView edtTitle, edtCredit, edtTime,edtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectinformation);

        edtCredit = findViewById(R.id.txSubjectCredit);
        edtPlace = findViewById(R.id.txSubjectPlace);

        edtTime = findViewById(R.id.txSubjectPlace);
        edtTitle = findViewById(R.id.txSubjectTitle);


        // lấy dữ liệu
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String  place = intent.getStringExtra("place");


        // Gán giá trị lên
        edtTitle.setText(title);
        edtTime.setText(time);
        edtPlace.setText(place);
        edtCredit.setText(credit+"");
    }
}