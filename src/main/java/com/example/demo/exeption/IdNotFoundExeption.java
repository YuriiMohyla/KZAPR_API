package com.example.demo.exeption;

import java.util.function.Supplier;

public class IdNotFoundExeption extends Exception{

    private long id;

    public IdNotFoundExeption(long id) {
        super(String.format("record not found by id : '%s'", id));
        this.id = id;
    }
}
