package com.iamalexvybornyi.core.exception;

import lombok.NonNull;

public class UnsupportedBrowserTypeException extends RuntimeException {

    public UnsupportedBrowserTypeException(@NonNull String providedBrowserValue) {
        super(String.format("Provided browser type '%s' is not supported", providedBrowserValue));
    }
}
