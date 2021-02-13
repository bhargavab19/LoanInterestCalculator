package com.example.loaninterestcalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class MonthlyRecord implements Parcelable {
    private int month;
    private String balance;
    private String principal;
    private String interest;
    private String payment;

    public MonthlyRecord(int month, String balance, String principal, String interest, String payment) {
        this.month = month;
        this.balance = balance;
        this.principal = principal;
        this.interest = interest;
        this.payment = payment;
    }

    protected MonthlyRecord(Parcel in) {
        month = in.readInt();
        balance = in.readString();
        principal = in.readString();
        interest = in.readString();
        payment = in.readString();
    }

    public static final Creator<MonthlyRecord> CREATOR = new Creator<MonthlyRecord>() {
        @Override
        public MonthlyRecord createFromParcel(Parcel in) {
            return new MonthlyRecord(in);
        }

        @Override
        public MonthlyRecord[] newArray(int size) {
            return new MonthlyRecord[size];
        }
    };

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(month);
        dest.writeString(balance);
        dest.writeString(principal);
        dest.writeString(interest);
        dest.writeString(payment);
    }
}
