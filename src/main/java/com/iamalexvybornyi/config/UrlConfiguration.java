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
    private String home;
    @NonNull
    private String inventory;
    @NonNull
    private String cart;
}
