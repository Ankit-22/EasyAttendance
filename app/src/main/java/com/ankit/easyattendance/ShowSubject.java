package com.ankit.easyattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ShowSubject extends AppCompatActivity {
    public int subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subject);
        Intent intent = getIntent();
        String s_subject = intent.getStringExtra(MainActivity.EXTRA);
        subject= Integer.parseInt(s_subject);
        TextView sub = (TextView) findViewById(R.id.subjectName);
        TextView atte = (TextView) findViewById(R.id.Attended);
        DataBase db = new DataBase(this);
        if(subject==1)
        {
            int att,tot;
            att=db.getAttended(subject);
            tot=db.getTotal(subject);
            atte.setText("Attended: "+Integer.toString(att)+"/"+Integer.toString(tot));
            sub.setText("AOA");
        }
        if(subject==2)
        {
            int att,tot;
            att=db.getAttended(subject);
            tot=db.getTotal(subject);
            atte.setText("Attended: "+Integer.toString(att)+"/"+Integer.toString(tot));
            sub.setText("DBMS");
        }
        if(subject==3)
        {
            int att,tot;
            att=db.getAttended(subject);
            tot=db.getTotal(subject);
            atte.setText("Attended: "+Integer.toString(att)+"/"+Integer.toString(tot));
            sub.setText("COA");
        }
        if(subject==4)
        {
            int att,tot;
            att=db.getAttended(subject);
            tot=db.getTotal(subject);
            atte.setText("Attended: "+Integer.toString(att)+"/"+Integer.toString(tot));
            sub.setText("TOC");
        }
        if(subject==5)
        {
            int att,tot;
            att=db.getAttended(subject);
            tot=db.getTotal(subject);
            atte.setText("Attended: "+Integer.toString(att)+"/"+Integer.toString(tot));
            sub.setText("CG");
        }
        if(subject==6)
        {
            int att,tot;
            att=db.getAttended(subject);
            tot=db.getTotal(subject);
            atte.setText("Attended: "+Integer.toString(att)+"/"+Integer.toString(tot));
            sub.setText("AM-IV");
        }
        /*db.insertEntries(1,13,12,2016,1);
        db.insertEntries(2,13,12,2016,0);
        ArrayList<String> array_list = db.getAllEntries();
        for(String s : array_list)
        {
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }*/
    }
    public void addEntry(View view)
    {
        EditText att = (EditText) findViewById(R.id.attendedText);
        EditText tot = (EditText) findViewById(R.id.Total);
        if(!att.getText().toString().matches("")&&!tot.getText().toString().matches(""))
        {
            int a = Integer.parseInt(att.getText().toString());
            int t = Integer.parseInt(tot.getText().toString());
            if (a > t || t <= 0 || a < 0)
            {
                Toast.makeText(getApplicationContext(), "Please enter proper inputs", Toast.LENGTH_SHORT).show();
            }
            else
            {
                for(int i = 0;i<a;i++)
                {
                    DataBase db = new DataBase(this);
                    Date date1 = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date1);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int year = cal.get(Calendar.YEAR);
                    db.insertEntries(subject, day, month, year, 1);
                }
                for(int i = 0; i < t-a; i++)
                {
                    DataBase db = new DataBase(this);
                    Date date1 = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date1);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int year = cal.get(Calendar.YEAR);
                    db.insertEntries(subject, day, month, year, 0);
                }
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Inputs are null",Toast.LENGTH_LONG).show();
        }
    }
    public void showLogs(View view)
    {
        Intent intent = new Intent(this, ShowLogs.class);
        intent.putExtra("Subject",subject);
        startActivity(intent);
    }
}
