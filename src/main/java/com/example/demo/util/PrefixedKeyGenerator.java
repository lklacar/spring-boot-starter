package com.example.demo.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PrefixedKeyGenerator implements KeyGenerator {

    private final String prefix;

    public PrefixedKeyGenerator(GitProperties gitProperties, BuildProperties buildProperties) {
        this.prefix = generatePrefix(gitProperties, buildProperties);
    }

    private String generatePrefix(GitProperties gitProperties, BuildProperties buildProperties) {
        String shortCommitId = null;
        if (Objects.nonNull(gitProperties)) {
            shortCommitId = gitProperties.getShortCommitId();
        }

        Instant time = null;
        String version = null;
        if (Objects.nonNull(buildProperties)) {
            time = buildProperties.getTime();
            version = buildProperties.getVersion();
        }
        Object p = ObjectUtils.firstNonNull(shortCommitId, time, version, RandomStringUtils.randomAlphanumeric(12));

        if (p instanceof Instant instant) {
            return DateTimeFormatter.ISO_INSTANT.format(instant);
        }
        return p.toString();
    }

    @Override
    @Nonnull
    public Object generate(@Nonnull Object target, Method method, @Nonnull Object... params) {
        return new PrefixedSimpleKey(prefix, method.getName(), params);
    }
}

