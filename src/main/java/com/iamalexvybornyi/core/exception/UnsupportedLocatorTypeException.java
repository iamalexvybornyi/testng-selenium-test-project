package com.iamalexvybornyi.core.exception;

public class UnsupportedLocatorTypeException extends RuntimeException {
    public UnsupportedLocatorTypeException() {
        super("The provided locator type is not supported");
    }
}
