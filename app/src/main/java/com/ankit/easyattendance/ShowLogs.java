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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowLogs extends AppCompatActivity {

    public int subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_logs);
        Intent intent = getIntent();
        final Context context = this;
        subject = intent.getIntExtra("Subject",0);
        //subject= Integer.parseInt(s_subject);
        TextView sub = (TextView) findViewById(R.id.subjectNameAdd);
        if(subject==1)
            sub.setText("AOA");
        if(subject==2)
            sub.setText("DBMS");
        if(subject==3)
            sub.setText("COA");
        if(subject==4)
            sub.setText("TOC");
        if(subject==5)
            sub.setText("CG");
        if(subject==6)
            sub.setText("AM-IV");
        final DataBase db = new DataBase(this);
        ArrayList<String> list = db.getAllEntries(subject);
        if(list.isEmpty())
        {
            list.add("No Logs Found\r\n");
        }
        ArrayAdapter<String> adapter;
        ListView lv = (ListView) findViewById(R.id.list);
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
                                db.delete(subject,s);
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
}
