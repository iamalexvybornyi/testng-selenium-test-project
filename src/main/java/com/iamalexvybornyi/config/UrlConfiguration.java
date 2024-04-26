package com.iamalexvybornyi.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.url")
@Validated
public class UrlConfiguration {
    @NonNull
    private Saucedemo saucedemo;
    @NonNull
    private Dhtmlx dhtmlx;
    @NonNull
    private Letskodeit letskodeit;

    @Getter
    @Setter
    public static class Saucedemo {
        @NonNull
        private String home;
        @NonNull
        private String inventory;
        @NonNull
        private String cart;
    }

    @Getter
    @Setter
    public static class Dhtmlx {
        @NonNull
        private String home;
    }

    @Getter
    @Setter
    public static class Letskodeit {
        @NonNull
        private String practice;
    }
}
