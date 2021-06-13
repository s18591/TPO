package com.company;

import java.util.Random;

public final class Request extends Message {

    private static final Random r = new Random();
    private final  int component;
    private final int component2;

    public Request(Requestor requestor){
        super(requestor,MessagePriority.getRandomPriority());
        this.component = r.nextInt();
        this.component2 = r.nextInt();
    }
    public int getComponent(){
        return component;
    }
    public int getComponent2(){
        return component2;
    }

    public String toString(){
        return super.toString() + "(" + this.component + ", " + this.component2 + ")";
    }

    @Override
    public int compareTo(Message o) {
        return 0;
    }
}
