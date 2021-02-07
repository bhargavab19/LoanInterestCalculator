package com.example.loaninterestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    String str_pymntFrequency;
    static int freq;
    double pv,r;
    int per;
    public static final String key="com.example.loaninterestcalculator.AmrtzTable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this, AmortizationTable.class);

        Button btnCalc=(Button) findViewById(R.id.button_calculate);
        Button button=findViewById(R.id.button);

        EditText eTextLoanAmount=(EditText) findViewById(R.id.editText_loanAmount);
        EditText eTextInterestRate=(EditText) findViewById(R.id.editText_interestRate);
        EditText eTextPeriod=(EditText) findViewById(R.id.editText_period);
        TextView emiAmount=(TextView) findViewById(R.id.textView_emiAmount);
        TextView effROI=(TextView) findViewById(R.id.textView_effROI);

        Spinner pymntFrequency=(Spinner) findViewById(R.id.spinner_repaymentType);
        pymntFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_pymntFrequency =parent.getItemAtPosition(position).toString();
                switch (position){
                    case 0:
                        freq=12;
                        break;

                    case 1:
                        freq=4;
                        break;

                    case 2:
                        freq=2;
                        break;

                    case 3:
                        freq=1;
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pv= Double.parseDouble(eTextLoanAmount.getText().toString());
                r= Double.parseDouble(eTextInterestRate.getText().toString())/(freq*100);
                per=Integer.parseInt(eTextPeriod.getText().toString())*freq;

                Double emiCalc=calcEMI(pv,r,per);
                Double effROICalc=calcEffROI(emiCalc,pv,per);
                DecimalFormat formatVal=new DecimalFormat("#,###.##");

                emiAmount.setText("\u20B9" + formatVal.format(emiCalc));
                effROI.setText(formatVal.format(effROICalc)+"%");


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putStringArrayListExtra(key,displayAmortizationTable(pv,r,per));
                startActivity(intent);
            }
        });
    }

    public Double calcEMI(double pv,double r,int per){
        return (pv*r*Math.pow(1+r,per))/(Math.pow(1+r,per)-1);
    }

    public Double calcEffROI(double emiCalc,double pv,int per){
        return (emiCalc*per-pv)/pv*100;
    }

    public ArrayList<String> displayAmortizationTable(double pv,double r,int per){

        double balance= pv;
        double payment=calcEMI(pv,r,per);
        double irPaid, principalPaid, newBalance;
        DecimalFormat formatVal=new DecimalFormat("#,###.##");

        ArrayList<String> amrtzTable=new ArrayList<>();
        amrtzTable.add("Month   Old Balance   Payment   Interest   Principal   New Balance");

        for(int month=1; month<=per;month++){
            irPaid=balance*r;
            principalPaid=payment-irPaid;
            newBalance=balance-principalPaid;

            amrtzTable.add(month+"   "+formatVal.format(balance)+"   "+formatVal.format(payment)+"   "+formatVal.format(irPaid)+"   "+formatVal.format(principalPaid)+"   "+formatVal.format(newBalance));

            balance=newBalance;
        }

        return amrtzTable;
    }
}
