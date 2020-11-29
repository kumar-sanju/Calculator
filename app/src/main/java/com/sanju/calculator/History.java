package com.sanju.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private ListView lv;
    private DBHelperCalculator dbHelperCalculator;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private String calcName="";
    private String []EmptyList={"There is  no history yet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv=(ListView)findViewById(R.id.listView);
        dbHelperCalculator =new DBHelperCalculator(this);
        calcName=getIntent().getStringExtra("calcName");
        list= dbHelperCalculator.showHistory(calcName);
        if(!list.isEmpty())
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        else
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,EmptyList);
        lv.setAdapter(adapter);
    }

    public void onClick(View v)
    {
        dbHelperCalculator.deleteRecords(calcName);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,EmptyList);
        lv.setAdapter(adapter);
    }

}
