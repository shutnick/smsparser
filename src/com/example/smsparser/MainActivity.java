package com.example.smsparser;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends Activity {
    private final String ADDRESS_COLUMN_NAME ="address";
    private final String BODY_COLUMN_NAME = "body";
    private final String BANK_ADDRESS = "CAgricole";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView messagesListView = (ListView) findViewById(R.id.messages_list_view);
//        TextView textView = (TextView) findViewById(R.id.text_view);

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        cursor.moveToFirst();

        List<String> bankMessages = new LinkedList<String>();
        MessageDetailsHolder holder = MessageDetailsHolder.getInstance();

        int bankAddressColumnIndex = cursor.getColumnIndex(ADDRESS_COLUMN_NAME);
        int messageColumnIndex = cursor.getColumnIndex(BODY_COLUMN_NAME);

        while (!cursor.isAfterLast()) {
            if (BANK_ADDRESS.equals(cursor.getString(bankAddressColumnIndex))) {
                bankMessages.add(cursor.getString(messageColumnIndex));
            }
            cursor.moveToNext();
        }


//        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_item, R.id.text_view, bankMessages);
//        messagesListView.setAdapter(adapter);
        holder.parseMessages(bankMessages);
    }


}
