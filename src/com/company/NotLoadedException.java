package com.company;

public class NotLoadedException extends RuntimeException{

    public NotLoadedException() {
        super("Not loaded");
    }
}
