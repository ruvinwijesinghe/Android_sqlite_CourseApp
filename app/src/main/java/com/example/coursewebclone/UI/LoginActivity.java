package com.example.coursewebclone.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursewebclone.Database.DBHelper;
import com.example.coursewebclone.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String LOGGED_USER = "User";
    Button btnLogin,btnRegister;
    EditText Name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name=findViewById(R.id.Lo_Name);
        password=findViewById(R.id.Lo_Pass);
        btnRegister=findViewById(R.id.LoReg);
    }
    public void signIn(View view)
    {
        DBHelper dbHelper=new DBHelper(this);
        List usernames=dbHelper.readAllInfo("user");
        List passwords=dbHelper.readAllInfo("password");
        List types=dbHelper.readAllInfo("type");

        String name=Name.getText().toString();
        String pass=password.getText().toString();

        String type1 = "Teacher";
        String type2 = "Student";

        if(usernames.indexOf(name)>=0)
        {
            if(passwords.get(usernames.indexOf(name)).equals(password))
            {
                if(types.get(usernames.indexOf(name)).equals(type1)){
                    Intent intent = new Intent(this,TeacherActivity.class);
                    intent.putExtra(LOGGED_USER,usernames.get(usernames.indexOf(name)).toString());
                    Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this,Student.class);
                    intent.putExtra(LOGGED_USER,usernames.get(usernames.indexOf(name)).toString());
                    Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
            else
            {
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        }


    }
}