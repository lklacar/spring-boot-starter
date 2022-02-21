package com.example.demo.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ConfigurationProperties(prefix = "root", ignoreUnknownFields = false)
public class RootProperties {

    private final Async async = new Async();
    private final Http http = new Http();
    private final Cache cache = new Cache();
    private final Mail mail = new Mail();
    private final Security security = new Security();
    private final ApiDocs apiDocs = new ApiDocs();
    private final Logging logging = new Logging();
    private final CorsConfiguration cors = new CorsConfiguration();
    private final AuditEvents auditEvents = new AuditEvents();
    private final ClientApp clientApp = new ClientApp();

    @Getter
    @Setter
    public static class ClientApp {
        private String name = RootPropertyDefaults.ClientApp.name;
    }

    @Getter
    @Setter
    public static class Async {
        private int corePoolSize = RootPropertyDefaults.Async.corePoolSize;
        private int maxPoolSize = RootPropertyDefaults.Async.maxPoolSize;
        private int queueCapacity = RootPropertyDefaults.Async.queueCapacity;
    }

    @Getter
    @Setter
    public static class Http {
        private final Cache cache = new Cache();

        @Getter
        @Setter
        public static class Cache {
            private int timeToLiveInDays = RootPropertyDefaults.Http.Cache.timeToLiveInDays;
        }
    }

    @Getter
    @Setter
    public static class Cache {
        private final Ehcache ehcache = new Ehcache();

        @Getter
        @Setter
        public static class Ehcache {
            private int timeToLiveSeconds = RootPropertyDefaults.Cache.Ehcache.timeToLiveSeconds;
            private long maxEntries = RootPropertyDefaults.Cache.Ehcache.maxEntries;

        }
    }

    @Getter
    @Setter
    public static class Mail {
        private boolean enabled = RootPropertyDefaults.Mail.enabled;
        private String from = RootPropertyDefaults.Mail.from;
        private String baseUrl = RootPropertyDefaults.Mail.baseUrl;
    }

    @Getter
    @Setter
    public static class Security {
        private String contentSecurityPolicy = RootPropertyDefaults.Security.contentSecurityPolicy;
        private final Authentication authentication = new Authentication();
        private final RememberMe rememberMe = new RememberMe();

        @Getter
        @Setter
        public static class ClientAuthorization {
            private String accessTokenUri = RootPropertyDefaults.Security.ClientAuthorization.accessTokenUri;
            private String tokenServiceId = RootPropertyDefaults.Security.ClientAuthorization.tokenServiceId;
            private String clientId = RootPropertyDefaults.Security.ClientAuthorization.clientId;
            private String clientSecret = RootPropertyDefaults.Security.ClientAuthorization.clientSecret;
        }

        @Getter
        @Setter
        public static class Authentication {
            private final Jwt jwt = new Jwt();

            @Getter
            @Setter
            public static class Jwt {
                private String secret = RootPropertyDefaults.Security.Authentication.Jwt.secret;
                private String base64Secret = RootPropertyDefaults.Security.Authentication.Jwt.base64Secret;
                private long tokenValidityInSeconds = RootPropertyDefaults.Security.Authentication.Jwt.tokenValidityInSeconds;
                private long tokenValidityInSecondsForRememberMe = RootPropertyDefaults.Security.Authentication.Jwt.tokenValidityInSecondsForRememberMe;
            }
        }

        @Getter
        @Setter
        public static class RememberMe {
            @NotNull
            private String key = RootPropertyDefaults.Security.RememberMe.key;
        }
    }

    @Getter
    @Setter
    public static class ApiDocs {
        private String title = RootPropertyDefaults.ApiDocs.title;
        private String description = RootPropertyDefaults.ApiDocs.description;
        private String version = RootPropertyDefaults.ApiDocs.version;
        private String termsOfServiceUrl = RootPropertyDefaults.ApiDocs.termsOfServiceUrl;
        private String contactName = RootPropertyDefaults.ApiDocs.contactName;
        private String contactUrl = RootPropertyDefaults.ApiDocs.contactUrl;
        private String contactEmail = RootPropertyDefaults.ApiDocs.contactEmail;
        private String license = RootPropertyDefaults.ApiDocs.license;
        private String licenseUrl = RootPropertyDefaults.ApiDocs.licenseUrl;
        private String defaultIncludePattern = RootPropertyDefaults.ApiDocs.defaultIncludePattern;
        private String managementIncludePattern = RootPropertyDefaults.ApiDocs.managementIncludePattern;
        private Server[] servers = {};

        @Getter
        @Setter
        public static class Server {
            private String url;
            private String description;
        }
    }

    @Getter
    @Setter
    public static class Logging {
        private boolean useJsonFormat = RootPropertyDefaults.Logging.useJsonFormat;
        private final Logstash logstash = new Logstash();

        @Getter
        @Setter
        public static class Logstash {
            private boolean enabled = RootPropertyDefaults.Logging.Logstash.enabled;
            private String host = RootPropertyDefaults.Logging.Logstash.host;
            private int port = RootPropertyDefaults.Logging.Logstash.port;
            private int queueSize = RootPropertyDefaults.Logging.Logstash.queueSize;
        }
    }


    @Getter
    @Setter
    public static class AuditEvents {
        private int retentionPeriod = RootPropertyDefaults.AuditEvents.retentionPeriod;
    }
}
