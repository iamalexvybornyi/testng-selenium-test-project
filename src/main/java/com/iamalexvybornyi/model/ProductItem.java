package com.iamalexvybornyi.model;

import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Comparator;

public record ProductItem(@NonNull String title, @NonNull String description, @NonNull String price,
                          @Nullable String image, @Nullable String imageSrc) {

    public static class ProductItemTitleComparatorAsc implements Comparator<ProductItem> {
        @Override
        public int compare(ProductItem o1, ProductItem o2) {
            return o1.title().compareTo(o2.title());
        }
    }

    public static class ProductItemTitleComparatorDesc implements Comparator<ProductItem> {
        @Override
        public int compare(ProductItem o1, ProductItem o2) {
            return o2.title().compareTo(o1.title());
        }
    }

    public static class ProductItemPriceComparatorAsc implements Comparator<ProductItem> {
        @Override
        public int compare(ProductItem o1, ProductItem o2) {
            return Integer.valueOf(o1.price().replaceAll("\\D", ""))
                    .compareTo(Integer.valueOf(o2.price().replaceAll("\\D", "")));
        }
    }

    public static class ProductItemPriceComparatorDesc implements Comparator<ProductItem> {
        @Override
        public int compare(ProductItem o1, ProductItem o2) {
            return Integer.valueOf(o2.price().replaceAll("\\D", ""))
                    .compareTo(Integer.valueOf(o1.price().replaceAll("\\D", "")));
        }
    }
}
