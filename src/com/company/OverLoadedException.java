package com.company;

public class OverLoadedException extends RuntimeException{

    public OverLoadedException() {
        super("It has already been loaded");
    }
}
