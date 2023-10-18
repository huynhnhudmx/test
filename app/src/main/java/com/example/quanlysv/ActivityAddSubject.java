package com.example.quanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlysv.database.database;
import com.example.quanlysv.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    Button buttonAddSubject;
    EditText edtSubjectTitle,edtSubjectCredit, edtSubjectTime,edtSubjectPlace;
    com.example.quanlysv.database.database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonAddSubject = findViewById(R.id.btnAddSb);
        edtSubjectTitle = findViewById(R.id.Editsbtt);
        edtSubjectCredit = findViewById(R.id.EditCredit);
        edtSubjectTime = findViewById(R.id.EditsbTime);
        edtSubjectPlace = findViewById(R.id.EditsbPlace);


        database = new database(this);
        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });

    }
    private void DialogAdd(){

        // tạo đối tượng cửa sổ
        Dialog dialog = new Dialog(this);

        // nạp  layout vào dialog
        dialog.setContentView(R.layout.dialogadd);

        // tắt clieck ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subjecttitle = edtSubjectTitle.getText().toString().trim();
                String credit =(edtSubjectCredit.getText().toString().trim());
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectPlace.getText().toString().trim();


                // nếu dữ liệu chưa nhập đầy đủ
                if(subjecttitle.equals("") || credit.equals("")|| time.equals("") || place.equals("")){
                    Toast.makeText(ActivityAddSubject.this,"Did not enter enough ìnormation",Toast.LENGTH_SHORT).show();
                }
                else{

                    // gán cho đối tượng subject giá trị được nhập vào
                    Subject subject = CreatSubject();


                    // add trong database
                    database.AddSubjects(subject);

                    // add thành công thì chuyển qua giao diện subject
                    Intent intent  = new Intent (ActivityAddSubject.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddSubject.this,"more success",Toast.LENGTH_SHORT).show();

                }
            }
        });

        // nếu không add nữa
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        // show dialog
        dialog.show();

    }

    // Create subject
    private Subject CreatSubject(){
        String subjecttitle = edtSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectPlace.getText().toString().trim();

        Subject subject = new Subject (subjecttitle,credit,time,place);
        return subject;
    }
}