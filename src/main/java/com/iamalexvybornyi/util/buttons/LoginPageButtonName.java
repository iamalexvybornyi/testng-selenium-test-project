package com.iamalexvybornyi.util.buttons;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LoginPageButtonName implements ButtonName {

    LOGIN("Login"),
    ;

    @NonNull
    private final String buttonName;
}
