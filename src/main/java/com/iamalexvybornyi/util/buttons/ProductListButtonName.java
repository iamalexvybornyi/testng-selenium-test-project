package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductListButtonName implements ButtonName {

    SIDEBAR_MENU("Sidebar Menu"),
    ;

    @NonNull
    private final String buttonName;
}
