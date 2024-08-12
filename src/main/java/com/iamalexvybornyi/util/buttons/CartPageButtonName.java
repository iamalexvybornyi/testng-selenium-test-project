package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CartPageButtonName implements ButtonName {

    CHECKOUT("Checkout"),
    CONTINUE_SHOPPING("Continue Shopping"),
    ;

    @NonNull
    private final String buttonName;
}
