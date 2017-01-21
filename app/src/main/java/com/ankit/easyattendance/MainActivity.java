package com.ankit.easyattendance;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA = "com.ankit.easyattendance.Subject";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase db = new DataBase(this);
        double a = db.getPercentage(1);
        int tot = db.getTotal(1);
        Button b =(Button) findViewById(R.id.perc_aoa);
        if(a>=0 && tot>0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        a = db.getPercentage(2);
        b = (Button) findViewById(R.id.perc_dbms);
        tot = db.getTotal(2);
        if(a>=0 && tot>0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        a=db.getPercentage(3);
        b = (Button) findViewById(R.id.perc_coa);
        tot = db.getTotal(3);
        if(a>=0 && tot>0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        a=db.getPercentage(4);
        b = (Button) findViewById(R.id.perc_toc);
        tot = db.getTotal(4);
        if(a>=0 && tot>0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        a=db.getPercentage(5);
        b = (Button) findViewById(R.id.perc_cg);
        tot = db.getTotal(5);
        if(a>=0 && tot>0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        a=db.getPercentage(6);
        b = (Button) findViewById(R.id.perc_am4);
        tot = db.getTotal(6);
        if(a>=0 && tot>0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        a=0;
        tot=0;
        b = (Button) findViewById(R.id.perc_total);
        for(int i = 1;i < 7;i++)
        {
            a+=(double)db.getAttended(i);
            tot+=db.getTotal(i);
        }
        a=a/(double)tot*10000.0;
        tot = (int)a;
        a = (double)tot/100.0;
        if(a>=0 && tot > 0)
        {
            b.setText(Double.toString(a)+"%");
            if(a<75.0)
                b.setTextColor(Color.RED);
        }
        EventData e = new EventData(this);
        tot = e.getCount();
        b = (Button) findViewById(R.id.perc_spev);
        b.setText(Integer.toString(tot));
        MissedData m = new MissedData(this);
        tot = m.getCount();
        b = (Button) findViewById(R.id.perc_misstut);
        b.setText(Integer.toString(tot));
    }
    public void aoa(View view)
    {
        Intent intent = new Intent(this , ShowSubject.class);
        intent.putExtra(EXTRA,"1");
        startActivity(intent);
    }
    public void dbms(View view)
    {
        Intent intent = new Intent(this , ShowSubject.class);
        intent.putExtra(EXTRA,"2");
        startActivity(intent);
    }
    public void coa(View view)
    {
        Intent intent = new Intent(this , ShowSubject.class);
        intent.putExtra(EXTRA,"3");
        startActivity(intent);
    }
    public void toc(View view)
    {
        Intent intent = new Intent(this , ShowSubject.class);
        intent.putExtra(EXTRA,"4");
        startActivity(intent);
    }
    public void cg(View view)
    {
        Intent intent = new Intent(this , ShowSubject.class);
        intent.putExtra(EXTRA,"5");
        startActivity(intent);
    }
    public void am4(View view)
    {
        Intent intent = new Intent(this , ShowSubject.class);
        intent.putExtra(EXTRA,"6");
        startActivity(intent);
    }
    public void spEv(View view)
    {
        Intent intent = new Intent(this , SpecialEvent.class);
        startActivity(intent);
    }
    public void missTut(View view)
    {
        Intent intent = new Intent(this , MissedTuts.class);
        startActivity(intent);
    }

}
