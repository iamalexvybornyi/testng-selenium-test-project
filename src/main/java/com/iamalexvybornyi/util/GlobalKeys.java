package com.iamalexvybornyi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public enum GlobalKeys {

    TEST_ID("testId");

    @NonNull
    private final String key;
}
