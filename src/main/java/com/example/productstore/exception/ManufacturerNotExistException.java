package com.example.productstore.exception;

public class ManufacturerNotExistException extends Exception{

    public ManufacturerNotExistException(String name) {
        super("Manufacturer " + name + " does not exist!");
    }
}
