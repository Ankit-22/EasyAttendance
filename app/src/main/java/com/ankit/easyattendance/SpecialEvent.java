package com.ankit.easyattendance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class SpecialEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_event);
        final EventData db = new EventData(this);
        final Context context = this;
        ArrayList<String> list = db.getAllEntries();
        if(list.isEmpty())
        {
            list.add("No Logs Found\r\n");
        }
        ArrayAdapter<String> adapter;
        ListView lv = (ListView) findViewById(R.id.eventList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String s = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.delete(s);
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });

    }
    public void addEvent(View view)
    {
        EditText et = (EditText) findViewById(R.id.eventName);
        String s = et.getText().toString();
        Intent intent = new Intent(this , MainActivity.class);
        if(s.matches(""))
        {
            Toast.makeText(getApplicationContext(),"Nothing Entered",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        EventData db = new EventData(this);
        Date date1 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        db.insertEntries(s,day,month,year);
        Toast.makeText(getApplicationContext(),"Added Event "+s,Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
