package com.example.coursewebclone.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static android.content.ContentValues.TAG;
import androidx.annotation.Nullable;

import com.example.coursewebclone.Model.Message;
import com.example.coursewebclone.Model.User;

import java.util.ArrayList;
import java.util.List;

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
    public List readAllInfo(String req){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                DatabaseMaster.User._ID,
                DatabaseMaster.User.Col_Name,
                DatabaseMaster.User.Col_Password,
                DatabaseMaster.User.Col_Type

        };

        //String sortOrder = DBStructure.User.COLUMN_USER_COL1 + " DESC";

        Cursor cursor = db.query(
                DatabaseMaster.User.table_Name,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();
        List types = new ArrayList<>();

        while (cursor.moveToNext()){
            //Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(DBStructure.User._ID));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.User.Col_Name));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.User.Col_Password));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.User.Col_Type));

            userNames.add(username);
            passwords.add(password);
            types.add(type);

        }

        cursor.close();
        Log.i(TAG,"readAllInfo:"+ userNames);

        if(req == "user"){
            return userNames;
        }
        else if(req == "password"){
            return passwords;
        }
        else if (req == "type"){
            return types;
        }
        else{
            return null;
        }

    }
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
   /* public ArrayList<Message>getAllMessages(){
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
*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
