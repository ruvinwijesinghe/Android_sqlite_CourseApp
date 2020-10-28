package com.example.coursewebclone.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.coursewebclone.Database.DBHelper;
import com.example.coursewebclone.Database.DatabaseMaster;
import com.example.coursewebclone.Model.User;
import com.example.coursewebclone.R;

public class RegisterActivity extends AppCompatActivity {
    DBHelper db;
    EditText name,password;
    RadioButton teacher,student;
    Button Registerbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DBHelper(this);

        Registerbtn=findViewById(R.id.RegisterButtonR);
        name=findViewById(R.id.NameReg);
        password=findViewById(R.id.PassReg);
        teacher=findViewById(R.id.Teacher);
        student=findViewById(R.id.Student);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u=new User();
                u.setUser(name.getText().toString());
                u.setPassword(password.getText().toString());
                if (teacher.isChecked()){
                    u.setUserCategory("teacher");
                }
                else{
                    u.setUserCategory("Student");
                }
                if(db.RegisterUser(u)>0){
                    Toast.makeText(RegisterActivity.this, "You Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*// Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());*/
    }
}