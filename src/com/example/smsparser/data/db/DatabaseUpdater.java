package com.example.smsparser.data.db;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.example.smsparser.MessageType;
import com.example.smsparser.data.Message;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by s.sofienko on 05-Sep-14.
 */
public class DatabaseUpdater {

    private final String ADDRESS_COLUMN_NAME ="address";
    private final String BODY_COLUMN_NAME = "body";
    private final String BANK_ADDRESS = "CAgricole";

    private Context mContext;
    private MessageDBProvider mDatabaseProvider;
    private Date mLastUpdateDate;

    public DatabaseUpdater(Context context) {
        mContext = context;
        mDatabaseProvider = MessageDBProvider.getInstance(context);
        if (mDatabaseProvider.size() == 0) {
            updateData();
        }
    }


    public void updateData() {
        Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        cursor.moveToFirst();
        int bankAddressColumnIndex = cursor.getColumnIndex(ADDRESS_COLUMN_NAME);
        int messageColumnIndex = cursor.getColumnIndex(BODY_COLUMN_NAME);
        List<String> bankMessages = new LinkedList<String>();

        while (!cursor.isAfterLast()) {
            if (BANK_ADDRESS.equals(cursor.getString(bankAddressColumnIndex))) {
                Message message = new Message(0L, MessageType.REMOVE, new Date(), "", 0D, 0D);
                mDatabaseProvider.insertMessage(message);
                bankMessages.add(cursor.getString(messageColumnIndex));
            }
            cursor.moveToNext();
        }
    }
}
