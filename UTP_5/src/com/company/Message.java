package com.company;

import java.util.Date;

public abstract class Message implements Comparable<Message> {
    private static int currentId = 0;
    private synchronized static  int getCurrentId(){
        return currentId++;
    }
    private final int id;
    private final Requestor requestor;
    private final MessagePriority priority;
    private final Date date;

    protected Message(Requestor requestor,MessagePriority priority){
        this.id = getCurrentId();
        this.requestor =requestor;
        this.priority = priority;
        this.date = new Date();
    }
    public final int getId(){
        return id;
    }
    public final MessagePriority getPriority(){
        return priority;
    }
    public final Requestor getRequestor(){
        return this.requestor;
    }
    public final MessageQueue responseQueue(){
        return  getRequestor().responce();
    }
    public final int compareTo(Message message){
        int res = 0;
        if(message == null){
            res = -1;
        }
        res = getPriority().compareTo(message.getPriority());
        if(res == 0){
            res = this.id - message.id ;

        }
        return res;
    }

    public String toString(){
        return getClass().getName() + " (" + getId() + ")";
    }
}
