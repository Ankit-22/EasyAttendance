package com.ankit.easyattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Scanner;


public class EventData extends SQLiteOpenHelper
{
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table eventData " +
                        "(key integer primary key AUTOINCREMENT, event_name text, day integer, month integer, year integer) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "eventData");
        onCreate(db);
    }
    public EventData(Context context) {
        super(context, "EventData", null, 1);
    }
    public boolean insertEntries (String sub_id, int day, int month, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_name", sub_id);
        contentValues.put("day", day);
        contentValues.put("month", month);
        contentValues.put("year", year);
        db.insert("eventData", null, contentValues);
        return true;
    }
    public ArrayList<String> getAllEntries() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from eventData", null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex("event_name"))+"            "+res.getString(res.getColumnIndex("day"))+"/"+res.getString(res.getColumnIndex("month"))+"/"+res.getString(res.getColumnIndex("year")));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }
    public int getCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from eventData", null );
        return res.getCount();
    }
    public void delete(String s)
    {
        Scanner sc = new Scanner(s);
        String name = "";
        String[] all = new String[100];
        int num =0;
        sc.useDelimiter("/| |\r\n");
        while(sc.hasNext())
        {
            all[num]=sc.next();
            //Log.i("Ankit",Integer.toString(num)+"\r\n"+all[num]);
            //name+=sc.next()+" ";
            num=num+1;
        }
        //Log.i("Ankit",Integer.toString(num)+"\r\n");
        name=name+all[0];
        for(int i=1;i<num-14;i++)
            name+=" "+all[i];
        int day=Integer.parseInt(all[num-3]);
        int month = Integer.parseInt(all[num-2]);
        int year = Integer.parseInt(all[num-1]);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("Select key from eventData where event_name='%s' and day=%d and month = %d and year = %d",name,day,month,year),null);
        //Log.i("Ankit",String.format("Select key from missedData where event_name='%s' and day=%d and month = %d and year = %d",name,day,month,year));
        res.moveToFirst();
        int key = res.getInt(res.getColumnIndex("key"));
        db.delete("eventData","key="+Integer.toString(key),null);

    }
}
