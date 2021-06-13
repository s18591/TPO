package com.company;

public enum OperationMode {

    Write("Write", 1),
    Read("Read", 0);

    private final String value;
    private final int mark;

     OperationMode(String value, int mark) {
       this.mark = mark;
       this.value = value;
    }

    public int getMark(){
        return this.mark;
    }


}
