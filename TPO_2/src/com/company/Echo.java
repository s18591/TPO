package com.company;

public class Echo implements IProtocol {

    public Echo() {
    }
    private String string;

    public Echo(String string) {
        this.string = string;
    }

    @Override
    public String request(String request) {
        return "Echo:0"  + request;
    }

    @Override
    public String getMessg() {
        return string;
    }
}
