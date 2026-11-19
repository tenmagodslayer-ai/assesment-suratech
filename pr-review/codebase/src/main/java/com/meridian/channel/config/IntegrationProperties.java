package com.meridian.channel.config;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "meridian.integration")
public record IntegrationProperties(
        String quoteExperienceApiUrl,
        Duration connectTimeout,
        Duration readTimeout,
        String clientRegistrationId) {
}
