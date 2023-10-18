package com.example.quanlysv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quanlysv.ActivityStudent;
import com.example.quanlysv.R;
import com.example.quanlysv.model.Student;

import java.util.ArrayList;

public class adapterstudent extends BaseAdapter {

    private ActivityStudent context;
    private ArrayList<Student>ArrayListStudent;


    public adapterstudent(ActivityStudent context, ArrayList<Student> arrayListStudent) {
        this.context = context;
        ArrayListStudent = arrayListStudent;
    }

    @Override
    public int getCount() {

        return ArrayListStudent.size();
    }

    @Override
    public Object getItem(int i) {

        return ArrayListStudent.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudent,null);

        TextView txtName = view.findViewById(R.id.tvSdname);
        TextView txtCode = view.findViewById(R.id.tvStudentCode);

        ImageButton imgbtnDelete = view.findViewById(R.id.studentdelete);
        ImageButton imgbtnUpdate = view.findViewById(R.id.studentupdate);
        ImageButton imginformation = view.findViewById(R.id.studentinformation);

        Student student = ArrayListStudent.get(i);

        txtName.setText(student.getStudent_name());
        txtCode.setText(student.getStudent_code());

        int id = student.getId_student();


        //click Delete
        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.delete(id);

            }
        });

        //click update
        imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.update(id);
            }
        });

        //click information
        imginformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.information(id);
            }
        });


        return view;
    }
}
