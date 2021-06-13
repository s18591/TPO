package com.company;

import java.util.Random;

public enum MessagePriority {
    High("High priority"),
    Regular("Regular priority"),
    Low("Low priority");

    private static final Random r = new Random();

    public static MessagePriority getRandomPriority(){
        MessagePriority[] values = MessagePriority.values();
        int index = r.nextInt(values.length);
        return values[index];
    }
    private final String l;
    private MessagePriority(String l){
        this.l = l;
    }
    public String getLocalisedName(){
        return l;
    }

}
