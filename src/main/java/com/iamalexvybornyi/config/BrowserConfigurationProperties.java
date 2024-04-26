package com.iamalexvybornyi.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Getter
@Setter
@Validated
public class BrowserConfigurationProperties {
    @NonNull
    private String name;
    @NonNull
    private Boolean remote;
    @Nullable
    private String remoteServerAddress;
    @NonNull
    private Resolution resolution;
    @NonNull
    private Boolean headless;
    @NonNull
    private Timeout timeout;

    @Getter
    @Setter
    public static class Resolution {
        @NonNull
        private Integer width;
        @NonNull
        private Integer height;
    }

    @Getter
    @Setter
    public static class Timeout {
        @NonNull
        private Duration implicit;
        @NonNull
        private Duration explicit;
        @NonNull
        private Duration polling;
    }
}
