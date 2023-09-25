package com.example.demo.exceptions;

public class ValueNotIdentifiedException extends NumberFormatException{
    public ValueNotIdentifiedException(String message){
        super(message);
    }
}
