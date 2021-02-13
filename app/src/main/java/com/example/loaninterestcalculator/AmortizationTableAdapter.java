package com.example.loaninterestcalculator;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.time.Month;
import java.util.List;

public class AmortizationTableAdapter extends ArrayAdapter<MonthlyRecord> {

    private Context mContext;
    int mResource;

    static class ViewHolder{
        TextView month;
        TextView balance;
        TextView principal;
        TextView interest;
        TextView payment;
    }

    public AmortizationTableAdapter(@NonNull Context context, int resource, @NonNull List<MonthlyRecord> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int month=getItem(position).getMonth();
        String balance=getItem(position).getBalance();
        String principal=getItem(position).getPrincipal();
        String interest=getItem(position).getInterest();
        String payment=getItem(position).getPayment();

        MonthlyRecord monthlyRecord=new MonthlyRecord(month,balance,principal,interest,payment);

        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(mContext);
            convertView=inflater.inflate(mResource,parent,false);
            holder=new ViewHolder();
            holder.month=(TextView) convertView.findViewById(R.id.month);
            holder.balance=(TextView) convertView.findViewById(R.id.balance);
            holder.principal=(TextView) convertView.findViewById(R.id.principal);
            holder.interest=(TextView) convertView.findViewById(R.id.interest);
            holder.payment=(TextView) convertView.findViewById(R.id.payment);

            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.month.setText(String.valueOf(monthlyRecord.getMonth()));
        holder.balance.setText(String.valueOf(monthlyRecord.getBalance()));
        holder.principal.setText(String.valueOf(monthlyRecord.getPrincipal()));
        holder.interest.setText(String.valueOf(monthlyRecord.getInterest()));
        holder.payment.setText(String.valueOf(monthlyRecord.getPayment()));

        return convertView;
    }
}
