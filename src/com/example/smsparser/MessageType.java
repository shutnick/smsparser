package com.example.smsparser;

/**
 * Created by Moreno on 25.08.2014.
 */
public enum MessageType {
    ADD("!popolnenie!"),
    REMOVE("!uvedomlenie!");

    private String key;

    MessageType(String key) {
        this.key = key;
    }

    public String getkey() {
        return key;
    }
}