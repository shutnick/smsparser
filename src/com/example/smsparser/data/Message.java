package com.example.smsparser.data;

import com.example.smsparser.MessageType;

import java.util.Date;

/**
 * Created by s.sofienko on 05-Sep-14.
 */
public class Message {
    private long mCardNumber;
    private MessageType mType;
    private Date mDate;
    private String mPlace;
    private double mAmount;
    private double mRest;

    public Message(long card, MessageType type, Date date, String place, double amount, double rest) {
        mCardNumber = card;
        mType = type;
        mDate = date;
        mPlace = place;
        mAmount = amount;
        mRest = rest;
    }

    public long getCardNumber() {
        return mCardNumber;
    }

    public MessageType getType() {
        return mType;
    }

    public Date getDate() {
        return mDate;
    }

    public String getPlace() {
        return mPlace;
    }

    public double getAmount() {
        return mAmount;
    }

    public double getRest() {
        return mRest;
    }
}
