package com.example.geektrust.exceptions;

import com.example.geektrust.errors.ErrorInterface;

public class InventoryNotFoundExceptions extends RuntimeException{


    private ErrorInterface error;
    private String message;

    public InventoryNotFoundExceptions(ErrorInterface error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public InventoryNotFoundExceptions(ErrorInterface error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.message = message;
    }
}
