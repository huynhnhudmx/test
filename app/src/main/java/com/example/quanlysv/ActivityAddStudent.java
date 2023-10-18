package com.example.quanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.quanlysv.database.database;
import com.example.quanlysv.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    Button buttonAddStudent;
    EditText edtTextStudentName, edtTextStudentCode, edtTextDataofbirth;
    RadioButton radiobuttonMale, radioButtonFemale;
   database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddStudent = findViewById(R.id.btnAddStudent);
        edtTextDataofbirth = findViewById(R.id.EditStudentBirthday);
        edtTextStudentCode = findViewById(R.id.EditStudentCode);
        edtTextStudentName = findViewById(R.id.EditTextStudentName);

        radioButtonFemale = findViewById(R.id.radiobuttonFeMale);
        radiobuttonMale = findViewById(R.id.radiobuttonMale);


        // lấy id subject
        Intent intent = getIntent();
        int id_subject= intent.getIntExtra("id_subject",0);


        database = new database(this);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(id_subject);
            }
        });

    }
    // dialog add
    private void DialogAdd(int id_subject){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);


        Button btnYes = dialog.findViewById(R.id.buttonYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTextStudentName.getText().toString().trim();
                String code = edtTextStudentCode.getText().toString().trim();
                String birthday = edtTextDataofbirth.getText().toString().trim();
                String sex = "";

                // kiểm tra radiobutton true tại đâu thì lấy giá trị nam hay nữ gần vào sex
                if(radiobuttonMale.isChecked()){
                    sex = "Male";
                }

                else if(radioButtonFemale.isChecked()){
                    sex = "Female";
                }

                if(name.equals("") || code.equals("") || birthday.equals("") || sex.equals("")) {
                    Toast.makeText(ActivityAddStudent.this, "Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {
                    Student student = CreteStudent(id_subject);

                    database.AddStudent(student);

                    Intent intent = new Intent(ActivityAddStudent.this,ActivityStudent.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);


                    Toast.makeText(ActivityAddStudent.this,"more success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();

    }
    private Student CreteStudent(int id_subject){
        String name = edtTextStudentName.getText().toString().trim();
        String code = edtTextStudentCode.getText().toString().trim();
        String birthday = edtTextDataofbirth.getText().toString().trim();
        String sex = "";

        // kiểm tra radiobutton true tại đâu thì lấy giá trị nam hay nữ gần vào sex
        if(radiobuttonMale.isChecked()){
            sex = "Male";
        }

        else if(radioButtonFemale.isChecked()){
            sex = "Female";
        }

        Student student = new Student(name,sex,code,birthday,id_subject);
        return   student;

    }
}