package com.example.quanlysv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlysv.adapter.adapterstudent;
import com.example.quanlysv.database.database;
import com.example.quanlysv.model.Student;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    database database;

   adapterstudent adapterstudent;

    int id_subject = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarStudent);
        listViewstudent =  findViewById(R.id.listviewstudent);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject",0);

        // toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();

        Cursor cursor = database.getDatabaseStudent(id_subject);
        while (cursor.moveToNext()){
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            ArrayListStudent.add(new Student(id, name,sex, code,birthday,id_sub));

        }

        adapterstudent = new adapterstudent(ActivityStudent.this,ArrayListStudent);

        // hiển thị listview
        listViewstudent.setAdapter(adapterstudent);
        cursor.moveToFirst();
        cursor.close();
    }

    // thêm icon add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            //chuyển qua màn hình add  student
            case R.id.menuaddstudent:
                Intent intent = new Intent(ActivityStudent.this,ActivityAddStudent.class);
                intent.putExtra("id subject",id_subject);
                startActivity(intent);
                break;

                // Còn lại nút Back chuyển qua subject activity
            default:
                Intent intent1 = new Intent(ActivityStudent.this,ActivitySubject.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    // Back trên đt ra subject activity
    @Override
    public void onBackPressed() {
        counter++;
        if(counter>=1){
            Intent intent = new Intent(this,ActivitySubject.class);
            startActivity(intent);
            finish();
        }

    }

    public void information (final int pos) {
        Cursor cursor = database.getDatabaseStudent(id_subject);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

                if(id == pos){
                    Intent intent  = new Intent(ActivityStudent.this,ActivityinformationStudent.class);

                    intent.putExtra("id",pos);

                    String name = cursor.getString(1);
                    String  sex = cursor.getString(2);
                    String code = cursor.getString (3);
                    String birthday = cursor.getString(4);
                    int id_subject= cursor.getInt(5);

                    intent.putExtra("name",name);
                    intent.putExtra("sex",sex);
                    intent.putExtra("code",code);
                    intent.putExtra("birthday",birthday);
                    intent.putExtra("id_subject",id_subject);

                    startActivity(intent);
                }
        }
        cursor.close();
    }

    //update
    public void update(final int id_student){
        Cursor cursor = database.getDatabaseStudent(id_subject);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == id_student){
                Intent intent  = new Intent(ActivityStudent.this,ActivityUpdateStudent.class);

                intent.putExtra("id",id_student);

                String name = cursor.getString(1);
                String  sex = cursor.getString(2);
                String code = cursor.getString (3);
                String birthday = cursor.getString(4);
                int id_subject= cursor.getInt(5);

                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birthday",birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }

        }
        cursor.close();
    }

    public void delete(final int id_student){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletestudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYeDeleteStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // xóa student trong database
                database.DeleteStudent(id_student);

                // mở lại activity student
                Intent intent = new Intent(ActivityStudent.this,ActivityStudent.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);

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
}