package com.company;

public final class Response extends Message {
    private final int result;

    public Response(Request request){
        super(request.getRequestor(), request.getPriority());
        result = request.getComponent() + request.getComponent2();
    }
    public int getResult(){
        return result;
    }
    public String toString(){
        return super.toString() + "(" + this.result + ")";
    }
}
