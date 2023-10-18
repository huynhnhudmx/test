package com.example.quanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSubject, btnAuthor, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAuthor = findViewById(R.id.buttonAuthor);
        btnSubject = findViewById(R.id.buttonSubject);
        btnExit = findViewById(R.id.buttonExit);


        //Click tác giả
       btnAuthor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DialogAuthor();
           }
       });

       // Click subject
       btnSubject.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // chuyển qua Activity Subject
               Intent intent = new Intent (MainActivity.this, ActivitySubject.class);
               startActivity(intent);
           }
       });


       //Click exit app

       btnExit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DialogExit();
           }
       });

        }

        // hàm hiển thị cửa sổ dialog exit
    private void DialogExit() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogexit);

        // Tắt click ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

                // Thoát
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
            }
        });

        // nếu Click No thì đóng cửa sổ

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        //show dialog
        dialog.show();
    }

    // hiển thị thông tin tác giả
    private void DialogAuthor() {
        // Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog (this);

        // nạp layout vào dialog
        dialog.setContentView(R.layout.dialoginfomation);
        dialog.show();
    }

    // run.........
}