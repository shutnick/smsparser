package com.example.smsparser.data.db;

import android.content.Context;
import com.example.smsparser.data.Message;

/**
 * Created by s.sofienko on 05-Sep-14.
 */
public class MessageDBProvider {
    private static MessageDBProvider mMessageProvider;
    private MessageDB mMesageDatabase;
    private MessageDBProvider(Context context) {
        mMesageDatabase = new MessageDB(context);
    }
    
    public static MessageDBProvider getInstance(Context context) {
        if (mMessageProvider == null) {
            mMessageProvider = new MessageDBProvider(context);
        }
        return mMessageProvider;
    }

    public long size() {
        return mMesageDatabase.size();
    }

    public void insertMessage(Message message) {
        mMesageDatabase.insert(message);
    }
}
