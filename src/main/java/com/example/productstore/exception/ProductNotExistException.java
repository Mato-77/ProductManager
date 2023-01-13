package com.example.productstore.exception;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(Long id){
        super("Product with id=" + id + " doesn't exist!" );
    }
}
