package com.iamalexvybornyi.model;

import lombok.NonNull;

public record ProductItem(@NonNull String title, @NonNull String description, @NonNull String price,
                          @NonNull String image) {
}
