package com.meridian.channel.config;

import com.meridian.channel.customer.Country;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Country-specific settings. Behavior that differs by country is configured here,
 * not coded with conditionals.
 */
@ConfigurationProperties(prefix = "meridian")
public record CountryProperties(Map<Country, CountrySettings> countries) {

    public CountrySettings forCountry(Country country) {
        CountrySettings settings = countries.get(country);
        if (settings == null) {
            throw new IllegalStateException("Country not configured: " + country);
        }
        return settings;
    }

    public record CountrySettings(String currency, Map<String, ProductSettings> products) {
    }

    public record ProductSettings(BigDecimal taxRate) {
    }
}
