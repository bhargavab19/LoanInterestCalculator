package com.example.loaninterestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AmortizationTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amortization_table);

        ListView listview=findViewById(R.id.amrtztable);

        Intent intent=getIntent();
        ArrayList<MonthlyRecord> amrtzTable=intent.getParcelableArrayListExtra(MainActivity.key);

        AmortizationTableAdapter adapter=new AmortizationTableAdapter(this,R.layout.amortization_view_layout,amrtzTable);
        listview.setAdapter(adapter);
    }
}