package com.example.loaninterestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{

    String str_pymntFrequency;
    static int freq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalc=(Button) findViewById(R.id.button_calculate);

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
                EditText eTextLoanAmount=(EditText) findViewById(R.id.editText_loanAmount);
                EditText eTextInterestRate=(EditText) findViewById(R.id.editText_interestRate);
                EditText eTextPeriod=(EditText) findViewById(R.id.editText_period);
                TextView emiAmount=(TextView) findViewById(R.id.textView_emiAmount);
                TextView effROI=(TextView) findViewById(R.id.textView_effROI);

                double pv= Double.parseDouble(eTextLoanAmount.getText().toString());
                double r= Double.parseDouble(eTextInterestRate.getText().toString())/(freq*100);
                int per=Integer.parseInt(eTextPeriod.getText().toString())*freq;

                Double emiCalc=calcEMI(pv,r,per);
                Double effROICalc=calcEffROI(emiCalc,pv,per);
                DecimalFormat formatVal=new DecimalFormat("#,###.##");

                emiAmount.setText("\u20B9" + formatVal.format(emiCalc));
                effROI.setText(formatVal.format(effROICalc)+"%");
            }
        });
    }

    public Double calcEMI(double pv,double r,int per){
        return (pv*r*Math.pow(1+r,per))/(Math.pow(1+r,per)-1);
    }

    public Double calcEffROI(double emiCalc,double pv,int per){
        return (emiCalc*per-pv)/pv*100;
    }
}
