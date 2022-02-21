package com.example.demo.config;

public class ApplicationConstants {
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    private ApplicationConstants() {
        throw new IllegalStateException("Cannot instantiate");
    }
}
