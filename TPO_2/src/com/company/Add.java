package com.company;

public class Add implements IProtocol {
    private String string;

    public Add(String string) {
        this.string = string;
    }

    public Add() {
    }

    @Override
    public String request(String request) {
                int n = Integer.parseInt(request);
                int summ = n + n;
                return summ + "";
    }

    @Override
    public String getMessg() {
        return string;
    }
}
