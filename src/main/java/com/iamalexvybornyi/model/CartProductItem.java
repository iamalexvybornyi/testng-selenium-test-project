package com.iamalexvybornyi.model;

import lombok.NonNull;

public record CartProductItem(@NonNull String title, @NonNull String description, @NonNull String price,
                              @NonNull Integer quantity) {
}
