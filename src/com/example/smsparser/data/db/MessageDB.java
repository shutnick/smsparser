package com.example.smsparser.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.smsparser.data.Message;

/**
 * Created by s.sofienko on 05-Sep-14.
 */
public class MessageDB extends SQLiteOpenHelper{

    public static final String MESSAGE_DB_NAME = "message_db";
    public static final String MESSAGE_LIST_NAME = "message_list";
    private static final int MESSAGE_DB_VERSION = 1;
    private static final String CARD_COLUMN = "card";
    private static final String TYPE_COLUMN = "type";
    private static final String DATE_COLUMN = "date";
    private static final String PLACE_COLUMN = "place";
    private static final String AMOUNT_COLUMN = "amount";
    private static final String REST_COLUMN = "rest";
    private static final String TEXT_TYPE = " TEXT";

    private SQLiteDatabase mDatabase;

    private final String CREATE_MESSAGE_TABLE = new StringBuilder().
            append("CREATE TABLE ").append(MESSAGE_LIST_NAME).append(" (").
            append(CARD_COLUMN).append(TEXT_TYPE).append(", ").
            append(TYPE_COLUMN).append(TEXT_TYPE).append(", ").
            append(DATE_COLUMN).append(TEXT_TYPE).append(", ").
            append(PLACE_COLUMN).append(TEXT_TYPE).append(", ").
            append(AMOUNT_COLUMN).append(TEXT_TYPE).append(", ").
            append(REST_COLUMN).append(TEXT_TYPE).append(");").
            toString();

    public MessageDB(Context context) {
        super(context, MESSAGE_DB_NAME, null, MESSAGE_DB_VERSION);
        mDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected void insert(Message message) {
        ContentValues values = new ContentValues();
        values.put(CARD_COLUMN, message.getCardNumber());
        values.put(TYPE_COLUMN, message.getType().getkey());
        values.put(DATE_COLUMN, message.getDate().getTime());
        values.put(PLACE_COLUMN, message.getPlace());
        values.put(AMOUNT_COLUMN, message.getAmount());
        values.put(REST_COLUMN, message.getRest());

        mDatabase.insert(MESSAGE_LIST_NAME, null, values);
    }

    protected Cursor getMessageCursor() {
        Cursor messageCursor = mDatabase.query(MESSAGE_LIST_NAME, null, null, null, null, null, null);
        messageCursor.moveToFirst();
        return messageCursor;
    }

    protected long size() {
        return DatabaseUtils.queryNumEntries(mDatabase, MESSAGE_LIST_NAME);
    }
}
