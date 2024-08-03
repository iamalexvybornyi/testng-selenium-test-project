package com.iamalexvybornyi.model;

import lombok.NonNull;

public record CheckoutInformation(@NonNull String firstName, @NonNull String lastName, @NonNull String postalCode) {
}
