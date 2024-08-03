package com.iamalexvybornyi.model;

import lombok.NonNull;

public record OrderSummary(@NonNull String paymentInformation, @NonNull String shippingInformation,
                           @NonNull String priceSubtotal, @NonNull String tax, @NonNull String priceTotal) {
}
