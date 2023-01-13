package com.example.productstore.exception;

public class CategoryNotExistException extends Exception{

    public CategoryNotExistException(String name) {
        super("The category " + name + " does not exist!");
    }
}
