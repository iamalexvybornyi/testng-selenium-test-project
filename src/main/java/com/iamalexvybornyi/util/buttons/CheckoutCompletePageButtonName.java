package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CheckoutCompletePageButtonName implements ButtonName {

    BACK_HOME("Back Home"),
    ;

    @NonNull
    private final String buttonName;
}
