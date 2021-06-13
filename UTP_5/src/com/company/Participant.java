package com.company;

public abstract class Participant implements Runnable {

    private static final int sleep = 1000;
    private static int currentId = 0;

    private static int getCurrentId() {
        return currentId++;
    }

    private final int id;
    protected final MessageQueue queue;

    protected Participant(MessageQueue queue){
        id = getCurrentId();
        this.queue = queue;
    }
    public final int getId(){
        return id;
    }

    public String toString(){
        return getClass().getName() + " (" + getId() + ")";
    }

    protected void sleep(){
        try{
            Thread.sleep(sleep);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
