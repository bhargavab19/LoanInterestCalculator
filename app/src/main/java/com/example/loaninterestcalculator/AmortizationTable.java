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

        Intent intent=getIntent();
        ArrayList<String> key=intent.getStringArrayListExtra(MainActivity.key);

        ListView mylistview=findViewById(R.id.mylistview);
        ArrayAdapter<String> amrtzArrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,key);
        mylistview.setAdapter(amrtzArrayAdapter);
    }
}