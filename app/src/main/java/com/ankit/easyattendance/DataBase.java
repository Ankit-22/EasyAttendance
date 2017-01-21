package com.ankit.easyattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Scanner;


public class DataBase extends SQLiteOpenHelper
{
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table allData " +
                        "(key integer primary key AUTOINCREMENT, sub_id integer, day integer, month integer, year integer,attended integer) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "allData");
        onCreate(db);
    }
    public DataBase(Context context) {
        super(context, "AllData", null, 1);
    }
    public boolean insertEntries (int sub_id, int day, int month, int year, int attended) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sub_id", sub_id);
        contentValues.put("day", day);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("attended", attended);
        db.insert("allData", null, contentValues);
        return true;
    }
    public ArrayList<String> getAllEntries(int id) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from allData where sub_id="+Integer.toString(id), null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            int j = res.getInt(res.getColumnIndex("attended"));
            if(j==1)
            {
                array_list.add(res.getString(res.getColumnIndex("day"))+"/"+res.getString(res.getColumnIndex("month"))+"/"+res.getString(res.getColumnIndex("year"))+"         Attended");
            }
            else
            {
                array_list.add(res.getString(res.getColumnIndex("day"))+"/"+res.getString(res.getColumnIndex("month"))+"/"+res.getString(res.getColumnIndex("year"))+"         Not Attended");
            }
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public int getAttended(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select sum(attended) from allData where sub_id="+Integer.toString(id), null );
        res.moveToFirst();
        int a = res.getInt(0);
        res.close();
        return a;
    }

    public int getTotal(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor all = db.rawQuery("select attended from allData where sub_id="+Integer.toString(id),null);
        int full = all.getCount();
        all.close();
        return full;
    }

    public double getPercentage(int id) {
        //hp = new HashMap();
        int full = this.getTotal(id);
        int a = this.getAttended(id);
        double d = (double)a/(double)full*100.0;
        double b = d*100;
        d=((int)b/100.0);
        return d;
    }

    public void delete(int id,String s)
    {
        Scanner sc = new Scanner(s);
        sc.useDelimiter(" |/|\r\n");
        int day = sc.nextInt();
        int month = sc.nextInt();
        int year = sc.nextInt();
        int att;
        if(s.endsWith("Not Attended"))
            att=0;
        else
            att=1;
        sc.close();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("Select key from allData where sub_id=%d and day=%d and month = %d and year = %d and attended = %d",id,day,month,year,att),null);
        //Log.i("Ankit",String.format("Select key from missedData where event_name='%s' and day=%d and month = %d and year = %d",name,day,month,year));
        res.moveToFirst();
        int key = res.getInt(res.getColumnIndex("key"));
        db.delete("allData","key="+Integer.toString(key),null);
    }
}
