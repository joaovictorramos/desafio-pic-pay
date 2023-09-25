package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exceptions.DataNotValidException;
import com.example.demo.exceptions.ValueNotIdentifiedException;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<String> handleDataValidException(DataNotValidException ex){
        return ResponseEntity.badRequest().body("Data not valid! \n");
    }

    @ExceptionHandler(ValueNotIdentifiedException.class)
    public ResponseEntity<String> handleValueNotIdentifiedException(ValueNotIdentifiedException ex){
        return ResponseEntity.badRequest().body("Value not identified! \n");
    }
}
