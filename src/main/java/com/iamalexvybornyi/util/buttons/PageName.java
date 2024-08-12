package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PageName {

    LOGIN("Login"),
    CART("Cart"),
    CHECKOUT_STEP_ONE("Checkout Step One"),
    CHECKOUT_STEP_TWO("Checkout Step Two"),
    CHECKOUT_COMPLETE("Checkout Complete"),
    PRODUCT_LIST("Product List"),
    ;

    @NonNull
    private final String pageName;
}
