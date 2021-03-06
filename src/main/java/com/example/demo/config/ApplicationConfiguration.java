package com.example.demo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({ RootProperties.class, ApplicationProperties.class })
@Configuration
public class ApplicationConfiguration {
}
