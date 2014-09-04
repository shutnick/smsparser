package com.example.smsparser.data;

import com.example.smsparser.MessageType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Moreno on 25.08.2014.
 */
public class MessageDetailsHolder {

    public static final String REMOVE_DATE_PATTERN = "dd/MM HH:mm";
    public static final String ADD_DATE_PATTERN = "dd.MM HH:mm";

    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int OPERATION_DATE_INDEX = 1;
    public static final int CARD_NUMBER_INDEX = 2;
    public static final int SUM_INDEX = 3;
    public static final int REST_INDEX = 4;
    public static final int PLACE_INDEX = 5;



    private static MessageDetailsHolder holder;
    private List<MessageType> types;
    private List<Date> dates;
    private List<Integer> cardNumbers;
    private List<Float> amounts;
    private List<String> places;
    private List<Float> restAfterOperation;

    private MessageDetailsHolder() {
        types = new LinkedList<MessageType>();
        dates = new LinkedList<Date>();
        cardNumbers = new LinkedList<Integer>();
        amounts = new LinkedList<Float>();
        restAfterOperation = new LinkedList<Float>();
        places = new LinkedList<String>();
    }

    public static MessageDetailsHolder getInstance() {
        if (holder == null) {
            holder = new MessageDetailsHolder();
        }

        return holder;
    }

    public void addMessage (MessageType type, Date date, Integer cardNumber, Float amount, Float rest, String place){
        types.add(type);
        dates.add(date);
        cardNumbers.add(cardNumber);
        amounts.add(amount);
        restAfterOperation.add(rest);
        places.add(place);
    }

    public void parseMessages(List<String> messages) {
        SimpleDateFormat format = new SimpleDateFormat();
        for (String message : messages) {
            String[] details = message.split("\n");
            MessageType type = details[OPERATION_TYPE_INDEX].equals(MessageType.ADD.getkey()) ?
                    MessageType.ADD : MessageType.REMOVE;

            Date date = new Date();
            try {
                String dateToFormat = details[OPERATION_DATE_INDEX].substring(5);
                switch (type) {
                    case ADD:
                        format.applyPattern(ADD_DATE_PATTERN);
                        date = format.parse(dateToFormat);
                        break;
                    case REMOVE:
                        format.applyPattern(REMOVE_DATE_PATTERN);
                        date = format.parse(dateToFormat);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String stringCardNumber = details[CARD_NUMBER_INDEX];
            Integer cardNumber = Integer.valueOf(stringCardNumber.substring(stringCardNumber.indexOf('-') + 1));

            String stringSum = details[SUM_INDEX];
            Float sum = Float.parseFloat(stringSum.substring(stringSum.indexOf(' ') + 1, stringSum.indexOf("UAH")));

            String stringRest = details[REST_INDEX];
            Float rest = Float.valueOf(stringRest.substring(stringSum.indexOf(' ') + 1, stringSum.indexOf("UAH")));

            holder.addMessage(type, date, cardNumber, sum, rest, details[PLACE_INDEX]);
        }
    }
}