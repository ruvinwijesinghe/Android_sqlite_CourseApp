package com.example.coursewebclone.Database;

import android.provider.BaseColumns;

public class DatabaseMaster  {
    public static class User implements BaseColumns{
        public static final String table_Name="User";
        public static final String Col_Name="Name";
        public static final String Col_Password="Password";
        public static final String Col_Type="Type";
            
    }
    public static class Message implements  BaseColumns{
        public static final String table_Name="Message";
        public static final String Col_User="user";
        public static final String Col_Subject="subject";
        public static final String Col_Message="message";

    }
}
