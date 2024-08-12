package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CheckoutStepOnePageButtonName implements ButtonName {

    CANCEL("Cancel"),
    CONTINUE("Continue"),
    ;

    @NonNull
    private final String buttonName;
}
