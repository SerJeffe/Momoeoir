package com.example.momoeoir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Conn extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "momo.db";
    public static final String TABLE = "diary";
    public static final String TABLEcolor = "Color";
    public static final String COL1 = "ID";
    public static final String COL2 = "TITLE";
    public static final String COL3 = "DAT";
    public static final String COL4 = "DATE";
    public static final String MSG = "ASS";
    Context con;

    //public static final String MSG
    //DATABASE CREATION
    public Conn(Context con){
        super(con, DATABASE_NAME, null, 1);
        this.con = con;
        SQLiteDatabase db = this.getWritableDatabase();
    }
    //TABLE CREATION
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE+"("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT,"+COL3+" TEXT, "+COL4+" TEXT)");
        db.execSQL("create table "+TABLEcolor+"(ID INTEGER PRIMARY KEY, COLOR TEXT)");
        db.execSQL("insert into "+TABLEcolor+" values(0,'pink')");
        db.execSQL("insert into "+TABLEcolor+" values(1,'Ser Jeffe')");
    }
    //UPGRADE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    //SET DATA
    public boolean insert(String title,String content, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,title);
        cv.put(COL3,content);
        cv.put(COL4,date);
        long result = db.insert(TABLE,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    //GETDATA
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE,null);
        return res;
    }
    //UPDATE
    public boolean UpdateData(String title, String body, String date, String old)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(COL2,title);
        con.put(COL3,body);
        con.put(COL4,date);
        Log.i(MSG,""+old);
        db.update(TABLE,con,"TITLE = ?", new String[] {old});
        return true;
    }
    //delete
    public Integer deleteShiz(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE,"TITLE = ?", new String[] {title});
    }
    //COLOR update
    public boolean updateColor(String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("COLOR",color);
        db.update(TABLEcolor,con,"ID = "+0,null);
        return true;
    }
    //COLOR GET
    public Cursor getColor(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLEcolor+" where ID ="+0,null);
        return res;
    }
    public boolean updateName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("COLOR",name);
        db.update(TABLEcolor,con,"ID = "+1,null);
        return true;
    }
    public Cursor getName(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLEcolor+" where ID ="+1,null);
        return res;
    }
}

