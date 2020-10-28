package com.example.coursewebclone.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.coursewebclone.Model.Message;
import com.example.coursewebclone.Model.User;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName="CourseWeb.db";
    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Generate tables

        String userTable="CREATE TABLE "+DatabaseMaster.User.table_Name+"("+DatabaseMaster.User._ID+" INTEGER PRIMARY KEY,"+DatabaseMaster.User.Col_Name+" TEXT,"+
                DatabaseMaster.User.Col_Password+" TEXT,"+
                DatabaseMaster.User.Col_Type+" TEXT)";
        db.execSQL(userTable);
        String messageTable="CREATE TABLE "+DatabaseMaster.Message.table_Name+"("+DatabaseMaster.Message._ID+" INTEGER PRIMARY KEY,"+DatabaseMaster.Message.Col_User+" INTEGER,"+
                DatabaseMaster.Message.Col_Subject+" TEXT,"+
                DatabaseMaster.Message.Col_Message+" TEXT,"+
                "FOREIGN KEY("+DatabaseMaster.Message.Col_User+") REFERENCES "+ DatabaseMaster.User.table_Name+"("+DatabaseMaster.User._ID+"))";
        db.execSQL(messageTable);
    }
    //Register USer
    public long RegisterUser(User user){
        SQLiteDatabase DB=getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(DatabaseMaster.User.Col_Name,user.getUser());
        data.put(DatabaseMaster.User.Col_Password,user.getPassword());
        data.put(DatabaseMaster.User.Col_Type,user.getUserCategory());
        //returning long id
        return DB.insert(DatabaseMaster.User.table_Name,null,data);
    }
    //Login checker
    //Send Message
    public long SendMessage(Message message){
        SQLiteDatabase DB=getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(DatabaseMaster.Message.Col_Message,message.getMessage());
        data.put(DatabaseMaster.Message.Col_Subject,message.getSubject());
        data.put(DatabaseMaster.Message.Col_User,message.getUserID());
        //returning long id
        return DB.insert(DatabaseMaster.Message.table_Name,null,data);
    }
    //Retrieve All message from Database
    public ArrayList<Message>getAllMessages(){
        SQLiteDatabase Db=getReadableDatabase();
        //SELECT messageID.subject from message where messageID=1
        String MessageArray[]={DatabaseMaster.Message._ID, DatabaseMaster.Message.Col_User,DatabaseMaster.Message.Col_Subject,DatabaseMaster.Message.Col_Message};
        //String selection="Col_User LIKE ? AND Col_Subject LIKE ?";
        //String selectionArg[]={"1","MADD"};
        Cursor cursor=Db.query(DatabaseMaster.Message.table_Name,
                MessageArray,
                null,
                null,
                null,
                null,
                null);
        ArrayList<Message> list=new ArrayList<Message>();
        Message m;
        while(cursor.moveToNext()){
            m=new Message();
            m.setMessageID(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Message._ID)));
            m.setUserID(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.Col_User)));
            m.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.Col_Subject)));
            m.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.Col_Message)));

            list.add(m);
        }
        return list;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
