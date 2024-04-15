package com.iamalexvybornyi.model;

import lombok.NonNull;
import org.springframework.lang.Nullable;

public record ProductItem(@NonNull String title, @NonNull String description, @NonNull String price,
                          @Nullable String image) {
}
