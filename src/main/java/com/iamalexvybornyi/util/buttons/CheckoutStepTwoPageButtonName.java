package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CheckoutStepTwoPageButtonName implements ButtonName {

    CANCEL("Cancel"),
    FINISH("Finish"),
    ;

    @NonNull
    private final String buttonName;
}
