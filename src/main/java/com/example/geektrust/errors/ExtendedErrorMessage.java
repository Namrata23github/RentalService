package com.example.geektrust.errors;

public enum ExtendedErrorMessage implements ErrorInterface{

    INVENTORY_NOT_FOUND("%s not found for id %"),
    ALREADY_EXISTS("% with id % already exists");

    private final String message;

    ExtendedErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
