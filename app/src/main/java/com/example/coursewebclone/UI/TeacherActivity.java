package com.example.coursewebclone.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursewebclone.Database.DBHelper;
import com.example.coursewebclone.Model.Message;
import com.example.coursewebclone.R;

public class TeacherActivity extends AppCompatActivity {
    DBHelper db;
    EditText name,Messagee,Subject;
    Button SenMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        db=new DBHelper(this);
        Intent intent = getIntent();
        String user = intent.getStringExtra(LoginActivity.LOGGED_USER);
        name.findViewById(R.id.MeName);
        name.setText(user);
        SenMessage.findViewById(R.id.MeMessage);
        Subject=findViewById(R.id.MeSubject);
        Messagee=findViewById(R.id.MeMessage);


        SenMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message m=new Message();

                db.SendMessage(m);


            }
        });



    }

}